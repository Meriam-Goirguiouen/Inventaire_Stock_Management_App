package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class dashMaController {

    @GetMapping("/magasinier")
    public String home() {
        return "dashMagasinier";
    }
}

