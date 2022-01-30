package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.model.Produkt;
import pl.edu.agh.fis.shop.model.ProduktSklep;
import pl.edu.agh.fis.shop.model.ProduktSklepView;
import pl.edu.agh.fis.shop.model.TanieProdukty;
import pl.edu.agh.fis.shop.repository.ProduktRepository;
import pl.edu.agh.fis.shop.repository.ProduktSklepRepository;
import pl.edu.agh.fis.shop.repository.ProduktSklepViewRepository;
import pl.edu.agh.fis.shop.repository.TanieProduktyRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Kontroler zajmujący się produktami
 */
@CrossOrigin
@RestController
@RequestMapping("/api/produkt")
@AllArgsConstructor
public class ProduktController {
    private final ProduktRepository produktRepository;
    private final ProduktSklepRepository produktSklepRepository;
    private final ProduktSklepViewRepository sklepRepository;
    private final TanieProduktyRepository tanieProduktyRepository;

    /**
     * Funkcja zwracająca wszystkie produkty z bazy
     * @return lista wszystkich produktów
     */
    @GetMapping
    public List<Produkt> getAll() {
        return produktRepository.findAll();
    }

    /**
     * Funkcja zwracająca listę produktów z danej kategorii w danym sklepie
     * @param sklepId id sklepu
     * @param kategoria kategoria produktów
     * @return lista produktów
     */
    @GetMapping("/kategoria")
    public List<ProduktSklepView> getByShopIdAndCategory(@RequestParam int sklepId, @RequestParam String kategoria) {
        return sklepRepository.findAllBySklepIdAndAndKategoria(sklepId, kategoria);
    }

    /**
     * Funkcja zwracająca listę produktów w danym sklepie
     * @param id id sklepu
     * @return lista produktów w danym sklepie
     */
    @GetMapping("/sklep/{id}")
    public List<ProduktSklepView> getBySklepId(@PathVariable int id) {
        return sklepRepository.findAllBySklepId(id).stream().filter(produktSklepView -> produktSklepView.getIlosc() > 0).collect(Collectors.toList());
    }

    /**
     * Funkcja dodająca dany produkt do sklepu
     * @param produktSklep dane produktu i sklepu oraz ilość produktu wpisane w formularzu
     * @return id inwentarza jeśli zapisanie na bazie się powiodło, 0 jeśli nie
     */
    @PostMapping("/sklep")
    public Integer addToShop(@RequestBody ProduktSklep produktSklep) {
        var ps = produktSklepRepository.getBySklepIdAndProduktId(produktSklep.getSklepId(), produktSklep.getProduktId());
        if (ps != null) {
            ps.setIlosc(ps.getIlosc()+produktSklep.getIlosc());
            return produktSklepRepository.save(ps).getId();
        }
        return produktSklepRepository.save(produktSklep).getId();
    }

    /**
     * Funkcja tworząca nowy produkt
     * @param produkt produkt do zapisania na bazie danych
     * @return id produktu jeśli się udało, 0 jeśli nie lub -1 jeśli wystąpił błąd na bazie
     */
    @PostMapping
    public Integer create(@RequestBody Produkt produkt) {
        var prev = produktRepository.getProduktByNazwa(produkt.getNazwa());
        int id = 0;
        if (prev == null) {
            try {
                id = produktRepository.save(produkt).getId();
            } catch (DataAccessException e) {
                return -1;
            }
        }
        return id;
    }

    /**
     * Funkcja zwracająca listę najtańszych produktów z widoku tanie_produkty
     * @return ilość tańszych niż 15 zł dla danej kategorii
     */
    @GetMapping("/cheapest")
    public List<TanieProdukty> getCheapest() {
        return tanieProduktyRepository.findAll();
    }
}
