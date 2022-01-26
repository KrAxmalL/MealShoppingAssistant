package ua.edu.ukma.interpreters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@GetMapping(path="/")
	public String home() {
		System.out.println("Controller works!");
		return "index.html";
	}
	
}