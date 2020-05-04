package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.DisponibilityDao;
import es.uji.ei1027.majorsacasa.dao.VolunteerDao;
import es.uji.ei1027.majorsacasa.model.Disponibility;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import es.uji.ei1027.majorsacasa.model.Volunteer;
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

import javax.servlet.http.HttpSession;

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



    @RequestMapping("/main")
    public String getVolunteerMain(HttpSession session, Model model){

        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (!user.getRol().equals("Volunteer")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a voluntari per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());

        }else{
            Volunteer volunteer = volunteerDao.getVolunteer(user.getDni());
            if (volunteer.getState()=='A'){

                model.addAttribute("disponibilities", disponibilityDao.getDisponibilitysAccepted(user.getDni()));

                return "volunteer/main";
            }else{
                System.out.println("El voluntario no tiene su peticion activada por el cas");
                session.invalidate();
                throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
            }
        }

    }


    @RequestMapping("/disponibilitys")
    public String getVolunteerList(HttpSession session, Model model){



        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (!user.getRol().equals("Volunteer")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a voluntari per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());


        }else{
            model.addAttribute("disponibilities", disponibilityDao.getDisponibilitys(user.getDni()));

            return "volunteer/disponibilitys";
        }

    }


    @RequestMapping(value="/deleteDisponibility/{dayOfWeek}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeDispo(HttpSession session,@PathVariable Integer dayOfWeek){
        UserDetails user = (UserDetails) session.getAttribute("user");
        System.out.println(dayOfWeek+" "+user.getDni());
        disponibilityDao.removeDisponibility(dayOfWeek,user.getDni());
        return "redirect:../disponibilitys";
    }

    @RequestMapping(value="/updateDisponibility/{dayOfWeek}", method = {RequestMethod.GET})
    public String updateDisponibility(Model model,HttpSession session,@PathVariable Integer dayOfWeek){
        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }

        UserDetails user = (UserDetails) session.getAttribute("user");

        if (!user.getRol().equals("Volunteer")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a voluntari per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());
        }


        model.addAttribute("disponibility",disponibilityDao.getDisponibility(dayOfWeek,user.getDni()));

        return "volunteer/updateDisponibility";
    }

    // Una vez le damos a sumbit se actualiza el voluntario en el dao
    @RequestMapping(value="/updateDisponibility", method=RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("disponibility") Disponibility disponibility,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "volunteer/updateDisponibility";

        disponibilityDao.updateDisponibility(disponibility);
        return "redirect:main";
    }



    // LLama a la vista pasandole un objeto voluntario
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

    // Una vez le damos al sumbit la vista devuelve el objeto volunteer con todos los atributos
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

        return "redirect:main";
    }

    // Cuando le damos al boton editar en la lista, llamamos a este metodo el cual llama a la vista correspondiente
    //      pasandole el objeto voluntario
    @RequestMapping(value="/update" ,method = RequestMethod.GET)
    public String update(Model model,HttpSession session){

        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }

        UserDetails user = (UserDetails) session.getAttribute("user");

        if (!user.getRol().equals("Volunteer")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a voluntari per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());
        }


        model.addAttribute("volunteer",volunteerDao.getVolunteer(user.getDni()));

        return "volunteer/update";
    }

    // Una vez le damos a sumbit se actualiza el voluntario en el dao
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("volunteer") Volunteer volunteer,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "volunteer/update";
        volunteerDao.updateVolunteer(volunteer);
        return "redirect:main";
    }

    // Una vez en el listado le damos al boton delete, lo borramos del dao
    @RequestMapping(value="/delete/{dni}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeVolunteer(@PathVariable String dni){
        volunteerDao.removeVolunteer(dni);
        return "redirect:../list";
    }

    // LLama a la vista pasandole un objeto disponibility
    @RequestMapping(value="/newDisponibility")
    public String addDisponibility(Model model,HttpSession session){

        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }

        UserDetails user = (UserDetails) session.getAttribute("user");
        if (!user.getRol().equals("Volunteer")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a voluntari per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());

        }

        model.addAttribute("disponibility",new Disponibility());
        return "volunteer/newDisponibility";
    }

    // Una vez le damos al sumbit la vista devuelve el objeto disponibility con todos los atributos
    @RequestMapping(value="/newDisponibility", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("disponibility") Disponibility disponibility,
                                   BindingResult bindingResult,HttpSession session) {
        UserDetails user = (UserDetails) session.getAttribute("user");
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

        return "redirect:main";
    }



}
