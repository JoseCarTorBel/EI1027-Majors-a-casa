package es.uji.ei1027.majorsacasa.controller;


import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import es.uji.ei1027.majorsacasa.dao.DisponibilityDao;
import es.uji.ei1027.majorsacasa.dao.VolunteerDao;
import es.uji.ei1027.majorsacasa.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/volunteer")
public class VolunteerController {

    private VolunteerDao volunteerDao;

    @Autowired
    public void setVolunteerDao(VolunteerDao volunteerDao){
        this.volunteerDao=volunteerDao;
    }

    DisponibilityDao disponibilityDao;
    @Autowired
    public void setDisponibilityDao(DisponibilityDao disponibilityDao){
        this.disponibilityDao=disponibilityDao;
    }



    /**
     * Carga la vista principal de voluntario, para ello comprueba si el usuario ha iniciado sesion (obj session), y
     *  despues si tiene el rol adecuado para acceder a esta pagina, finalmente comprueba si tiene la peticion aceptada
     *  por el cas, en caso contrario le muestra un mensaje de error y le devuelve al main
     *
     */
    @RequestMapping("/main")
    public String getVolunteerMain(HttpSession session, Model model){

        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }

        Volunteer volunteer = volunteerDao.getVolunteer(user.getDni());
        if (volunteer.getState()=='A'){


            ArrayList<String> directions = (ArrayList<String>) disponibilityDao.getDirectionsAccepted(user.getDni());
            ArrayList<Disponibility> disponibilities = (ArrayList<Disponibility>) disponibilityDao.getDisponibilitysAccepted(user.getDni());

            int i=0;
            while(i<disponibilities.size()){
                if(disponibilities.get(i).getInitialTime().isAfter(LocalDate.now()) || disponibilities.get(i).getFinalTime().isBefore(LocalDate.now()) ) {
                    disponibilities.remove(i);
                    directions.remove(i);
                }else{
                    i++;
                }
            }


            model.addAttribute("directions",directions);

            model.addAttribute("disponibilities",disponibilities);

            return "volunteer/main";
        }else{
            System.out.println("El voluntario no tiene su peticion activada por el cas");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }


    }


    /**
     * Carga la vista de disponibilitys, para ello comprueba si el usuario ha iniciado sesion (obj session), y
     *  despues si tiene el rol adecuado para acceder a esta pagina, finalmente comprueba si tiene la peticion aceptada
     *  por el cas, en caso contrario le muestra un mensaje de error y le devuelve al main
     *
     */
    @RequestMapping("/disponibilitys")
    public String getVolunteerList(HttpSession session, Model model){


        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }

        model.addAttribute("disponibilities", disponibilityDao.getDisponibilitys(user.getDni()));

        return "volunteer/disponibilitys";


    }


    /**
     * Escuchador para borraar una disponibility, borra la disponibility que queremos (dia de la semana, dni)
     * solo se pueden borrar si no estan aceptadas
     *
     */
    @RequestMapping(value="/deleteDisponibility/{dayOfWeek}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeDispo(HttpSession session,@PathVariable Integer dayOfWeek){

        UserDetails user = checkSession(session);

        disponibilityDao.removeDisponibility(dayOfWeek,user.getDni());
        throw  new MajorsACasaException("Disponibilitat esborrada correctament","Success","../../volunteer/disponibilitys");
    }


    /**
     * Escuchador para actualizar una disponibility, para ello comprueba si el usuario ha iniciado sesion (obj session), y
     * despues si tiene el rol adecuado para acceder a esta pagina, finalmente comprueba si tiene la peticion aceptada
     * por el cas, en caso contrario le muestra un mensaje de error y le devuelve al main
     */
    @RequestMapping(value="/updateDisponibility/{dayOfWeek}", method = {RequestMethod.GET})
    public String updateDisponibility(Model model,HttpSession session,@PathVariable Integer dayOfWeek){

        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }

        model.addAttribute("disponibility",disponibilityDao.getDisponibility(dayOfWeek,user.getDni()));

        return "volunteer/updateDisponibility";
    }

    // Una vez le damos a sumbit se actualiza el voluntario en el dao
    @RequestMapping(value="/updateDisponibility", method=RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("disponibility") Disponibility disponibility,
            BindingResult bindingResult,HttpSession session) {

        UserDetails user = checkSession(session);

        if (bindingResult.hasErrors())
            return "volunteer/updateDisponibility";

        disponibilityDao.updateDisponibility(disponibility);

        throw  new MajorsACasaException("Disponibilitat actualiztzada correctament","Success","../volunteer/main");
    }



    /**
     * Carga la vista de registro de un voluntario, para ello comprueba si el usuario ha iniciado sesion (obj session)
     * en su caso le redirige a su main
     *
     */
    @RequestMapping(value="/add")
    public String addVolunteer(Model model,HttpSession session){

        if (session.getAttribute("user") == null) {
            model.addAttribute("volunteer", new Volunteer());
            return "volunteer/add";
        }else{
            UserDetails user = (UserDetails) session.getAttribute("user");
            throw  new MajorsACasaException("No pots accedir al login perquè ja has iniciat sessió.","AccesDenied","../"+user.getMainPage());

        }
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("volunteer") Volunteer volunteer,
                                   BindingResult bindingResult) {
        VolunteerVallidator validator = new VolunteerVallidator();
        validator.validate(volunteer, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "volunteer/add";
        }
        try {
            volunteerDao.addVolunteer(volunteer);
        }catch (DuplicateKeyException dk){
            throw new MajorsACasaException("Ja existeix un voluntari amb el dni "+volunteer.getDni()+" o amb el usuari "+volunteer.getUsername(),"CPduplicada");
        } catch (DataAccessException e) {
            throw new MajorsACasaException(
                    "Error en l'accés a la base de dades", "ErrorAccedintDades");
        }

        throw  new MajorsACasaException("Te has registrat correctament","Success","../volunteer/main");
    }

    // Cuando le damos al boton editar en la lista, llamamos a este metodo el cual llama a la vista correspondiente
    //      pasandole el objeto voluntario
    @RequestMapping(value="/update" ,method = RequestMethod.GET)
    public String update(Model model,HttpSession session){

        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        model.addAttribute("volunteer",volunteerDao.getVolunteer(user.getDni()));

        return "volunteer/update";
    }

    // Una vez le damos a sumbit se actualiza el voluntario en el dao
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("volunteer") Volunteer volunteer,
            BindingResult bindingResult,HttpSession session) {

        UserDetails user = checkSession(session);

        if (bindingResult.hasErrors())
            return "volunteer/update";
        volunteerDao.updateVolunteer(volunteer);
        throw  new MajorsACasaException("Actualitzat correctament","Success","../volunteer/main");
    }

    // Una vez en el listado le damos al boton delete, lo borramos del dao
    @RequestMapping(value="/delete/{dni}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeVolunteer(@PathVariable String dni){
        volunteerDao.removeVolunteer(dni);
        throw  new MajorsACasaException("BorradoCorrectamente","Success","../volunteer/list");
    }

    // LLama a la vista pasandole un objeto disponibility
    @RequestMapping(value="/newDisponibility")
    public String addDisponibility(Model model,HttpSession session){

        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        model.addAttribute("disponibility",new Disponibility());
        return "volunteer/newDisponibility";
    }

    // Una vez le damos al sumbit la vista devuelve el objeto disponibility con todos los atributos
    @RequestMapping(value="/newDisponibility", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("disponibility") Disponibility disponibility,
                                   BindingResult bindingResult,HttpSession session) {

        UserDetails user = checkSession(session);

        disponibility.setDniVolunteer(user.getDni());
        if (bindingResult.hasErrors()) {
            return "volunteer/newDisponibility";
        }
        try {
            disponibilityDao.addDisponibility(disponibility);
        }catch (DuplicateKeyException dk){
            throw new MajorsACasaException("Ja tens una disponibility el dia "+disponibility.getDayOfWeek(),"CPduplicada");
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new MajorsACasaException(
                    "Error en l'accés a la base de dades", "ErrorAccedintDades");
        }

        throw  new MajorsACasaException("Disponibilitat creada corectament","Success","../"+user.getMainPage());
    }

    @RequestMapping(value = "/contact",method=RequestMethod.GET)
    public String contactWithCas(HttpSession session, Model model) {

        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        model.addAttribute("volunteer",volunteerDao.getVolunteer(user.getDni()));
        model.addAttribute("msg",new String());

        return "volunteer/contact";

    }

    @RequestMapping(value="/contact",method=RequestMethod.POST)
    public String contactSubmit(@ModelAttribute("volunteer") Volunteer volunteer,
                                @ModelAttribute("msg") String msg,
                                BindingResult bindingResult,HttpSession session){

        UserDetails user = checkSession(session);

        if (bindingResult.hasErrors())
            return "volunteer/contact";
        //todo arreglar o quitar

        System.out.println("Mensaje enviado por "+volunteer.getName()+": "+msg);
        throw  new MajorsACasaException("Missatge enviat correctament","Mensaje ENviado","../"+user.getMainPage());
    }

    /**
     * Carga la vista de disponibilitys, para ello comprueba si el usuario ha iniciado sesion (obj session), y
     *  despues si tiene el rol adecuado para acceder a esta pagina, finalmente comprueba si tiene la peticion aceptada
     *  por el cas, en caso contrario le muestra un mensaje de error y le devuelve al main
     *
     */
    @RequestMapping("/hobbies")
    public String getHobbiesList(HttpSession session, Model model){

        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        model.addAttribute("hobbie",new Hobbie());
        model.addAttribute("hobbies", volunteerDao.getHobbies(user.getDni()));

        return "volunteer/hobbies";


    }

    @RequestMapping(value="/deleteHobbie/{hobbie}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeHobbie(HttpSession session,@PathVariable String hobbie){

        UserDetails user = checkSession(session);

        volunteerDao.removeHobbie(user.getDni(),hobbie);
        throw  new MajorsACasaException("Hobbie esborrat correctament","Success","../../volunteer/hobbies");
    }


    // Una vez le damos al sumbit la vista devuelve el objeto disponibility con todos los atributos
    @RequestMapping(value="/newHobbie", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("hobbie") Hobbie hobbie,
                                   BindingResult bindingResult,HttpSession session) {

        UserDetails user = checkSession(session);

        if (bindingResult.hasErrors()) {
            return "volunteer/newHobbie";
        }
        try {
            volunteerDao.newHobbie(user.getDni(),hobbie.getHobbie());
        }catch (DuplicateKeyException dk){
            throw new MajorsACasaException("Ja tens un hobbie ["+hobbie.getHobbie()+"]","CPduplicada");
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new MajorsACasaException(
                    "Error en l'accés a la base de dades", "ErrorAccedintDades");
        }

        throw  new MajorsACasaException("Hobbie creaat corectament","Success","../../volunteer/hobbies");
    }




    // *************************
    // Métodos privados ********
    // *************************


    private UserDetails checkSession(HttpSession session){


        if(session.getAttribute("user")==null) {
          return null;
        }

        UserDetails user = (UserDetails) session.getAttribute("user");


        if (!user.getRol().equals("Volunteer")) {
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. \n" +
                    "Has d'haver iniciat sessió com a voluntari per a poder accedir-hi.", "AccesDenied", "../" + user.getMainPage());
        }

        return user;
    }
}



