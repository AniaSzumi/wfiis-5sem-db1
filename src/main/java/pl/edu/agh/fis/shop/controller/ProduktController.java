package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.model.Produkt;
import pl.edu.agh.fis.shop.model.ProduktSklep;
import pl.edu.agh.fis.shop.model.ProduktSklepView;
import pl.edu.agh.fis.shop.repository.ProduktRepository;
import pl.edu.agh.fis.shop.repository.ProduktSklepRepository;
import pl.edu.agh.fis.shop.repository.ProduktSklepViewRepository;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/produkt")
@AllArgsConstructor
public class ProduktController {
    private final ProduktRepository produktRepository;
    private final ProduktSklepRepository produktSklepRepository;
    private final ProduktSklepViewRepository sklepRepository;

    @GetMapping
    public List<Produkt> getAll() {
        return produktRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produkt getById(@PathVariable int id) {
        return produktRepository.findById(id).orElse(new Produkt());
    }

    @GetMapping("/kategoria")
    public List<ProduktSklepView> getByShopIdAndCategory(@RequestParam int sklepId, @RequestParam String kategoria) {
        return sklepRepository.findAllBySklepIdAndAndKategoria(sklepId, kategoria);
    }

    @GetMapping("/sklep/{id}")
    public List<ProduktSklepView> getBySklepId(@PathVariable int id) {
        return sklepRepository.findAllBySklepId(id);
    }

    @PostMapping("/sklep")
    public Integer addToShop(@RequestBody ProduktSklep produktSklep) {
        var ps = produktSklepRepository.getBySklepIdAndProduktId(produktSklep.getSklepId(), produktSklep.getProduktId());
        if (ps != null) {
            ps.setIlosc(ps.getIlosc()+produktSklep.getIlosc());
            return produktSklepRepository.save(ps).getId();
        }
        return produktSklepRepository.save(produktSklep).getId();
    }

    @PostMapping
    public Integer create(@RequestBody Produkt produkt) {
        var prev = produktRepository.getProduktByNazwa(produkt.getNazwa());
        if (prev == null) {
            return produktRepository.save(produkt).getId();
        }
        return 0;
    }
}
