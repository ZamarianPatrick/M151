package ch.patrick.shop.controller;

import ch.patrick.shop.model.ShopObject;
import ch.patrick.shop.model.request.LoginRequest;
import ch.patrick.shop.service.ShopObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller für die Startseite
 *
 * @author Patrick
 * @version 1.0
 */
@Controller
@SessionAttributes("user")
public class IndexController {

    /**
     * Service für die Inhalte und Artikel
     */

    @Autowired
    private ShopObjectService shopObjectService;

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
     * Zeigt die Startseite an
     *
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping("/")
    public String show(Model model){
        model.addAttribute("side", "home");
        return "index";
    }

    /**
     * Zeigt die Startseite über /home an
     *
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping("/home")
    public String showHome(Model model){
        model.addAttribute("side", "home");
        return "index";
    }

    /**
     * Zeigt die Artikel an
     *
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping("/articles")
    public String showArticles(Model model){
        List<ShopObject> articles = shopObjectService.getAll();
        model.addAttribute("articles", articles);
        model.addAttribute("side", "articles");
        return "index";
    }

    /**
     * Zeigt die Seite für die Passwortwiederherstellung an
     *
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping("/recover")
    public String showRecover(Model model){
        model.addAttribute("side", "recover");
        return "index";
    }
}
