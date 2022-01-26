package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.api.LoginForm;
import pl.edu.agh.fis.shop.api.RegistrationForm;
import pl.edu.agh.fis.shop.model.Kategoria;
import pl.edu.agh.fis.shop.model.Klient;
import pl.edu.agh.fis.shop.repository.KlientRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/klient")
@AllArgsConstructor
public class KlientController {

    private final KlientRepository klientRepository;

    @GetMapping
    public List<Klient> getAll() {
        return klientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Klient getById(@PathVariable int id) {
        return klientRepository.findById(id).orElse(new Klient());
    }

    @PostMapping
    public Klient create(@RequestBody Klient klient) {
        return klientRepository.save(klient);
    }

    @PostMapping("/login")
    public Klient login(@RequestBody LoginForm loginForm) {
        return klientRepository.findKlientByEmailAndHaslo(loginForm.getEmail(), loginForm.getPassword());
    }

    @PostMapping("/register")
    public Integer register(@RequestBody RegistrationForm form) {
        var klient = klientRepository.findKlientByEmail(form.getEmail());
        if (klient != null) {
            return 0;
        }
        var saved = klientRepository.save(new Klient(form.getName(), form.getSurname(), form.getEmail(), form.getPassword()));
        return saved.getId();
    }
}
