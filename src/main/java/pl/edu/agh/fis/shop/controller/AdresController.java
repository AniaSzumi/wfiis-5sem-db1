package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.model.Adres;
import pl.edu.agh.fis.shop.repository.AdresRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/adres")
@AllArgsConstructor
public class AdresController {

    private final AdresRepository adresRepository;

    @GetMapping("/")
    public List<Adres> getAll() {
        return adresRepository.findAll();
    }

    @GetMapping("/{id}")
    public Adres getById(@PathVariable int id) {
        return adresRepository.findById(id).orElse(new Adres());
    }

    @PostMapping("/")
    public Integer create(@RequestBody Adres adres) {
        return adresRepository.save(adres).getId();
    }
}
