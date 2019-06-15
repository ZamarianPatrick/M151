package ch.patrick.shop.controller;

import ch.patrick.shop.model.*;
import ch.patrick.shop.model.request.EditOrderPositionRequest;
import ch.patrick.shop.model.request.LoginRequest;
import ch.patrick.shop.service.ArticleService;
import ch.patrick.shop.service.OrderService;
import ch.patrick.shop.service.ShopObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller für die Bestellungen
 *
 * @author Patrick
 * @version 1.0
 */
@Controller
@RequestMapping("/order")
@SessionAttributes({"user", "order"})
public class OrderController {

    /**
     * Service für die Artikel
     */

    @Autowired
    public ArticleService articleService;

    /**
     * Service für die Inhalte und Artikel
     */

    @Autowired
    public ShopObjectService shopObjectService;

    /**
     * Service für die Bestellungen
     */

    @Autowired
    public OrderService orderService;

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
     * Legt den Standard-Wert für die Bestellungen fest
     *
     * @param user eingeloggter Benutzer
     * @return NULL wenn der Benutzer nicht eingeloggt ist, ansonsten neue Bestellung
     */

    @ModelAttribute("order")
    public Order setOrder(@SessionAttribute(value = "user", required = false) Customer user){
        if(user != null){
            Order order = new Order();
            order.setCustomer(user);
            return order;
        }
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
     * Setzt den Standard-Wert für die zu ladende Seite
     *
     * @return Name der zu ladenden Seite
     */

    @ModelAttribute("side")
    public String setSide(){
        return "order";
    }

    /**
     * Zeigt die Bestellung an
     *
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping
    public String showOrder(Model model){
        model.addAttribute("articles", articleService.getAll());
        return "index";
    }

    /**
     * Fügt der Bestellung ein Artikel hinzu
     *
     * @param order aktuelle Bestellung
     * @param id Id des Artikels
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @RequestMapping(method = RequestMethod.POST)
    public String addArticle(@ModelAttribute("order") Order order, @RequestParam("article-id") Long id, Model model){
        if(order == null){
            return "index";
        }
        Optional<Article> optionalArticle = articleService.getById(id);
        if(optionalArticle.isPresent()){
            Article article = optionalArticle.get();
            OrderPosition orderPosition = new OrderPosition();
            orderPosition.setOrder(order);
            orderPosition.setArticle(article);
            order.getOrderPositions().add(orderPosition);
        }
        model.addAttribute("forwardOrder", true);
        return "index";
    }

    /**
     * Zeigt die Bearbeitungsseite für die Inhalte eines Artikels an
     *
     * @param order aktuelle Bestellung
     * @param id Id des zu bearbeitenden Artikels
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping("/edit")
    public String showEditArticle(@SessionAttribute("order") Order order, @RequestParam("id") Integer id, Model model){
        if(order == null){
            return "index";
        }
        if(order.getOrderPositions().size() > id){
            if(order.getOrderPositions().get(id) != null){
                OrderPosition orderPosition = order.getOrderPositions().get(id);
                EditOrderPositionRequest editOrderPositionRequest = new EditOrderPositionRequest();
                editOrderPositionRequest.setArticle(orderPosition.getArticle());
                for(ShopObject shopObject : orderPosition.getItems()){
                    editOrderPositionRequest.getSelectedContents().add(shopObject.getId());
                }
                editOrderPositionRequest.setIndex(id);
                model.addAttribute("editOrderPosition", editOrderPositionRequest);
            }
        }
        model.addAttribute("side", "order-edit");
        return "index";
    }

    /**
     * Speichert die bearbeiteten Inhalte eines Artikels
     *
     * @param order aktuelle Bestellung
     * @param editOrderPositionRequest Anfrage für die Bearbeitung
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @PostMapping("/edit")
    public String editArticle(@SessionAttribute("order") Order order, @ModelAttribute("orderPosition") EditOrderPositionRequest editOrderPositionRequest, Model model){
        if(order == null){
            return "index";
        }
        OrderPosition orderPosition = order.getOrderPositions().get(editOrderPositionRequest.getIndex());
        orderPosition.setItems(new ArrayList<>());
        for(Long itemId : editOrderPositionRequest.getSelectedContents()){
            Optional<ShopObject> optionalShopObject = shopObjectService.getById(itemId);
            if(optionalShopObject.isPresent()){
                ShopObject shopObject = optionalShopObject.get();
                orderPosition.getItems().add(shopObject);
            }
        }
        model.addAttribute("side", "order-edit");
        model.addAttribute("forwardOrder", true);
        return "index";
    }

    /**
     * Löscht einen Artikel aus der Bestellung
     *
     * @param order aktuelle Bestellung
     * @param id Id des zu löschenden Artikels
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping("/delete")
    public String deleteArticle(@SessionAttribute("order") Order order, @RequestParam int id, Model model){
        if(order == null){
            return "index";
        }
        if(order.getOrderPositions().size() > id){
            if(order.getOrderPositions().get(id) != null){
                order.getOrderPositions().remove(id);
            }
        }
        model.addAttribute("forwardOrder", true);
        return "index";
    }

    /**
     * Zeigt die Bestelldaten an
     *
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping("/data")
    public String showOrderData(Model model){
        model.addAttribute("side", "order-data");
        return "index";
    }

    /**
     * Schickt eine Bestellung ab
     *
     * @param order Bestellung die abgeschickt wird
     * @param httpSession Session mit den Variabeln
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping("/submit")
    public String submitOrder(@SessionAttribute(value = "order", required = false) Order order, HttpSession httpSession, Model model){
        if(order == null){
            model.addAttribute("side", "home");
            return "index";
        }
        model.addAttribute("side", "order-submit");
        if(order.getCustomer() == null
                || order.getOrderPositions().size() == 0){
            return "index";
        }
        order.setOrderDate(LocalDate.now());
        orderService.save(order);
        httpSession.removeAttribute("order");
        model.addAttribute("order", null);
        return "index";
    }

    /**
     * Löscht die Bestellung
     *
     * @param customer eingeloggter Benutzer
     * @param httpSession Session mit den Variabeln
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping("/destroy")
    public String destroyOrder(@SessionAttribute(value = "user", required = false) Customer customer, HttpSession httpSession, Model model){
        if(customer == null){
            model.addAttribute("side", "home");
            return "index";
        }
        httpSession.removeAttribute("order");
        model.addAttribute("order", null);
        model.addAttribute("forwardOrder", true);
        model.addAttribute("side", "order");
        return "index";
    }

    /**
     * Zeigt alle Bestellungen des Benutzers an
     *
     * @param customer eingeloggter Benutzer
     * @param model Model für die Attribute
     * @return Name des HTML Dokumentes
     */

    @GetMapping("/info")
    public String showInfo(@SessionAttribute(value = "user", required = false) Customer customer, Model model){
        if(customer == null){
            model.addAttribute("side", "order-info");
            return "index";
        }
        model.addAttribute("orders", orderService.getOrders(customer));
        model.addAttribute("side", "order-info");
        return "index";
   }
}
