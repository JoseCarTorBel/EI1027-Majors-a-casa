package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.model.Company;
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


@Controller
@RequestMapping("/company")
public class CompanyController {
    private CompanyDao companyDao;

    @Autowired
    public void setCompanyDao(CompanyDao companyDao){
        this.companyDao=companyDao;
    }

    @RequestMapping(value="/register")
    public String addCompany(Model model){
        model.addAttribute("company",new Company());
        return "company/register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("company") Company company,
                                   BindingResult bindingResult, HttpSession session){
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
}
