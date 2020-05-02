package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.ElderlyPeopleDao;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/comite")
public class CasComiteControler {

    private ElderlyPeopleDao elderlyPeopleDao;

    @Autowired
    public void setElderlyPeopleDao(ElderlyPeopleDao elderlyPeopleDao){
        this.elderlyPeopleDao=elderlyPeopleDao;
    }


    @RequestMapping("/main")
    public String getComiteMain(HttpSession session, Model model){

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

    @RequestMapping("/solicitudsRegistre")
    public String getComiteSolicitudsRegistre(HttpSession session, Model model){

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

            model.addAttribute("personesMajors", elderlyPeopleDao.getElderlyPeopleSetState('P'));

            return "comite/solicitudsRegistre";
        }

    }

    @RequestMapping("/solicitudsServeis")
    public String getComiteSolicitudsServeis(HttpSession session, Model model){

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
            return "comite/solicitudsServeis";
        }

    }

    @RequestMapping("/solicitudsVoluntaris")
    public String getComiteSolicitudsVoluntaris(HttpSession session, Model model){

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
            return "comite/solicitudsVoluntaris";
        }

    }
}
