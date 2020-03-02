package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.VolunteerDao;
import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/volunteer")
public class VolunteerController {

    private VolunteerDao volunteerDao;

    @Autowired
    public void setVolunteerDao(VolunteerDao volunteerDao){
        this.volunteerDao=volunteerDao;
    }

    @RequestMapping("/provaVoluntari")
    public String provaUnNadador(Model model) {
        Volunteer volunteer = volunteerDao.getVolunteer("2091");

        model.addAttribute("volunteer", volunteer);
        return "volunteer/prova_voluntari";
    }




    @RequestMapping("/list")
    public String getVolunteerList(Model model){
        model.addAttribute("volunteers",volunteerDao.getVolunteerList());
        return "volunteer/list";
    }

    @RequestMapping(value="/add")
    public String addVolunteer(Model model){
        model.addAttribute("volunteer",new Volunteer());
        return "volunteer/add";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("volunteer") Volunteer volunteer,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "volunteer/add";
        volunteerDao.addVolunteer(volunteer);
        return "redirect:list";
    }


    @RequestMapping(value="/update/{dni}", method= {RequestMethod.GET, RequestMethod.POST})
    public String updatePassword(Model model, @PathVariable String dni){
        model.addAttribute("volunteer",volunteerDao.getVolunteer(dni));
        return "volunteer/update";
    }

    @RequestMapping(value="/update", method = {RequestMethod.GET, RequestMethod.POST})
    public String processUpdateSubmit(
            @ModelAttribute("volunteer") Volunteer volunteer,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "nadador/update";

        volunteerDao.updatePasswd(volunteer.getDni(), volunteer.getPassword());
        return "redirect:list";
    }

    @RequestMapping(value="/delete/{dni}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeVolunteer(@PathVariable String dni){
        volunteerDao.removeVolunteer(dni);
        return "redirect:../list";
    }
}
