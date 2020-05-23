package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.DisponibilityDao;
import es.uji.ei1027.majorsacasa.dao.ElderlyPeopleDao;
import es.uji.ei1027.majorsacasa.dao.RequestDao;
import es.uji.ei1027.majorsacasa.model.Disponibility;
import es.uji.ei1027.majorsacasa.model.ElderlyPeople;
import es.uji.ei1027.majorsacasa.model.Request;
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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/comite")
public class CasComiteControler {

    private ElderlyPeopleDao elderlyPeopleDao;
    @Autowired
    public void setElderlyPeopleDao(ElderlyPeopleDao elderlyPeopleDao){
        this.elderlyPeopleDao=elderlyPeopleDao;
    }

    DisponibilityDao disponibilityDao;
    @Autowired
    public void setDisponibilityDao(DisponibilityDao disponibilityDao){
        this.disponibilityDao=disponibilityDao;
    }

    RequestDao requestDao;
    @Autowired
    public void setRequestyDao(RequestDao requestDao){
        this.requestDao=requestDao;
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

    @RequestMapping(value="/acceptarSolicitud/{dni}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String acceptarSolicitud(HttpSession session, Model model, @PathVariable String dni) {

        ElderlyPeople nouElderly = elderlyPeopleDao.getElderlyPeople(dni);
        nouElderly.setState('A');
        elderlyPeopleDao.updateElderlyPeople(nouElderly);
        return "redirect:../solicitudsRegistre";
    }

    @RequestMapping(value="/rebujarSolicitud/{dni}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String rebujarSolicitud(HttpSession session, Model model, @PathVariable String dni) {

        ElderlyPeople nouElderly = elderlyPeopleDao.getElderlyPeople(dni);
        nouElderly.setState('R');
        elderlyPeopleDao.updateElderlyPeople(nouElderly);
        return "redirect:../solicitudsRegistre";
    }


    @RequestMapping("/solicitudsVoluntaris")
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
            model.addAttribute("disponibilities",disponibilityDao.getDisponibilitiesPendents());
            return "comite/solicitudsVoluntaris";
        }

    }

    @RequestMapping(value="/acceptarSolicitudVoluntari/{dayOfWeek}/{dniVolunteer}", method = {RequestMethod.GET, RequestMethod.POST})
    public String acceptarSolicitudVoluntari(HttpSession session, Model model, @PathVariable Integer dayOfWeek, @PathVariable String dniVolunteer) {

        Disponibility novaDisp = disponibilityDao.getDisponibility(dayOfWeek, dniVolunteer);
        novaDisp.setState('A');
        disponibilityDao.updateDisponibility(novaDisp);
        return "redirect:../../solicitudsVoluntaris";
    }

    @RequestMapping(value="/rebujarSolicitudVoluntari/{dayOfWeek}/{dniVolunteer}", method = {RequestMethod.GET, RequestMethod.POST})
    public String rebujarSolicitudVoluntari(HttpSession session, Model model, @PathVariable Integer dayOfWeek, @PathVariable String dniVolunteer) {

        Disponibility novaDisp = disponibilityDao.getDisponibility(dayOfWeek, dniVolunteer);
        novaDisp.setState('R');
        disponibilityDao.updateDisponibility(novaDisp);
        return "redirect:../../solicitudsVoluntaris";
    }

    @RequestMapping("/solicitudsServeis")
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

            model.addAttribute("requests", requestDao.getPendentRequest());

            return "comite/solicitudsServeis";
        }

    }

    @RequestMapping("/viewSolicitudRegistre/{dniElderlyPeople}")
    public String getSolicitudRegistre(HttpSession session, Model model, @PathVariable String dniElderlyPeople){

        if (session.getAttribute("user") == null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        if (!user.getRol().equals("Comite")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a CAS Comite per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());

        }else{
            model.addAttribute("elderly", elderlyPeopleDao.getElderlyPeople(dniElderlyPeople) );

            return "comite/viewSolicitudRegistre";
        }

    }

    @RequestMapping(value="/acceptarSolicitudServici/{codReq}", method=RequestMethod.GET)
    public String editRequest(Model model, @PathVariable String codReq) {
        model.addAttribute("request", requestDao.getRequest(codReq));
        return "comite/updateRequest";
    }

    @RequestMapping(value="/updateRequest", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("request") Request request,
            BindingResult bindingResult) {

        //RequestVallidator requestVallidator = new RequestVallidator();
        //requestVallidator.validate(request, bindingResult);
        if (bindingResult.hasErrors())
            return "comite/updateRequest";

        request.setState('A');
        request.setAprovedDate(LocalDate.now());
        System.out.println(request.toString());
        requestDao.updateRequest(request);
        return "redirect:../comite/main";
    }

    @RequestMapping(value="/rebujarSolicitudServici/{codReq}", method = {RequestMethod.GET, RequestMethod.POST})
    public String rebujarSolicitudVoluntari(HttpSession session, Model model, @PathVariable String codReq) {

        Request novaReq =requestDao.getRequest(codReq);
        novaReq.setState('R');
        novaReq.setRejected(true);
        requestDao.updateRequest(novaReq);
        return "redirect:../solicitudsServeis";
    }
}
