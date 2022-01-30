package pl.edu.agh.fis.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Kontroler odpowiadający za dobre routowanie aplikacji frontendowej
 */
@Controller
public class TemplateFile {

    @RequestMapping("/")
    public String welcome() {
        return "index.html";
    }

    @RequestMapping(value={"/klient", "/pracownik", "/manager", "/addWorker", "/addShop", "/register", "/login" })
    public String HomePage() {
        return "forward:/";
    }
}
