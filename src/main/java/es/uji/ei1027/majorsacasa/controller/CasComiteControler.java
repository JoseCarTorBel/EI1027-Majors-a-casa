package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/comite")
public class CasComiteControler {


    @RequestMapping("/main")
    public String getElderlyPeopleMain(HttpSession session, Model model){

        if (session.getAttribute("user") == null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (!user.getRol().equals("Comite")){
            System.out.println(user.getRol());
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a CAS Comite per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());

        }else{
            return "comite/main";
        }

    }
}
