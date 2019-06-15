package ch.patrick.shop.controller;

import ch.patrick.shop.model.Customer;
import ch.patrick.shop.model.request.LoginRequest;
import ch.patrick.shop.service.CustomerService;
import ch.patrick.shop.utils.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Controller für den Login
 *
 * @author Patrick
 * @version 1.0
 */
@Controller()
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {

    /**
     * Service für die Benutzer
     */

    @Autowired
    private CustomerService customerService;

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
     * Zeigt die Loginseite an
     *
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping()
    public String login(Model model){
        model.addAttribute("side", "login");
        return "index";
    }

    /**
     * Überprüft Login Anfragen und setzt Session Variable sofern die Daten korrekt sind
     *
     * @param loginRequest Anfrage für den Login
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @PostMapping()
    public String login(@ModelAttribute @Valid LoginRequest loginRequest, Model model){
        Optional<Customer> customerOptional;
        if(loginRequest.getUser().contains("@")){
            customerOptional = customerService.getByEmail(loginRequest.getUser());
        }else{
            customerOptional = customerService.getByUsername(loginRequest.getUser());
        }
        model.addAttribute("side", "login");
        model.addAttribute("failed", true);
        if(customerOptional.isPresent()){
            if(customerOptional.get().getPassword().equals(Encryptor.hashPassword(loginRequest.getPassword()).get())){
                Customer loggedIn = customerOptional.get();
                model.addAttribute("user", loggedIn);
                model.addAttribute("side", "home");
                model.addAttribute("failed", false);
            }
        }
        return "index";
    }
}
