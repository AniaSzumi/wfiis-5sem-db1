package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.model.Kategoria;
import pl.edu.agh.fis.shop.model.ProduktSklepView;
import pl.edu.agh.fis.shop.repository.KategoriaRepository;
import pl.edu.agh.fis.shop.repository.ProduktSklepViewRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Kontroler zajmujący się kategorią produktów
 */
@CrossOrigin
@RestController
@RequestMapping("/api/kategoria")
@AllArgsConstructor
public class KategoriaController {

    private final KategoriaRepository kategoriaRepository;
    private final ProduktSklepViewRepository produktSklepViewRepository;

    /**
     * Funkcja pobierająca wszystkie dostępne kategorie
     * @return wszystkie kategorie
     */
    @GetMapping
    public List<Kategoria> getAll() {
        return kategoriaRepository.findAll();
    }

    /**
     * Funkcja pobierająca wszystkie kategorie produktów dostępnych w danym sklepie
     * @param id id sklepu
     * @return lista kategorii produktów dostępnych w sklepie
     */
    @GetMapping("/sklep/{id}")
    public List<String> getByShopId(@PathVariable String id) {
        return produktSklepViewRepository.findAllBySklepId(Integer.parseInt(id)).stream()
                .map(ProduktSklepView::getKategoria).distinct().collect(Collectors.toList());
    }

    /**
     * Funkcja tworząca nową kategorię
     * @param kategoria kategoria do zapisania na bazie
     * @return indeks zapisanej kategorii jeśli udało się zapisać albo 0 jeśli się nie udało
     */
    @PostMapping("/")
    public Integer create(@RequestBody Kategoria kategoria) {
        var prev = kategoriaRepository.findKategoriaByNazwa(kategoria.getNazwa());
        if (prev == null) {
            return kategoriaRepository.save(kategoria).getId();
        }
        return 0;
    }
}
