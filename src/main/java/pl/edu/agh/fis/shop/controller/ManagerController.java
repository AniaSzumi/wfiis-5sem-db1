package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.api.LoginForm;
import pl.edu.agh.fis.shop.api.RegistrationForm;
import pl.edu.agh.fis.shop.model.Klient;
import pl.edu.agh.fis.shop.model.Manager;
import pl.edu.agh.fis.shop.repository.ManagerRepository;

import java.util.List;

/**
 * Kontroler zajmujący się managerami
 */
@CrossOrigin
@RestController
@RequestMapping("/api/manager")
@AllArgsConstructor
public class ManagerController {

    private final ManagerRepository managerRepository;

    /**
     * Funkcja znajdująca wszystkich managerów
     * @return lista wszystkich managerów
     */
    @GetMapping("/")
    public List<Manager> getAll() {
        return managerRepository.findAll();
    }

    /**
     * Funkcja znajdująca na bazie danych managera po danym id
     * @param id id managera do znalezienia
     * @return znaleziony manager
     */
    @GetMapping("/{id}")
    public Manager getById(@PathVariable int id) {
        return managerRepository.findById(id).orElse(new Manager());
    }

    /**
     * Funkcja szukająca managera na bazie po emailu i haśle, używana przy zalogowaniu
     * @param loginForm email i hasło wpisane przy logowaniu
     * @return znaleziony manager
     */
    @PostMapping("/login")
    public Manager login(@RequestBody LoginForm loginForm) {
        return managerRepository.findManagerByEmailAndHaslo(loginForm.getEmail(), loginForm.getPassword());
    }

    /**
     * Funkcja dodająca nowego managera do bazy, używana przy rejestracji
     * @param form dane managera wpisane przy rejestracji
     * @return id managera przy udanym zapisie do bazy, w przciwnym razie 0
     */
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
