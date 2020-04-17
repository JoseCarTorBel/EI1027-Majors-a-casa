package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.model.Company;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.apache.catalina.User;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Random;


@Controller
@RequestMapping("/company")
public class CompanyController {
    private CompanyDao companyDao;

    @Autowired
    public void setCompanyDao(CompanyDao companyDao){
        this.companyDao=companyDao;
    }

    @RequestMapping(value="/register")
    public String addCompany(Model model,HttpSession session){
        if (session.getAttribute("user") == null) {
            model.addAttribute("company",new Company());
            return "company/register";
        }else{
            UserDetails user = (UserDetails) session.getAttribute("user");
            throw  new MajorsACasaException("No pots accedir al login perquè ja has iniciat sessió.","AccesDenied","../"+user.getMainPage());
        }

    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("company") Company company,
                                   BindingResult bindingResult, HttpSession session){

        company.setUsername(company.getCif());
        company.setPasswd(generateRandomPasswd());
        System.out.println("Usuario: "+company.getCif()
                              +"Contraseña: "+company.getPasswd());

        CompanyVallidator companyVallidator = new CompanyVallidator();
        companyVallidator.validate(company,bindingResult);

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "company/register";
        }
        try{
            companyDao.addCompany(company);
        }catch (DuplicateKeyException dk){
            throw new MajorsACasaException("L'empressa ja està registradada.",
                                            "CPCompany Duplicate");
        }catch (DataAccessException ex){
            throw new MajorsACasaException("Error amb l'accés a la BBDD.",
                                            "Error Access BBDD");
        }
        session.setAttribute("registered",company);
        //return "redirect:company/register";
        return "redirect:/";
    }

    @RequestMapping(value="/main")
    public String getCompanyMain(HttpSession session, Model model){
        String sesion = checkSession(model,session);
        if(sesion!=null){ return sesion; }

        UserDetails user =  (UserDetails) session.getAttribute("user");

        if(!user.getRol().equals("Company")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a Company per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());
        }

        // TODO La empresa deberá de tener un correo asignado
        // TODO La empresa no tiene el dni, se supone que es el CIF
        Company company = companyDao.getCompany(user.getDni());

        model.addAttribute("company",company);
        return "company/main";
    }


    @RequestMapping(value = "/update",method=RequestMethod.GET)
    public String update(Model model, HttpSession session){
        String sesion = checkSession(model,session);
        if(sesion!=null){
            return sesion;
        }

        UserDetails user =  (UserDetails) session.getAttribute("user");
        if(!user.getRol().equals("Company")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. Has d'haver iniciat sessió com a Company per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());
        }

        model.addAttribute("company",companyDao.getCompany(user.getDni()));

        return "company/update";
    }

    /**
     * Actualiza la company
     * @param company
     * @param bindingResult
     * @return Página inicial de la companía
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("company") Company company,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "company/update";
        companyDao.updateCompany(company);
        return "redirect:main";
    }





    // *************************
    // Métodos privados ********
    // *************************

    /**
     *  Chequea si la sesión está activa o no
     * @param model
     * @param session
     * @return página donde redirecciona en caso que no esté activa
     */
    private String checkSession(Model model, HttpSession session){
        if(session.getAttribute("user")==null) {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        return null;
    }

    /**
     * Genera contraseña aleatoria proporcionada por el CAS
     * @return Random Password
     */
    private String generateRandomPasswd(){
        Random random = new Random();
        int numeroAleatorio = (int) (Math.random()*25+1);
        char a = (char) (random.nextInt(26) + 'a');
        char b = (char) (random.nextInt(26) + 'a');
        char c = (char) (random.nextInt(26) + 'a');

        String contraseña = ""+numeroAleatorio+""+b+""+c+"";
        return contraseña;
    }

}
