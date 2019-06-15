package ch.patrick.shop.controller;

import ch.patrick.shop.model.Customer;
import ch.patrick.shop.model.request.*;
import ch.patrick.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller für die Benutzerinfos
 *
 * @author Patrick
 * @version 1.0
 */
@Controller
@RequestMapping("/userInfo")
@SessionAttributes("user")
public class UserInfoController {

    /**
     * Service für die Benutzer
     */

    @Autowired
    private CustomerService customerService;

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
     * Setzt den Standard-Wert für den LoginRequest
     *
     * @return LoginRequest
     */

    @ModelAttribute("loginRequest")
    public LoginRequest setLoginRequest(){
        return new LoginRequest();
    }

    /**
     * Setzt den Standard-Wert für die aktuelle Seite
     *
     * @return userinfo
     */

    @ModelAttribute("side")
    public String setSide(){
        return "userinfo";
    }

    /**
     * Zeigt die Benutzerinfos an
     *
     * @param customer eingeloggter Benutzer
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping
    public String showUserInfo(@ModelAttribute("user") Customer customer, Model model){
        if(customer == null){
            model.addAttribute("user", null);
        }else {
            addAttributes(model, customer);
        }
        return "index";
    }

    /**
     * Ändert den Namen des Benutzers
     *
     * @param customer eingeloggter Benutzer
     * @param changeNameRequest Anfrage für die Namensänderung
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @PostMapping("/changeName")
    public String changeName(@ModelAttribute("user") Customer customer,
                             @ModelAttribute("changeName") @Valid ChangeNameRequest changeNameRequest,
                             Model model){
        customer.setFirstname(changeNameRequest.getFirstname());
        customer.setSurname(changeNameRequest.getSurname());
        customerService.updateCustomer(customer);
        addAttributes(model, customer);
        model.addAttribute("forwardUserInfo", true);
        return "index";
    }

    /**
     * Ändert die Email Adresse
     *
     * @param customer eingeloggter Benutzer
     * @param changeEmailRequest Anfrage für die Email Änderung
     * @param model Model für die Attribute
     * @return Name des HTML Dokuments
     */

    @PostMapping("/changeEmail")
    public String changeEmail(@ModelAttribute("user") Customer customer,
                              @ModelAttribute("changeEmail") @Valid ChangeEmailRequest changeEmailRequest,
                              Model model){
        customer.setEmail(changeEmailRequest.getEmail());
        customerService.updateCustomer(customer);
        addAttributes(model, customer);
        model.addAttribute("forwardUserInfo", true);
        return "index";
    }

    /**
     * Ändert die Adresse
     *
     * @param customer eingeloggter Benutzer
     * @param changeAddressRequest Anfrage für die Adresse Änderung
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @PostMapping("/changeAddress")
    public String changeAddress(@ModelAttribute("user") Customer customer,
                                @ModelAttribute("changeAddress")ChangeAddressRequest changeAddressRequest,
                                Model model){
        customer.getAddress().setStreet(changeAddressRequest.getStreet());
        customer.getAddress().getCity().setName(changeAddressRequest.getCity());
        customer.getAddress().getCity().setZip(changeAddressRequest.getZip());
        customerService.updateCustomer(customer);
        addAttributes(model, customer);
        model.addAttribute("forwardUserInfo", true);
        return "index";
    }

    /**
     * Ändert die Telefonnummer
     *
     * @param customer eingeloggter Benutzer
     * @param changeTelNumberRequest Anfrage für die Änderung der Telefonnummer
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @PostMapping("/changeTel")
    public String changeTel(@ModelAttribute("user") Customer customer,
                            @ModelAttribute("changeTel") ChangeTelNumberRequest changeTelNumberRequest,
                            Model model){
        customer.setTelephonNumber(changeTelNumberRequest.getNumber());
        customerService.updateCustomer(customer);
        addAttributes(model, customer);
        model.addAttribute("forwardUserInfo", true);
        return "index";
    }

    /**
     * Fügt Standard-Attribute hinzu
     *
     * @param model Model, welchem Werte hinzugefügt werden sollen
     * @param customer eingeloggter Benutzer
     */

    public void addAttributes(Model model, Customer customer){
        model.addAttribute("changeEmail", new ChangeEmailRequest(customer.getEmail()));
        model.addAttribute("changeName", new ChangeNameRequest(customer.getFirstname(), customer.getSurname()));
        model.addAttribute("changeAddress", new ChangeAddressRequest(
                customer.getAddress().getStreet(),
                customer.getAddress().getCity().getName(),
                customer.getAddress().getCity().getZip())
        );
        model.addAttribute("changeTel", new ChangeTelNumberRequest(customer.getTelephonNumber()));
        model.addAttribute("side", "userinfo");
    }

}
