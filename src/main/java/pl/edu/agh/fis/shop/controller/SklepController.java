package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.model.Sklep;
import pl.edu.agh.fis.shop.model.SklepView;
import pl.edu.agh.fis.shop.repository.SklepRepository;
import pl.edu.agh.fis.shop.repository.SklepViewRepository;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/sklep")
@AllArgsConstructor
public class SklepController {
    private final SklepRepository sklepRepository;
    private final SklepViewRepository viewRepository;

    @GetMapping
    public List<String> getAll() {
        return viewRepository.findAll().stream().map(SklepView::toString).collect(Collectors.toList());
    }

    @GetMapping("/manager/{id}")
    public List<String> getByManagerId(@PathVariable int id) {
        return viewRepository.getAllByManagerId(id).stream().map(SklepView::toString).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Sklep getById(@PathVariable int id) {
        return sklepRepository.findById(id).orElse(new Sklep());
    }

    @PostMapping
    public Integer create(@RequestBody Sklep sklep) {
        return sklepRepository.save(sklep).getId();
    }
}
