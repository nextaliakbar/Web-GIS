package com.web.apps.controller;

import com.web.apps.service.MarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private MarkerService markerService;

    @GetMapping(path = "/")
    public String dashboard(Model model) {
        model.addAttribute("title", "Beranda");
        model.addAttribute("markers", markerService.list());
        return "index";
    }

}
