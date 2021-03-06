
package es.uji.ei1027.majorsacasa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.uji.ei1027.majorsacasa.dao.UserDao;
import es.uji.ei1027.majorsacasa.model.UserDetails;

class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return UserDetails.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {
        UserDetails user = (UserDetails) obj;
        if(user.getUsername().trim().equals("")){
            errors.rejectValue("user","Obligatori", "Cal introduir la contrasenya.");
        }

        if(user.getPassword().trim().equals("")){
            errors.rejectValue("password","Obligatori", "Cal introduir la contrasenya.");
        }

    }
}

@Controller
public class LoginController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/login")
    public String login(Model model,HttpSession session) {

        if (session.getAttribute("user") == null) {

            model.addAttribute("user", new UserDetails());
            return "login";
        }else{
            UserDetails user = (UserDetails) session.getAttribute("user");
            throw  new MajorsACasaException("Ja estas logejat","AccesDenied","../"+user.getMainPage());
        }
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") UserDetails user,
                             BindingResult bindingResult, HttpSession session) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }
        // Comprova que el login siga correcte
        // intentant carregar les dades de l'usuari
        user = userDao.loadUserByUsername(user.getUsername(), user.getPassword());

        if (user == null) {
            bindingResult.rejectValue("password", "badpw", "Contrasenya incorrecta o usuari incorrecte");
            return "login";
        }
        // Autenticats correctament.
        // Guardem les dades de l'usuari autenticat a la sessió


        session.setAttribute("user", user);

        // Torna a la pàgina principal dependiendo del rol
        return "redirect:/"+user.getMainPage();
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}


