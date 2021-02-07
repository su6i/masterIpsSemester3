package com.masterips.javaeeproject.controllers.Security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public String login(Model model) {
		
		
//		model.addAllAttributes("user",user);
		return "login/login2";
	}
	
	@GetMapping(value= {"/","/home","/index"})
	public String home() {

		return "navbar";
    }
	
	@GetMapping("/403")
	public String login() {
		
		return "error/403";
	}


    @GetMapping("profile")
	public String profile() {
		return "user/profile";
	}

    @GetMapping("toast")
    public String test(Model model) {
        model.addAttribute("name", "Amir SHIRALIPOUR");

        return "toast";
    }











}
