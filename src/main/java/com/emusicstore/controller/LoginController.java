package com.emusicstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by sabin on 3/2/2017.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout",	required = false) String logout, Model model){

        if(error!=null){
          model.addAttribute("error","Invalid UserName and Password");
        }
        if (logout!=null){
            model.addAttribute("msgLogout","You have been logged out successfully");
        }
        return "login";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login?logout";
    }
}
