package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class commandeController {

    @GetMapping("/admin/commandes")
    public String commande() {
        return "commande"; 
    }

    
}