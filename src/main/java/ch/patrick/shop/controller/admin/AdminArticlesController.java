package ch.patrick.shop.controller.admin;

import ch.patrick.shop.model.Article;
import ch.patrick.shop.model.Customer;
import ch.patrick.shop.model.CustomerType;
import ch.patrick.shop.model.ShopObject;
import ch.patrick.shop.model.request.AddArticleRequest;
import ch.patrick.shop.model.request.EditArticleRequest;
import ch.patrick.shop.model.request.LoginRequest;
import ch.patrick.shop.service.ShopObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Patrick
 * @version 1.0
 * @since 15.06.2019
 */
@Controller
@RequestMapping("/admin/articles")
@SessionAttributes("user")
public class AdminArticlesController {

    /**
     * Service für Shop Objekte
     */
    @Autowired
    public ShopObjectService shopObjectService;

    /**
     * Setzt den Standard-Wert für die Session Variable User
     *
     * @return Benutzer, welcher eingeloggt ist, null wenn keiner eingeloggt ist
     */
    @ModelAttribute("user")
    public Customer setUser(){
        return null;
    }


    /**
     * Setzt den Standard-Wert für die Session Varible side
     *
     * @return Seite, Welche in das index.html reingeladen wird
     */
    @ModelAttribute("side")
    public String setSide(){
        return "admin";
    }

    /**
     * Setzt den Standard-Wert für die Login Abfrage
     *
     * @return Login Abfrage
     */
    @ModelAttribute("loginRequest")
    public LoginRequest setLoginRequest(){
        return new LoginRequest();
    }

    /**
     * Setzt den Standard-Wert für den Admin Inhalt
     *
     * @return Amdmin Inhalt
     */
    @ModelAttribute("adminContent")
    public String setAdminContent(){
        return "start";
    }

    /**
     * Setzt den Standard-Wert für den AddArticleRequest
     *
     * @return AddArticleRequest
     */

    @ModelAttribute("addArticleRequest")
    public AddArticleRequest setAddArticleRequest(){
        return new AddArticleRequest();
    }

    /**
     * Setzt den Standard-Wert für den EditArticleRequest
     *
     * @return EditArticleRequest
     */

    @ModelAttribute("editArticleRequest")
    public EditArticleRequest setEditArticleRequest(){
        return new EditArticleRequest();
    }

    /**
     * Zeigt die Artikel an
     *
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping
    public String showArticles(Model model){
        List<ShopObject> articles = shopObjectService.getAll();
        model.addAttribute("articles", articles);
        model.addAttribute("adminContent", "articles");
        model.addAttribute("addArticleRequest", new AddArticleRequest());
        return "index";
    }

    /**
     * Fügt ein Artikel hinzu
     *
     * @param customer eingeloggter User, welcher ein Artikel hinzufügen will
     * @param request Anfrage mit den Daten, welche der User getätigt hat
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @PostMapping("/add")
    public String addArticle(@SessionAttribute("user") Customer customer, @ModelAttribute("addArticleRequest") @Valid AddArticleRequest request, Model model){
        if(customer.getCustomerType() != CustomerType.ADMIN){
            return "index";
        }
        ShopObject object;
        if(request.getIsPackage()){
            object = new Article();
            setAvailableItems(request, object);
        }else{
            object = new ShopObject();
        }
        object.setIcon(request.getIcon());
        object.setName(request.getName());
        object.setPrice(request.getPrice());
        shopObjectService.addShopObject(object);
        model.addAttribute("adminContent", "articles");
        model.addAttribute("forwardArticles", true);
        return "index";
    }

    /**
     * Bearbeitet ein Artikel
     *
     * @param customer eingeloggter User, welcher den Artikel bearbeiten will
     * @param request Anfrage mit den Daten, welche der User getätigt hat
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @PostMapping("/edit")
    public String editArticle(@SessionAttribute("user") Customer customer, @ModelAttribute @Valid EditArticleRequest request, Model model){
        if(customer.getCustomerType() != CustomerType.ADMIN){
            return "index";
        }
        ShopObject object;
        if(request.getIsPackage()){
            object = new Article();
            setAvailableItems(request, object);
        }else{
            object = new ShopObject();
        }
        object.setPrice(request.getPrice());
        object.setIcon(request.getIcon());
        object.setName(request.getName());
        object.setId(request.getId());
        shopObjectService.addShopObject(object);
        model.addAttribute("adminContent", "articles");
        model.addAttribute("forwardArticles", true);
        return "index";
    }

    /**
     * Löscht einen Artikel
     *
     * @param customer eingeloggter User, welcher den Artikel löschen will
     * @param model Model für die Attribute
     * @param ids Array von Artikel ids, welche gelöscht werden sollen
     * @return Name des HTML Dokumentes
     */

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public String deleteArticles(@SessionAttribute("user") Customer customer, Model model, @RequestParam(value = "ids") Long... ids){
        if(customer.getCustomerType() != CustomerType.ADMIN){
            return "index";
        }
        for(Long id : ids){
            Optional<ShopObject> optionalShopObject = shopObjectService.getById(id);
            if(optionalShopObject.isPresent()){
                ShopObject shopObject = optionalShopObject.get();
                shopObjectService.deleteShopObject(shopObject);
            }
        }
        model.addAttribute("adminContent", "articles");
        model.addAttribute("forwardArticles", true);
        return "index";
    }

    /**
     * Setzt die verfügbaren Inhalte eines Artikels
     *
     * @param request Anfrage, welche getätigt wurde
     * @param object ShopObject, auf welchem die verfügbaren Inhalte gesetzt werden sollen
     */

    private void setAvailableItems(AddArticleRequest request, ShopObject object){
        for(Long articleId : request.getAvailableItems()){
            Optional<ShopObject> optionalShopObject = shopObjectService.getById(articleId);
            if(optionalShopObject.isPresent()){
                ShopObject shopObject = optionalShopObject.get();
                ((Article)object).getAvailableItems().add(shopObject);
            }
        }
    }

}
