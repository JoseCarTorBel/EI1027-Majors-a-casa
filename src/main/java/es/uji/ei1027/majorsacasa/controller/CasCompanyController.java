package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.dao.ContractDao;
import es.uji.ei1027.majorsacasa.model.Company;
import es.uji.ei1027.majorsacasa.model.Contract;
import es.uji.ei1027.majorsacasa.model.UserDetails;
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
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/cascompany")
public class CasCompanyController {

    private CompanyDao companyDao;
    private ContractDao contractDao;

    @Autowired
    public void setCompanyDao(CompanyDao companyDao){
        this.companyDao=companyDao;
    }

    @Autowired
    public void setContractDao(ContractDao contractDao) { this.contractDao=contractDao;}

    @RequestMapping(value="/newCompany")
    public String addCompany(Model model, HttpSession session){
        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }
        model.addAttribute("company",new Company());
        return "cascompany/newCompany";
    }

    /*-------------------------------*/

    /**
     * Dona d'alta una empressa com a responsable de contractació.
     * @param company       Empressa nova
     * @param bindingResult
     * @return Inicio si es correcto, sinó errores.
     */
    @RequestMapping(value = "/newCompany",method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("company") Company company,
                                   BindingResult bindingResult){

        company.setUsername(company.getCif());
        company.setPasswd(generateRandomPasswd());
        company.setUsername(generateRandomUser(company));

        System.out.println("Usuario: "+company.getCif()
                            +"Contraseña: "+company.getPasswd());

        CompanyVallidator companyVallidator = new CompanyVallidator();
        companyVallidator.validate(company,bindingResult);

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "cascompany/newCompany";
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
       // session.setAttribute("company",company);
        //return "redirect:company/register";
        return "redirect:list";
    }



    //----------------------------------------------------------------------


    @RequestMapping(value="/list")
    public String getCompanys(HttpSession session, Model model) {
        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }

        List<Company> listCompany = companyDao.getCompanys();
        model.addAttribute("listCompanys",listCompany);
        return "cascompany/list";
    }


    @RequestMapping(value="/delete/{cif}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeVolunteer(@PathVariable String cif){
        companyDao.removeCompany(cif);
        return "redirect:../list";
    }


    /**
     *
     * @param session
     * @param model
     * @return Devulve si la sesión y el usuario es el que corresponde para añadir el contrato.
     */
    @RequestMapping(value = "/newContract")
    public String newContract(HttpSession session, Model model){

        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }
        model.addAttribute("newContract",new Contract());
        return "cascompany/newContract";
    }

    /**
     * Añadir contracto a la BBDD.
     * @param contract
     * @param bindingResult
     * @return Devulve la página principal si se ha podido añadir a la BBDD
     */
    @RequestMapping(value = "/newContract",method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("contract")Contract contract,
                                   BindingResult bindingResult){

        ContractValidator contractValidator = new ContractValidator();
        contractValidator.validate(contract,bindingResult);

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "cascompany/newContract";
        }

        try{
            contractDao.addContract(contract);
        }catch (DuplicateKeyException dk){
            throw new MajorsACasaException("El contracte ja ha sigut fet.",
                                            "CPCompany Duplicate");
        }catch (DataAccessException ex){
            throw new MajorsACasaException( "Error amb l'accés a la BBDD.",
                                            "Error Access BBDD");
        }
        return "redirect:list";
    }


    /**
     *
     * @param session   Sesión actual
     * @param model     Modelo
     * @return  Vista de lista de contractos que tiene la empresa.
     */
    @RequestMapping(value = "/listContracts")
    public String getListContracts(HttpSession session, Model model){
        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }

        List<Contract> contractVigente = contractDao.getListContractVigente();
        List<Contract> contractPasados = contractDao.getListContractPasados();

        model.addAttribute("contractVigente",contractVigente);
        model.addAttribute("contractPasados",contractPasados);

        return "cascompany/listContracts";
    }











/** ___________________________________________________________________________________________________________________

    /******************************/
    /** private methods************/
    /******************************/

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
        checkRol(session);
        return null;
    }


    /**
     *Mira si el usuario actual puede o no acceder
     * @param session Sesion acutual
     */
    private void checkRol(HttpSession session){
        UserDetails user = (UserDetails) session.getAttribute("user");
        if(!user.getRol().equals("ResponsableContratacion")){
            System.out.println("El usuario no puede acceder a esta pagina con este rol");
            throw  new MajorsACasaException("No tens permisos per accedir a aquesta pàgina. " +
                    "Has d'haver iniciat sessió com a Company per a poder accedir-hi.","AccesDenied","../"+user.getMainPage());
        }
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

        String contraseña = ""+numeroAleatorio+""+a+""+b+""+c+"";
        return contraseña;
    }

    /**
     * Genera usuario a partir de las últimas 3 posiciones del CIF más la primera palabra del nombre de la empresa
     * @param company   Empresa a crear
     * @return  Devuelve el nombre usuario
     */
    private String generateRandomUser(Company company){
        String subcif = company.getCif().substring(company.getCif().length()-3);
        String name = company.getName().split(" ")[0];
        String user =subcif+""+name;
        return user;

    }
}
