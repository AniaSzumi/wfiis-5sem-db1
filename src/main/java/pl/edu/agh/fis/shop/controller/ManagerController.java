package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.api.LoginForm;
import pl.edu.agh.fis.shop.api.RegistrationForm;
import pl.edu.agh.fis.shop.model.Klient;
import pl.edu.agh.fis.shop.model.Manager;
import pl.edu.agh.fis.shop.repository.ManagerRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {

    private final ManagerRepository managerRepository;

    @GetMapping("/")
    public List<Manager> getAll() {
        return managerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Manager getById(@PathVariable int id) {
        return managerRepository.findById(id).orElse(new Manager());
    }

    @PostMapping("/")
    public Manager create(@RequestBody Manager manager) {
        return managerRepository.save(manager);
    }

    @PostMapping("/login")
    public Manager login(@RequestBody LoginForm loginForm) {
        return managerRepository.findManagerByEmailAndHaslo(loginForm.getEmail(), loginForm.getPassword());
    }

    @PostMapping("/register")
    public Integer register(@RequestBody RegistrationForm form) {
        var manager = managerRepository.findManagerByEmail(form.getEmail());
        if (manager != null) {
            return 0;
        }
        var saved = managerRepository.save(new Manager(form.getName(), form.getSurname(), form.getEmail(), form.getPassword()));
        return saved.getId();
    }
}
