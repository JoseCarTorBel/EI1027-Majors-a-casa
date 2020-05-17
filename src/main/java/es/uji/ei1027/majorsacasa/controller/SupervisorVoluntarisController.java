package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.DisponibilityDao;
import es.uji.ei1027.majorsacasa.dao.VolunteerDao;
import es.uji.ei1027.majorsacasa.model.Company;
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
@RequestMapping("/supervisorVoluntaris")
public class SupervisorVoluntarisController {

    private VolunteerDao volunteerDao;

    @Autowired
    public void setDVolunteerDao(VolunteerDao volunteerDao){
        this.volunteerDao=volunteerDao;
    }


    @RequestMapping("/main")
    public String getSupVolunteerMain(HttpSession session, Model model){



        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (!user.getRol().equals("SupervisorVolunatris")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a CAS supervisor voluntaris  per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());

        }else{
            return "supervisorVoluntaris/main";
        }

    }

    @RequestMapping("/requestPendent")
    public String getRequestVolunteerList(Model model,HttpSession session){
        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (!user.getRol().equals("SupervisorVolunatris")) {
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a CAS supervisor voluntaris  per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());
        }


        model.addAttribute("volunteers",volunteerDao.getVolunteersPendent());
        return "supervisorVoluntaris/requestPendent";
    }


    @RequestMapping("/listVolunteers")
    public String getVolunteerList(Model model,HttpSession session){
        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (!user.getRol().equals("SupervisorVolunatris")) {
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a CAS supervisor voluntaris  per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());
        }


        model.addAttribute("volunteers",volunteerDao.getVolunteerList());
        return "supervisorVoluntaris/listVolunteers";
    }


    @RequestMapping(value="/accept/{dniVolunteer}", method = {RequestMethod.GET})
    public String acceptVolunteer(@PathVariable String dniVolunteer,HttpSession session){
        volunteerDao.acceptVolunteer(dniVolunteer);
        throw  new MajorsACasaException("Voluntari acceptat correctament","Success","../../supervisorVoluntaris/requestPendent");
    }

    @RequestMapping(value="/deny/{dniVolunteer}", method = {RequestMethod.GET})
    public String denyVolunteer(@PathVariable String dniVolunteer,HttpSession session){
        volunteerDao.denyVolunteer(dniVolunteer);
        throw  new MajorsACasaException("Voluntari denegat correctament","Success","../../supervisorVoluntaris/requestPendent");
    }

    // Cuando le damos al boton editar en la lista, llamamos a este metodo el cual llama a la vista correspondiente
    //      pasandole el objeto voluntario
    @RequestMapping(value="/update/{dniVolunteer}" ,method = RequestMethod.GET)
    public String update(Model model,HttpSession session,@PathVariable String dniVolunteer){

        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }

        UserDetails user = (UserDetails) session.getAttribute("user");

        if (!user.getRol().equals("SupervisorVolunatris")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a CAS supervisor voluntaris  per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());
        }
        Volunteer volunteer = volunteerDao.getVolunteer(dniVolunteer);
        session.setAttribute("passwd",volunteer.getPasswd());
        model.addAttribute("volunteer",volunteer);

        return "supervisorVoluntaris/update";
    }

    // Una vez le damos a sumbit se actualiza el voluntario en el dao
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("volunteer") Volunteer volunteer,
            BindingResult bindingResult,HttpSession session) {
        if (bindingResult.hasErrors())
            return "supervisorVoluntaris/update";
        volunteer.setPasswd((String) session.getAttribute("passwd"));


        volunteerDao.updateVolunteer(volunteer);
        throw  new MajorsACasaException("Voluntari actualitzat correctament","Success","../../supervisorVoluntaris/main");
    }

    /**
     * Da la información extendida del voluntari.
     */
    @RequestMapping(value="/infoVolunteer/{dni}")
    public String getInfoCompany(@PathVariable String dni, HttpSession session, Model model){

        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }

        UserDetails user = (UserDetails) session.getAttribute("user");

        if (!user.getRol().equals("SupervisorVolunatris")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a CAS supervisor voluntaris  per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());
        }

        Volunteer volunteer =  volunteerDao.getVolunteer(dni);

        model.addAttribute("volunteer",volunteer);
        model.addAttribute("hobbies",volunteerDao.getHobbies(dni));

        return "supervisorVoluntaris/infoVolunteer";
    }




}
