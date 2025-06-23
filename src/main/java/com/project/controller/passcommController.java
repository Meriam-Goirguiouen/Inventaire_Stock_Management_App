package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class passcommController {

    @GetMapping("/magasinier/commandes")
    public String home() {
        return "passeComm";
    }
}
