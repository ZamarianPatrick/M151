package ch.patrick.shop.controller.admin;

import ch.patrick.shop.model.Customer;
import ch.patrick.shop.model.CustomerType;
import ch.patrick.shop.model.request.*;
import ch.patrick.shop.service.CustomerService;
import ch.patrick.shop.service.ShopObjectService;
import ch.patrick.shop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
@SessionAttributes("user")
public class AdminController {

    /**
     * StorageService für FileUploads
     */

    @Autowired
    public StorageService storageService;

    /**
     * CustomerService für die Benutzer
     */

    @Autowired
    public CustomerService customerService;

    /**
     * ShopObjectService für die Inhalte und Artikel
     */

    @Autowired
    public ShopObjectService shopObjectService;

    /**
     * Setzt den Standard-Wert für den eingeloggten User
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
     * Setzt den zu ladenen Seiteninhalt
     *
     * @return zu ladendes HTML Dokoument
     */

    @ModelAttribute("side")
    public String setSide(){
        return "admin";
    }

    /**
     * Setzt den zu ladenden Seiteninhalt für die Adminseite
     *
     * @return zu ladendes HTML Dokument
     */

    @ModelAttribute("adminContent")
    public String setAdminContent(){
        return "start";
    }

    /**
     * Zeigt die Adminseite an
     *
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping
    public String showAdmin(Model model){
        model.addAttribute("adminContent", "start");
        return "index";
    }

    /**
     * Zeigt die Iconseite des Adminbereiches an
     *
     * @param customer eingeloggter Benuter
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping("/icons")
    public String showIcons(@SessionAttribute(value = "user", required = false) Customer customer, Model model){
        if(customer == null || customer.getCustomerType() != CustomerType.ADMIN){
            return "index";
        }
        Stream<Path> stream = storageService.loadAll();
        List<String> fileNames = new ArrayList<>();
        stream.forEach(path -> {
            fileNames.add(path.getFileName().toString());
        });
        model.addAttribute("files", fileNames);
        model.addAttribute("adminContent", "icons");
        return "index";
    }

    /**
     * Lädt ein Bild hoch
     *
     * @param customer eingeloggter Benutzer
     * @param file hochzuladende Datei
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @PostMapping("/icons/upload")
    public String handleFileUpload(@SessionAttribute(value = "user", required = false) Customer customer,
                                   @RequestParam("pic") MultipartFile file, Model model) {
        if(customer == null || customer.getCustomerType() != CustomerType.ADMIN){
            return "index";
        }
        storageService.store(file);
        model.addAttribute("adminContent", "icons");
        model.addAttribute("forwardIcons", true);
        return "index";
    }

    /**
     * Löscht ein Bild
     *
     * @param customer eingeloggter Benutzer
     * @param fileName Name der zu löschenden Datei
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping("/icons/delete")
    public String deleteFile(@SessionAttribute(value = "user", required = false) Customer customer,
                             @RequestParam("file-name") String fileName, Model model){
        if(customer == null || customer.getCustomerType() != CustomerType.ADMIN){
            return "index";
        }
        Path path = storageService.load(fileName);
        if(path != null){
            try {
                Files.delete(path);
            } catch (IOException e) {}
        }
        model.addAttribute("adminContent", "icons");
        model.addAttribute("forwardIcons", true);
        return "index";
    }
}
