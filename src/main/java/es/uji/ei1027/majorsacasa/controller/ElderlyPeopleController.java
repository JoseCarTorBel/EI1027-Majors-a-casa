package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.DisponibilityDao;
import es.uji.ei1027.majorsacasa.dao.ElderlyPeopleDao;

import es.uji.ei1027.majorsacasa.dao.RequestDao;
import es.uji.ei1027.majorsacasa.dao.VolunteerDao;
import es.uji.ei1027.majorsacasa.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/elderlyPeople")
public class ElderlyPeopleController {

    private ElderlyPeopleDao elderlyPeopleDao;

    @Autowired
    public void setElderlyPeopleDao(ElderlyPeopleDao elderlyPeopleDao){
        this.elderlyPeopleDao=elderlyPeopleDao;
    }

    private VolunteerDao volunteerDao;
    @Autowired
    public void setVolunteerDao(VolunteerDao volunteerDao){this.volunteerDao=volunteerDao;}



    // Probar a mostrar la informacion de 1 elderlypeople
    @RequestMapping("/provaElderlyPeople")
    public String provaUnElderlyPeople(Model model) {

        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople("20987655T");

        model.addAttribute("elderlyPeople", elderlyPeople);
        return "elderlyPeople/prova_elderlyPeople";
    }


    // Llama a la vista elderly_list pasandole el listado completo de elderlys
    @RequestMapping("/list")
    public String getElderlyPeopleList(Model model){
        model.addAttribute("elderlys",elderlyPeopleDao.getElderlyPeopleList());
        return "elderlyPeople/list";
    }

    // LLama a la vista pasandole un objeto elderly
    @RequestMapping(value="/add")
    public String addElderlyPeople(Model model){
        model.addAttribute("elderlyPeople",new ElderlyPeople());
        return "elderlyPeople/add";
    }

