package com.sena.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sena.schedule.service.emailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class emailController {

    @Autowired
    private emailService emailService;

    @GetMapping("/basicEmail")
    public String sendBasicMail() {
        emailService.basicMail();
        return "Mail sent successfully";
    }
   
     @GetMapping("/advancedEmail/{email}")
    public String advancedEmail(@PathVariable String email) {
        
        emailService.advancedEmail(email);
        return "Mail sent successfully";
    }


    
}
