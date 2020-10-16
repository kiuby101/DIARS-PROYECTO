package com.nagro.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nagro.springboot.app.model.entity.Administrador;
import com.nagro.springboot.app.model.service.IAdministradorService;

@Controller
@RequestMapping("/administracion")
@SessionAttributes("administrador")
public class LoginAdminController {

    @Autowired
    private IAdministradorService administradorService;

    @RequestMapping("/index")
    public String Login(Model model) {
        Administrador administrador = new Administrador();
        model.addAttribute("administrador",administrador);
        return "/administrador/index";
    }

    @PostMapping("/login")
    public String ValidarLogin(Administrador administrador,Model model) {
        if(administradorService.findByMailAndPassword(administrador.getMail(), administrador.getPassword())) {
            return "redirect:../administracion/listarProductos";
        }else {
            return "redirect:../administracion/login";
        }
    }


}