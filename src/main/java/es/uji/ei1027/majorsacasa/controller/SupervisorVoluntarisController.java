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
        UserDetails usuario = (UserDetails) session.getAttribute("user");

        if (!usuario.getRol().equals("SupervisorVolunatris")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            //TODO redirija al main o a index o donde sea
            //TODO muestre un mensaje de error
            return "redirect:/";

        }else{
            return "supervisorVoluntaris/main";
        }

    }

    @RequestMapping("/requestPendent")
    public String getVolunteerList(Model model,HttpSession session){
        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails usuario = (UserDetails) session.getAttribute("user");

        if (!usuario.getRol().equals("SupervisorVolunatris")) {
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            //TODO redirija al main o a index o donde sea
            //TODO muestre un mensaje de error
            return "redirect:/";
        }


        model.addAttribute("volunteers",volunteerDao.getVolunteersPendent());
        return "supervisorVoluntaris/requestPendent";
    }




}
