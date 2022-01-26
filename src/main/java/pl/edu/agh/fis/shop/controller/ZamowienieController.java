package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.model.ProduktSklep;
import pl.edu.agh.fis.shop.model.Sklep;
import pl.edu.agh.fis.shop.model.Zamowienie;
import pl.edu.agh.fis.shop.model.ZamowienieOpis;
import pl.edu.agh.fis.shop.repository.ProduktSklepRepository;
import pl.edu.agh.fis.shop.repository.ZamowienieOpisRepository;
import pl.edu.agh.fis.shop.repository.ZamowienieRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/zamowienie")
@AllArgsConstructor
public class ZamowienieController {
    private final ZamowienieRepository zamowienieRepository;
    private final ZamowienieOpisRepository zamowienieOpisRepository;
    private final ProduktSklepRepository produktSklepRepository;

    @GetMapping
    public List<ZamowienieOpis> getAll() {
        System.out.println(zamowienieOpisRepository.findAll());
        return zamowienieOpisRepository.findAll();
    }

    @GetMapping("/{id}")
    public ZamowienieOpis getByUserId(@PathVariable int id) {
        return zamowienieOpisRepository.findZamowienieOpisByKlientId(id);
    }

    @PostMapping
    public Zamowienie create(@RequestBody Zamowienie zamowienie) {
//        ProduktSklep old = produktSklepRepository.getBySklepIdAndProduktId(zamowienie.getSklepId(), zamowienie.getProduktId());
//        int ilosc = old.getIlosc()- zamowienie.getIlosc();
//        if (ilosc < 0) {
//            ilosc = 0;
//        }
//        old.setIlosc(ilosc);
//        produktSklepRepository.save(old);
        return zamowienieRepository.save(zamowienie);
    }
}
