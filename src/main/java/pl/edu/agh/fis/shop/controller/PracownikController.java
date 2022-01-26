package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.api.LoginForm;
import pl.edu.agh.fis.shop.api.RegistrationForm;
import pl.edu.agh.fis.shop.model.Manager;
import pl.edu.agh.fis.shop.model.Pracownik;
import pl.edu.agh.fis.shop.repository.ManagerRepository;
import pl.edu.agh.fis.shop.repository.PracownikRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pracownik")
@AllArgsConstructor
public class PracownikController {
    private final PracownikRepository pracownikRepository;

    @GetMapping
    public List<Pracownik> getAll() {
        return pracownikRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pracownik getById(@PathVariable int id) {
        return pracownikRepository.findById(id).orElse(new Pracownik());
    }

    @GetMapping("/manager/{id}")
    public List<Pracownik> getByManagerId(@PathVariable int id) {
        return pracownikRepository.findAllByManagerId(id);
    }

    @PostMapping
    public Pracownik create(@RequestBody Pracownik pracownik) {
        return pracownikRepository.save(pracownik);
    }

    @PostMapping("/login")
    public Pracownik login(@RequestBody LoginForm loginForm) {
        return pracownikRepository.findPracownikByEmailAndHaslo(loginForm.getEmail(), loginForm.getPassword());
    }

    @PostMapping("/register")
    public Integer register(@RequestBody RegistrationForm form) {
        var pracownik = pracownikRepository.findPracownikByEmail(form.getEmail());
        if (pracownik != null) {
            return 0;
        }
        var saved = pracownikRepository.save(new Pracownik(
                form.getName(), form.getSurname(), form.getEmail(), form.getPassword(), form.getManagerId(), form.getShopId()));
        return saved.getId();
    }
}
