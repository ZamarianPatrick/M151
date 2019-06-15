package ch.patrick.shop.controller;

import ch.patrick.shop.model.request.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

/**
 * Controller für den Logout
 *
 * @author Patrick
 * @version 1.0
 */
@Controller
@RequestMapping("/logout")
@SessionAttributes({"user", "order"})
public class LogoutController {

    /**
     * Legt den Standard-Wert für den LoginRequest fest
     *
     * @return LoginRequest
     */

    @ModelAttribute("loginRequest")
    public LoginRequest setupLogin(){
        return new LoginRequest();
    }

    /**
     * Loggt den Benutzer aus
     *
     * @param session Session mit den Sessionvariabeln
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping
    public String logout(HttpSession session, Model model){
        session.removeAttribute("user");
        session.removeAttribute("order");
        model.addAttribute("user", null);
        model.addAttribute("order", null);
        model.addAttribute("side", "home");
        return "index";
    }

}
