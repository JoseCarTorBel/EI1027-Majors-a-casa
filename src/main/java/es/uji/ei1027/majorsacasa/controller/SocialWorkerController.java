package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.ElderlyPeopleDao;
import es.uji.ei1027.majorsacasa.dao.SocialWorkerDao;
import es.uji.ei1027.majorsacasa.model.ElderlyPeople;
import es.uji.ei1027.majorsacasa.model.SocialWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/socialWorker")
public class SocialWorkerController {
//TODO no funciona todavia
    private SocialWorkerDao socialWorkerDao;

    @Autowired
    public void setSocialWorkerDao(SocialWorkerDao socialWorkerDao){
        this.socialWorkerDao=socialWorkerDao;
    }


    // Probar a mostrar la informacion de 1 socialworker
    @RequestMapping("/provaSocialWorker")
    public String provaUnSocialWorker(Model model) {

        SocialWorker socialWorker = socialWorkerDao.getSocialWorker("20914826Y");

        model.addAttribute("socialworker", socialWorker);
        return "socialWorker/prova_elderlyPeople"; //todo A cambiar cuando se definan vistas
    }


    // Llama a la vista socialworker pasandole el listado completo de socialworker
    @RequestMapping("/list")
    public String getSocialWorkerList(Model model){
        model.addAttribute("socials",socialWorkerDao.getSocialWorkerList());
        return "socialWorker/list";
    }

    // LLama a la vista pasandole un objeto socialworker
    @RequestMapping(value="/add")
    public String addSocialWorker(Model model){
        model.addAttribute("socialWorker",new SocialWorker());
        return "socialWorker/add";
    }

    // Una vez le damos al sumbit la vista devuelve el objeto socialworker con todos los atributos
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("socialWorker") SocialWorker socialWorker,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "socialWorker/add";
        socialWorkerDao.addSocialWorker(socialWorker);
        return "redirect:list";
    }

    // Cuando le damos al boton editar en la lista, llamamos a este metodo el cual llama a la vista correspondiente
    //      pasandole el objeto socialWorker
    @RequestMapping(value="/update/{dni}", method= {RequestMethod.GET, RequestMethod.POST})
    public String updatePassword(Model model, @PathVariable String dni){
        model.addAttribute("socialWorker",socialWorkerDao.getSocialWorker(dni));
        return "socialWorker/update";
    }

    // Una vez le damos a sumbit se actualiza el socialWorker en el dao
    @RequestMapping(value="/update", method = {RequestMethod.GET, RequestMethod.POST})
    public String processUpdateSubmit(
            @ModelAttribute("socialWorker") SocialWorker socialWorker,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "socialWorker/update";

        socialWorkerDao.updateSocialWorker(socialWorker);
        return "redirect:list";
    }

    // Una vez en el listado le damos al boton delete, lo borramos del dao
    @RequestMapping(value="/delete/{dni}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeSocialWorker(@PathVariable String dni){
        socialWorkerDao.removeSocialWorker(dni);
        return "redirect:../list";
    }



}
