package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class magasinierController {

    @GetMapping("/admin/produits")
    public String afficherPagemagasinier() {
        return "magasinier"; 
    }
}
