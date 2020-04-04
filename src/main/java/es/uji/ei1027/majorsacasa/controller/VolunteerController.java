package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.DisponibilityDao;
import es.uji.ei1027.majorsacasa.dao.VolunteerDao;
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


    // Probar a mostrar la informacion de 1 voluntario
    @RequestMapping("/provaVoluntari")
    public String provaUnVoluntari(Model model) {

        Volunteer volunteer = volunteerDao.getVolunteer("20904567S");

        model.addAttribute("volunteer", volunteer);
        return "volunteer/prova_voluntari";
    }



    @RequestMapping("/list")
    public String getVolunteerList(Model model){
        model.addAttribute("volunteers",volunteerDao.getVolunteerList());
        return "volunteer/list";
    }

    DisponibilityDao disponibilityDao;
    @Autowired
    public void setDisponibilityDao(DisponibilityDao disponibilityDao){
        this.disponibilityDao=disponibilityDao;
    }


    @RequestMapping("/timetable")
    public String getVolunteerList(HttpSession session, Model model){



        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        UserDetails usuario = (UserDetails) session.getAttribute("user");

        if (usuario.getRol()!="Volunteer"){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            //TODO redirija al main o a index o donde sea
            return "/";

        }else{
            model.addAttribute("disponibilities", disponibilityDao.getDisponibility(usuario.getDni()));
            return "volunteer/timetable";
        }

    }



    // LLama a la vista pasandole un objeto voluntario
    @RequestMapping(value="/add")
    public String addVolunteer(Model model){
        model.addAttribute("volunteer",new Volunteer());
        return "volunteer/add";
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
            throw new MajorsACasaException("Ja existeix un voluntari amb el dni "+volunteer.getDni(),"CPduplicada");
        } catch (DataAccessException e) {
            throw new MajorsACasaException(
                    "Error en l'accés a la base de dades", "ErrorAccedintDades");
        }

        return "redirect:list";
    }

    // Cuando le damos al boton editar en la lista, llamamos a este metodo el cual llama a la vista correspondiente
    //      pasandole el objeto voluntario
    @RequestMapping(value="/update/{dni}", method= {RequestMethod.GET, RequestMethod.POST})
    public String updatePassword(Model model, @PathVariable String dni){
        model.addAttribute("volunteer",volunteerDao.getVolunteer(dni));
        return "volunteer/update";
    }

    // Una vez le damos a sumbit se actualiza el voluntario en el dao
    @RequestMapping(value="/update", method = {RequestMethod.GET, RequestMethod.POST})
    public String processUpdateSubmit(
            @ModelAttribute("volunteer") Volunteer volunteer,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "nadador/update";

        volunteerDao.updateVolunteer(volunteer);
        return "redirect:list";
    }

    // Una vez en el listado le damos al boton delete, lo borramos del dao
    @RequestMapping(value="/delete/{dni}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeVolunteer(@PathVariable String dni){
        volunteerDao.removeVolunteer(dni);
        return "redirect:../list";
    }

    //TODO Crear una vista nueva para añadir hobbies y desponibilities



}