    // Una vez le damos al sumbit la vista devuelve el objeto elderlyPeople con todos los atributos
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("elderlyPeople") ElderlyPeople elderlyPeople,
                                   BindingResult bindingResult) {

        ElderlyPeopleVallidator elderlyPeopleVallidator = new ElderlyPeopleVallidator();
        elderlyPeopleVallidator.validate(elderlyPeople, bindingResult);

        if (bindingResult.hasErrors())
            return "elderlyPeople/add";
        elderlyPeopleDao.addElderlyPeople(elderlyPeople);
        return "redirect:list";
    }

    // Cuando le damos al boton editar en la lista, llamamos a este metodo el cual llama a la vista correspondiente
    //      pasandole el objeto elderlyPeople
    @RequestMapping(value="/update/{dni}", method= {RequestMethod.GET, RequestMethod.POST})
    public String updatePassword(Model model, @PathVariable String dni){
        model.addAttribute("elderlyPeople",elderlyPeopleDao.getElderlyPeople(dni));
        return "elderlyPeople/update";
    }

    // Una vez le damos a sumbit se actualiza el elderly en el dao
    @RequestMapping(value="/update", method = {RequestMethod.GET, RequestMethod.POST})
    public String processUpdateSubmit(
            @ModelAttribute("elderlyPeople") ElderlyPeople elderlyPeople,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "elderlyPeople/update";

        elderlyPeopleDao.updateElderlyPeople(elderlyPeople);
        return "redirect:list";
    }

    // Una vez en el listado le damos al boton delete, lo borramos del dao
    @RequestMapping(value="/delete/{dni}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeElderlyPeople(@PathVariable String dni){
        elderlyPeopleDao.removeElderyPeople(dni);
        return "redirect:../list";
    }

    @RequestMapping("/main")
    public String getElderlyPeopleMain(HttpSession session, Model model){

        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            return "elderlyPeople/main";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }

    }

    @RequestMapping("/volunteersManagement")
    public String getElderlyPeopleVolunteersManagement(HttpSession session, Model model){
        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            return "elderlyPeople/volunteersManagement";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }

    }

    DisponibilityDao disponibilityDao;
    @Autowired
    public void setDisponibilityDao(DisponibilityDao disponibilityDao){
        this.disponibilityDao=disponibilityDao;
    }

    @RequestMapping("/meusVoluntaris")
    public String getMeusVoluntaris(HttpSession session, Model model){
        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            model.addAttribute("disponibilities", disponibilityDao.getDisponibilitiesElderly(user.getDni()));
            return "elderlyPeople/meusVoluntaris";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }

    }

    @RequestMapping(value="/deleteDisponibility/{dayOfWeek}/{dniVolunteer}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeDispo(HttpSession session,@PathVariable Integer dayOfWeek, @PathVariable String dniVolunteer) {

        disponibilityDao.removeDisponibility(dayOfWeek, dniVolunteer);
        return "redirect:../../meusVoluntaris";
    }

    @RequestMapping("/voluntarisLliures")
    public String getVoluntarisLliures(HttpSession session, Model model){
        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            model.addAttribute("disponibilities", disponibilityDao.getDisponibilitiesLibres());
            return "elderlyPeople/voluntarisLliures";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }

    }

    @RequestMapping("/viewVolunteerMeu/{dniVolunteer}")
    public String getVoluntariMeu(HttpSession session, Model model, @PathVariable String dniVolunteer){
        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            model.addAttribute("volunteer", volunteerDao.getVolunteer(dniVolunteer) );
            model.addAttribute("hobbies", volunteerDao.getHobbies(dniVolunteer));
            return "elderlyPeople/fichaVolunteerMeus";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }

    }

    @RequestMapping("/viewVolunteerLliure/{dniVolunteer}")
    public String getVoluntariLliure(HttpSession session, Model model, @PathVariable String dniVolunteer){
        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            model.addAttribute("volunteer", volunteerDao.getVolunteer(dniVolunteer) );
            model.addAttribute("hobbies", volunteerDao.getHobbies(dniVolunteer));
            return "elderlyPeople/fichaVolunteerLliure";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }

    }

    @RequestMapping(value="/solicitarDisponibility/{dayOfWeek}/{dniVolunteer}", method = {RequestMethod.GET, RequestMethod.POST})
    public String solicitarDispo(HttpSession session, Model model, @PathVariable Integer dayOfWeek, @PathVariable String dniVolunteer) {
        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            model.addAttribute("disponibilities", disponibilityDao.getDisponibilitiesLibres());
            Disponibility nuevaDisp = disponibilityDao.getDisponibility(dayOfWeek, dniVolunteer);
            nuevaDisp.setState('P');
            nuevaDisp.setDniElderlyPeople(user.getDni());
            disponibilityDao.updateDisponibility(nuevaDisp);
            return "redirect:../../voluntarisLliures";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }
    }

    @RequestMapping("/services")
    public String getElderlyPeopleServices(HttpSession session, Model model){
        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            return "elderlyPeople/services";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }

    }

    RequestDao requestDao;
    @Autowired
    public void setRequestyDao(RequestDao requestDao){
        this.requestDao=requestDao;
    }

    @RequestMapping("/meusServeis")
    public String getMeusServeis(HttpSession session, Model model){
        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            model.addAttribute("requests", requestDao.getRequestsElderly(user.getDni()));
            return "elderlyPeople/meusServeis";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }

    }

    @RequestMapping("/nousServeis")
    public String getNousServeis(HttpSession session, Model model){
        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            char menjar = 'C';
            char neteja = 'C';
            char salut = 'C';

            //Comprovar estat actual del servei de menjar
            for(Request req : requestDao.getRequestsMenjarElderly(user.getDni())){
                if(req.getState()=='P'){
                    menjar = 'P';
                    break;
                }else{
                    if(req.getState()=='A'){
                        if(req.getEndDate().isAfter(LocalDate.now())){
                            menjar = 'A';
                            break;
                        }
                    }
                }
            }

            //Comprovar estat actual del servei de neteja
            for(Request req : requestDao.getRequestsNetejaElderly(user.getDni())){
                if(req.getState()=='P'){
                    neteja = 'P';
                    break;
                }else{
                    if(req.getState()=='A'){
                        if(req.getEndDate().isAfter(LocalDate.now())){
                            neteja = 'A';
                            break;
                        }
                    }
                }
            }

            //Comprovar estat actual del servei de salut
            for(Request req : requestDao.getRequestsSalutElderly(user.getDni())){
                if(req.getState()=='P'){
                    salut = 'P';
                    break;
                }else{
                    if(req.getState()=='A'){
                        if(req.getEndDate().isAfter(LocalDate.now())){
                            salut = 'A';
                            break;
                        }
                    }
                }
            }

            model.addAttribute("menjar", menjar);
            model.addAttribute("neteja", neteja);
            model.addAttribute("salut", salut);


            return "elderlyPeople/nousServeis";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }

    }

    @RequestMapping(value="/solicitarServei/{serviceType}/{price}", method = {RequestMethod.GET, RequestMethod.POST})
    public String solicitarServei(HttpSession session, Model model, @PathVariable Integer serviceType, @PathVariable Integer price) {
        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            LocalDateTime actual = LocalDateTime.now();
            String codReq = "R" + actual.getYear()%100 + actual.getMonth() + actual.getDayOfMonth() + actual.getHour() + actual.getMinute() + actual.getSecond() + actual.getNano();
            Request nuevaReq = new Request(codReq,'P',ServiceType.getOpcion(serviceType),null,null,false,null,null,price,user.getDni(),null);
            requestDao.addRequest(nuevaReq);
            return "redirect:../../nousServeis";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }

    }

    @RequestMapping("/help")
    public String getElderlyPeopleAjuda(HttpSession session, Model model){
        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            model.addAttribute("mensaje", "");
            return "elderlyPeople/help";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }

    }

    @RequestMapping(value = "/help",method=RequestMethod.GET)
    public String contactWithCas(HttpSession session, Model model) {
        UserDetails user = checkSession(session);
        if (user==null){
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        ElderlyPeople elderlyPeople = elderlyPeopleDao.getElderlyPeople(user.getDni());

        if (elderlyPeople.getState()=='A'){

            model.addAttribute("elderly", elderlyPeopleDao.getElderlyPeople(user.getDni()));

            return "elderlyPeople/help";

        }else{
            System.out.println("El CAS no a acceptat la petició d'aquesta persona major");
            session.invalidate();
            throw  new MajorsACasaException("El CAS committee ha d'haver acceptat la petició de registre abans d'iniciar sessió.","AccesDenied");
        }

    }

    @RequestMapping(value="/help",method=RequestMethod.POST)
    public String contactSubmit(@ModelAttribute("elderly") Company company,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "elderlyPeople/help";

        throw  new MajorsACasaException("En breu es ficarem en contacte.","Success","../elderlyPeople/main");
    }



    private UserDetails checkSession(HttpSession session){


        if(session.getAttribute("user")==null) {
            return null;
        }

        UserDetails user = (UserDetails) session.getAttribute("user");


        if (!user.getRol().equals("Elderly")) {
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a persona major per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());

        }

        return user;
    }

}
