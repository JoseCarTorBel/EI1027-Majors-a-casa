package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.DisponibilityDao;
import es.uji.ei1027.majorsacasa.dao.ElderlyPeopleDao;

import es.uji.ei1027.majorsacasa.dao.RequestDao;
import es.uji.ei1027.majorsacasa.model.Disponibility;
import es.uji.ei1027.majorsacasa.model.ElderlyPeople;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/elderlyPeople")
public class ElderlyPeopleController {

    private ElderlyPeopleDao elderlyPeopleDao;

    @Autowired
    public void setElderlyPeopleDao(ElderlyPeopleDao elderlyPeopleDao){
        this.elderlyPeopleDao=elderlyPeopleDao;
    }


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

        if (session.getAttribute("user") == null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (user.getRol()!="Elderly"){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a Elderly per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());

        }else{
            return "elderlyPeople/main";
        }

    }

    @RequestMapping("/volunteersManagement")
    public String getElderlyPeopleVolunteersManagement(HttpSession session, Model model){

        if (session.getAttribute("user") == null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (user.getRol()!="Elderly"){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a persona major per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());


        }else{
            return "elderlyPeople/volunteersManagement";
        }

    }

    DisponibilityDao disponibilityDao;
    @Autowired
    public void setDisponibilityDao(DisponibilityDao disponibilityDao){
        this.disponibilityDao=disponibilityDao;
    }

    @RequestMapping("/meusVoluntaris")
    public String getMeusVoluntaris(HttpSession session, Model model){

        if (session.getAttribute("user") == null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (user.getRol()!="Elderly"){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a persona major per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());

        }else{
            model.addAttribute("disponibilities", disponibilityDao.getDisponibilitiesElderly(user.getDni()));
            return "elderlyPeople/meusVoluntaris";
        }

    }

    @RequestMapping(value="/deleteDisponibility/{dayOfWeek}/{dniVolunteer}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeDispo(HttpSession session,@PathVariable Integer dayOfWeek, @PathVariable String dniVolunteer) {

        disponibilityDao.removeDisponibility(dayOfWeek, dniVolunteer);
        return "redirect:../../meusVoluntaris";
    }

    @RequestMapping("/voluntarisLliures")
    public String getVoluntarisLliures(HttpSession session, Model model){

        if (session.getAttribute("user") == null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (user.getRol()!="Elderly"){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a persona major per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());

        }else{
            model.addAttribute("disponibilities", disponibilityDao.getDisponibilitiesLibres());
            return "elderlyPeople/voluntarisLliures";
        }

    }

    @RequestMapping(value="/solicitarDisponibility/{dayOfWeek}/{dniVolunteer}", method = {RequestMethod.GET, RequestMethod.POST})
    public String solicitarDispo(HttpSession session, Model model, @PathVariable Integer dayOfWeek, @PathVariable String dniVolunteer) {

        if (session.getAttribute("user") == null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (user.getRol()!="Elderly"){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a persona major per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());

        }else{
            model.addAttribute("disponibilities", disponibilityDao.getDisponibilitiesLibres());
            Disponibility nuevaDisp = disponibilityDao.getDisponibility(dayOfWeek, dniVolunteer);
            nuevaDisp.setState('P');
            nuevaDisp.setDniElderlyPeople(user.getDni());
            disponibilityDao.updateDisponibility(nuevaDisp);
            return "redirect:../../voluntarisLliures";
        }


    }

    @RequestMapping("/services")
    public String getElderlyPeopleServices(HttpSession session, Model model){

        if (session.getAttribute("user") == null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (user.getRol()!="Elderly"){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a persona major per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());


        }else{
            return "elderlyPeople/services";
        }

    }

    RequestDao requestDao;
    @Autowired
    public void setRequestyDao(RequestDao requestDao){
        this.requestDao=requestDao;
    }

    @RequestMapping("/meusServeis")
    public String getMeusServeis(HttpSession session, Model model){

        if (session.getAttribute("user") == null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (user.getRol()!="Elderly"){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a persona major per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());

        }else{
            model.addAttribute("requests", requestDao.getRequestsElderly(user.getDni()));
            return "elderlyPeople/meusServeis";
        }

    }

    @RequestMapping("/nousServeis")
    public String getNousServeis(HttpSession session, Model model){

        if (session.getAttribute("user") == null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (user.getRol()!="Elderly"){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a persona major per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());

        }else{
            //model.addAttribute("requests", requestDao.getRequestsElderly(user.getDni()));
            //Puede que haya que pasarle algo
            return "elderlyPeople/nousServeis";
        }

    }

    @RequestMapping("/help")
    public String getElderlyPeopleAjuda(HttpSession session, Model model){

        if (session.getAttribute("user") == null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (user.getRol()!="Elderly"){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a persona major per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());


        }else{

            model.addAttribute("mensaje", "");
            return "elderlyPeople/help";
        }

    }

}
