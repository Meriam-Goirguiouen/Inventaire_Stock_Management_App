

package com.example.inventaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/accueil")
    public String home() {
        return "accueil";
    }
}
