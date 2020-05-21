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
import sun.text.resources.en.FormatData_en_IN;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
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


    @RequestMapping(value="/main")
    public String getCompanyMain(HttpSession session, Model model){
        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }

        return "cascompany/main";
    }


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


    @RequestMapping(value="unregisterContract/{codContract}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String unsubscribeContract(@PathVariable String codContract,Model model){

        if(!contractDao.unsubscribeContract(codContract)){
            MajorsACasaException exection = new MajorsACasaException("No pots donar de baixa un contracte que tinga clients asignats",
                    "Contracte no donat de baixa.");

            List<Company> listCompany = companyDao.getCompanys();
            model.addAttribute("listCompanys",listCompany);

            exection.setReturnPath("/cascompany/listContracts");
            throw exection;
        }

        List<Company> listCompany = companyDao.getCompanys();
        model.addAttribute("listCompanys",listCompany);

        return "redirect:../listContracts";
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
    public String processAddSubmit(@ModelAttribute("newContract")Contract contract,
                                   BindingResult bindingResult){

        ContractValidator contractValidator = new ContractValidator();
        contractValidator.validate(contract,bindingResult);

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "cascompany/newContract";
        }

        try{
            contractDao.addContract(contract);
        }catch (DuplicateKeyException dk) {
            throw new MajorsACasaException("El contracte ja ha sigut fet.",
                    "CPCompany Duplicate");
        }catch (DataAccessException ex) {
            MajorsACasaException exection = new MajorsACasaException("No pots crear un contracte sense que l'empressa es done d'alta",
                    "Empressa no donada d'alta");
            exection.setReturnPath("/cascompany/newContract");
            throw exection;
        }
        return "redirect:listContracts";
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

    /**
     *
     * @param model
     * @Modelo utilizado
     * @return Vista para listar contratos de cateriong
     */
    @RequestMapping(value ="/listContractsCatering")
    public String getListContractsCatering(HttpSession session, Model model) {
        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }
        List<Contract> contractVigente = contractDao.getListContractVigente(0);
        List<Contract> contractPasados = contractDao.getListContractPasados(0);

        model.addAttribute("contractVigente",contractVigente);
        model.addAttribute("contractPasados",contractPasados);

        return "cascompany/listContracts";
    }

    /**
     *
     * @param session   Sesion usuario
     * @param model  utilizado
     * @return Vista para listar contratos de limpieza
     */
    @RequestMapping(value ="/listContractsClean")
    public String getListContractsClean(HttpSession session, Model model) {
        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }
        List<Contract> contractVigente = contractDao.getListContractVigente(1);
        List<Contract> contractPasados = contractDao.getListContractPasados(1);

        model.addAttribute("contractVigente",contractVigente);
        model.addAttribute("contractPasados",contractPasados);

        return "cascompany/listContracts";
    }

    /**
     *
     * @param session   Sesion actual
     * @param model     Modelo utilizado
     * @return Vista para listar contratos de salud
     */
    @RequestMapping(value ="/listContractsHealth")
    public String getListContractsHealth(HttpSession session, Model model) {
        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }
        List<Contract> contractVigente = contractDao.getListContractVigente(2);
        List<Contract> contractPasados = contractDao.getListContractPasados(2);

        model.addAttribute("contractVigente",contractVigente);
        model.addAttribute("contractPasados",contractPasados);

        return "cascompany/listContracts";
    }


    /**
     * Da la información extendida de la empresa.
     * @param cif   Código de la empresa
     * @param session   Sesion user
     * @param model Modelo
     * @return Página de mostrar información
     */
    @RequestMapping(value="/infoCompany/{cif}")
    public String getInfoCompany(@PathVariable String cif, HttpSession session, Model model){
        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }

        Company company =  companyDao.getCompany(cif);

        model.addAttribute("company",company);

        return "cascompany/infoCompany";
    }


    @RequestMapping(value = "/update/{cif}",method=RequestMethod.GET)
    public String update(@PathVariable String cif, Model model, HttpSession session){
        String isSession = checkSession(model,session);
        if(isSession!=null){ return isSession; }
        UserDetails user =  (UserDetails) session.getAttribute("user");

        model.addAttribute("company",companyDao.getCompany(cif));
        return "cascompany/update";
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
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "cascompany/update";
        }
        companyDao.updateCompany(company);
        return "cascompany/update";
    }

    @RequestMapping(value="/contractsCompany/{cif}")
    public String getInfoContractCompany(@PathVariable String cif, HttpSession session, Model model) {
        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }

        Contract currentContract = companyDao.getCurrentContract(cif);
        List<Contract> pastContract = companyDao.getPastContracts(cif);
        Company company = companyDao.getCompany(cif);

        model.addAttribute("company",company);
        model.addAttribute("contractCurrent",currentContract);
        model.addAttribute("contractsPast",pastContract);

        if(currentContract == null && pastContract.size()==0){
            throw new MajorsACasaException("Aquesta empressa no té contractes amb la GVA." +
                    "L'empressa està donada d'alta però no té cap contracte.","NoResults","../list");
        }
        return "cascompany/contractsCompany";
    }


    @RequestMapping(value="unregisterContractCompany/{codContract}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String unsubscribeContractCompany(@PathVariable String codContract,Model model,HttpSession session){
        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }
        String cif = contractDao.getContract(codContract).getCifcompany();

        if(!contractDao.unsubscribeContract(codContract)){
            MajorsACasaException exection = new MajorsACasaException("No pots donar de baixa un contracte que tinga clients asignats",
                    "Contracte no donat de baixa.");
//
//            List<Company> listCompany = companyDao.getCompanys();
//            model.addAttribute("listCompanys",listCompany);
            Company company = companyDao.getCompanyWithContract(codContract);
            exection.setReturnPath("/cascompany/contractsCompany/"+company.getCif());

            throw exection;
        }

        Contract currentContract = companyDao.getCurrentContract(cif);
        List<Contract> pastContract = companyDao.getPastContracts(cif);
        Company company = companyDao.getCompany(cif);

        model.addAttribute("company",company);
        model.addAttribute("contractCurrent",currentContract);
        model.addAttribute("contractsPast",pastContract);

        if(currentContract == null && pastContract.size()==0){
            throw new MajorsACasaException("Aquesta empressa no té contractes amb la GVA." +
                    "L'empressa està donada d'alta però no té cap contracte.","NoResults","../list");
        }
        return "cascompany/contractsCompany";
    }

    @RequestMapping(value="/contract/{codcontract}")
    public String getContract(@PathVariable String codcontract, HttpSession session, Model model) {
        String isSession = checkSession(model, session);
        if (isSession != null) {
            return isSession;
        }

        Contract contract = companyDao.getContract(codcontract);
        Company company = companyDao.getCompany(contract.getCifcompany());

        if(contract.getFinalDate().isAfter(LocalDate.now())){
            model.addAttribute("esActual",true);
        }else{
            model.addAttribute("esActual",false);
        }

        model.addAttribute("company",company);
        model.addAttribute("contract",contract);

        return "cascompany/contract";
    }




// ** ___________________________________________________________________________________________________________________

    // *****************************/
    // private methods************/
    //****************************/

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
