package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.Binding;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private CompanyDao companyDao;

    @Autowired
    public void setCompanyDao(CompanyDao companyDao){
        this.companyDao=companyDao;
    }

    @RequestMapping(value="/add")
    public String addCompany(Model model){
        model.addAttribute("company",new Company());
        return "/company/register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("company") Company company,
                                   BindingResult bindingResult){
        CompanyVallidator companyVallidator = new CompanyVallidator();
        companyVallidator.validate(company,bindingResult);

        //TODO CAMBIAR ESTO
        if(bindingResult.hasErrors()){
            return "company/register";
        }
        companyDao.addCompany(company);
        return "redirect:/";
    }
}
