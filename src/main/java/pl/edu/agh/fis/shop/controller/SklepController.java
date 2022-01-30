package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.model.IloscProduktow;
import pl.edu.agh.fis.shop.model.Sklep;
import pl.edu.agh.fis.shop.model.SklepView;
import pl.edu.agh.fis.shop.repository.IloscProduktowRepository;
import pl.edu.agh.fis.shop.repository.SklepRepository;
import pl.edu.agh.fis.shop.repository.SklepViewRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Kontroler zajmujący się sklepami
 */
@CrossOrigin
@RestController
@RequestMapping("/api/sklep")
@AllArgsConstructor
public class SklepController {
    private final SklepRepository sklepRepository;
    private final SklepViewRepository viewRepository;
    private final IloscProduktowRepository iloscProduktowRepository;

    /**
     * Funkcja zwracająca wszystkie sklepy z bazy danych
     * @return lista wszystkich sklepów
     */
    @GetMapping
    public List<String> getAll() {
        return viewRepository.findAll().stream().map(SklepView::toString).collect(Collectors.toList());
    }

    /**
     * Funkcja zwracająca sklepy zarządzane przez danego managera
     * @param id id managera zarządzającego sklepami
     * @return lista sklepów
     */
    @GetMapping("/manager/{id}")
    public List<String> getByManagerId(@PathVariable int id) {
        return viewRepository.getAllByManagerId(id).stream().map(SklepView::toString).collect(Collectors.toList());
    }

    /**
     * Funkcja tworząca nowy sklep
     * @param sklep sklep do zapisania na bazie
     * @return id sklepu jeśli operacja się udałą, 0 jeśli nie
     */
    @PostMapping
    public Integer create(@RequestBody Sklep sklep) {
        return sklepRepository.save(sklep).getId();
    }

    /**
     * Funkcja zwracająca dane z widoku ilosc_produktow pokazująca ile jest produktów w danym sklepie
     * @return lista ilości produktów dla każdego ze sklepów
     */
    @GetMapping("/produkty")
    public List<IloscProduktow> amountOfProducts() {
        return iloscProduktowRepository.findAll();
    }
}
