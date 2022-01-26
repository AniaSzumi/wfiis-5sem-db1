package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.model.Kategoria;
import pl.edu.agh.fis.shop.model.ProduktSklepView;
import pl.edu.agh.fis.shop.repository.KategoriaRepository;
import pl.edu.agh.fis.shop.repository.ProduktSklepViewRepository;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/kategoria")
@AllArgsConstructor
public class KategoriaController {

    private final KategoriaRepository kategoriaRepository;
    private final ProduktSklepViewRepository produktSklepViewRepository;

    @GetMapping
    public List<Kategoria> getAll() {
        return kategoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Kategoria getById(@PathVariable int id) {
        return kategoriaRepository.findById(id).orElse(new Kategoria());
    }

    @GetMapping("/sklep/{id}")
    public List<String> getByShopId(@PathVariable String id) {
        return produktSklepViewRepository.findAllBySklepId(Integer.parseInt(id)).stream()
                .map(ProduktSklepView::getKategoria).distinct().collect(Collectors.toList());
    }

    @PostMapping("/")
    public Integer create(@RequestBody Kategoria kategoria) {
        var prev = kategoriaRepository.findKategoriaByNazwa(kategoria.getNazwa());
        if (prev == null) {
            return kategoriaRepository.save(kategoria).getId();
        }
        return 0;
    }
}
