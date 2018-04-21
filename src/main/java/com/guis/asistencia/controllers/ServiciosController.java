package com.guis.asistencia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guis.asistencia.constants.ViewConstants;

@Controller
@RequestMapping("/servicios")
public class ServiciosController {
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("selectNavItem", "servicios");
		return ViewConstants.SERVICIOS_INDEX;
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("selectNavItem", "servicios");
		return ViewConstants.SERVICIOS_CREATE;
	}
}
