package ch.patrick.shop.controller.admin;

import ch.patrick.shop.model.Customer;
import ch.patrick.shop.model.CustomerType;
import ch.patrick.shop.model.request.ChangeUserGroupRequest;
import ch.patrick.shop.model.request.ChangeUsersGroupRequest;
import ch.patrick.shop.model.request.LoginRequest;
import ch.patrick.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controller für die Benutzerverwaltung
 *
 * @author Patrick
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/users")
@SessionAttributes("user")
public class AdminUserController {

    /**
     * Service für die Benutzer
     */

    @Autowired
    public CustomerService customerService;

    /**
     * Setzt den Standard-Wert für den eingeloggten Benutzer
     *
     * @return Null
     */

    @ModelAttribute("user")
    public Customer setUser(){
        return null;
    }

    /**
     * Setzt den zu ladenden Inhalt für die Adminseite
     *
     * @return zu ladender Inhalt für die Adminseite
     */

    @ModelAttribute("adminContent")
    public String setAdminContent(){
        return "users";
    }

    /**
     * Setzt den zu ladenden Inhalt
     *
     * @return Name des zu ladenden HTML Dokumentes
     */

    @ModelAttribute("side")
    public String setSide(){
        return "admin";
    }

    /**
     * Legt den Standard-Wert für den LoginRequest fest
     *
     * @return LoginRequest
     */

    @ModelAttribute("loginRequest")
    public LoginRequest setLoginRequest(){
        return new LoginRequest();
    }

    /**
     * Zeigt die registrierten Benutzer an
     *
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping
    public String showUsers(Model model){
        List<Customer> users = customerService.getAll();
        ChangeUsersGroupRequest changeUsersGroupRequest = new ChangeUsersGroupRequest();
        for(Customer user : users){
            ChangeUserGroupRequest request = new ChangeUserGroupRequest(user.getId(), user.getCustomerType());
            changeUsersGroupRequest.add(request);
        }
        model.addAttribute("users", users);
        model.addAttribute("changeUsers", changeUsersGroupRequest);
        return "index";
    }

    /**
     * Bearbeitet die Benutzer
     *
     * @param user eingeloggter Benutzer, welche die Benutzer bearbeiten will
     * @param requests Anfrage, welche vom Benutzer getätigt wurde
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @PostMapping
    public String editUsers(@SessionAttribute("user") Customer user, @ModelAttribute("changeUsers") @Valid ChangeUsersGroupRequest requests, Model model){
        if(user.getCustomerType() != CustomerType.ADMIN){
            return "index";
        }
        for(ChangeUserGroupRequest request : requests.getRequests()){
            Optional<Customer> optionalCustomer = customerService.getById(request.getId());
            if(optionalCustomer.isPresent()){
                Customer customer = optionalCustomer.get();
                if(customer.getCustomerType().equals(request.getGroup()) == false){
                    customer.setCustomerType(request.getGroup());
                    customerService.updateCustomer(customer);
                }
            }
        }
        List<Customer> users = customerService.getAll();
        model.addAttribute("users", users);
        return "index";
    }

}
