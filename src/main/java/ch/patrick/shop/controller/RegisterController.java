package ch.patrick.shop.controller;

import ch.patrick.shop.model.Customer;
import ch.patrick.shop.model.request.LoginRequest;
import ch.patrick.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Controller für die Registration
 *
 * @author Patrick
 * @version 1.0
 */
@Controller
@RequestMapping("/register")
@SessionAttributes("user")
public class RegisterController {

    /**
     * Service für die Benutzer
     */

    @Autowired
    private CustomerService customerService;

    /**
     * Setzt den Standard-Wert für den LoginRequest
     *
     * @return LoginRequest
     */

    @ModelAttribute("loginRequest")
    public LoginRequest setupLogin(){
        return new LoginRequest();
    }

    /**
     * Setzt den Standard-Wert für die aktuelle Seite
     *
     * @return aktuelle Seite
     */

    @ModelAttribute("side")
    public String setSide(){
        return "register";
    }

    /**
     * Zeigt das Registrationsformular an
     *
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping()
    public String showRegister(Model model){
        model.addAttribute("customer", new Customer());
        model.addAttribute("side", "register");
        return "index";
    }

    /**
     * Registriert ein Benutzer
     *
     * @param customer eingeloggter Benutzer
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @PostMapping()
    public String registerCustomer(@ModelAttribute @Valid Customer customer, Model model){
        Optional<Customer> optionalCustomer = customerService.getByUsername(customer.getUsername());
        Optional<Customer> optionalCustomerEmail = customerService.getByEmail(customer.getEmail());
        boolean jump = false;
        if(optionalCustomer.isPresent()){
            model.addAttribute("side", "register");
            model.addAttribute("userAlreadyExists", true);
            jump = true;
        }
        if(optionalCustomerEmail.isPresent()){
            model.addAttribute("emailAlreadyExists",true);
            jump = true;
        }
        if(jump){
            return "index";
        }
        customerService.addCustomer(customer);
        model.addAttribute("user", customer);
        model.addAttribute("side", "home");
        return "index";
    }
}
