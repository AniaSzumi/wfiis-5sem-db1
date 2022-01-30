package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.model.KlientZamowienia;
import pl.edu.agh.fis.shop.model.ProduktSklep;
import pl.edu.agh.fis.shop.model.Zamowienie;
import pl.edu.agh.fis.shop.model.ZamowienieOpis;
import pl.edu.agh.fis.shop.repository.KlientZamowieniaRepository;
import pl.edu.agh.fis.shop.repository.ProduktSklepRepository;
import pl.edu.agh.fis.shop.repository.ZamowienieOpisRepository;
import pl.edu.agh.fis.shop.repository.ZamowienieRepository;

import java.util.List;

/**
 * Kontroler zarządzający zamówieniami
 */
@CrossOrigin
@RestController
@RequestMapping("/api/zamowienie")
@AllArgsConstructor
public class ZamowienieController {
    private final ZamowienieRepository zamowienieRepository;
    private final ZamowienieOpisRepository zamowienieOpisRepository;
    private final ProduktSklepRepository produktSklepRepository;
    private final KlientZamowieniaRepository klientZamowieniaRepository;


    /**
     * Funkcja zwracająca wszystkie zamówienia z bazy danych
     * @return lista wszystkich zamówień
     */
    @GetMapping
    public List<ZamowienieOpis> getAll() {
        System.out.println(zamowienieOpisRepository.findAll());
        return zamowienieOpisRepository.findAll();
    }

    /**
     * Funkcja pobierająca wszystkie zamówienia danego klienta
     * @param id id klienta
     * @return lista zamówień danego klienta
     */
    @GetMapping("/{id}")
    public List<ZamowienieOpis> getByUserId(@PathVariable int id) {
        return zamowienieOpisRepository.findAllByKlientId(id);
    }

    /**
     * Funkcja pobierająca dane z widoku klient_zamowienia dostarczająca podsumowanie zamówień
     * @param id id klienta
     * @return podsumowanie zamówień danego klienta
     */
    @GetMapping("/summary/{id}")
    public KlientZamowienia summary(@PathVariable int id) {
        return klientZamowieniaRepository.findById(id).orElse(null);
    }

    /**
     * Funkcja tworząca nowe zamówienie
     * @param zamowienie dane zamówienia przesłąne z formularza
     * @return id utworzonego zamówienia jeśli dodanie do bazy się powiodło, 0 jeśli nie i -1 jeśli był błąd na bazie danych
     */
    @PostMapping
    public Integer create(@RequestBody Zamowienie zamowienie) {
        ProduktSklep old = produktSklepRepository.getBySklepIdAndProduktId(zamowienie.getSklepId(), zamowienie.getProduktId());
        int ilosc = old.getIlosc()- zamowienie.getIlosc();
        if (ilosc < 0) {
            ilosc = 0;
        }
        old.setIlosc(ilosc);
        produktSklepRepository.save(old);
        int id = 0;
        if (zamowienie.getIlosc() == 0) {
            return -1;
        }
        try {
            id = zamowienieRepository.save(zamowienie).getId();
        } catch (DataAccessException e) {
            return -1;
        }
        return id;
    }
}
