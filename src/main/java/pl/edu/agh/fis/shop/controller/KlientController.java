package pl.edu.agh.fis.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.shop.api.LoginForm;
import pl.edu.agh.fis.shop.api.RegistrationForm;
import pl.edu.agh.fis.shop.model.Klient;
import pl.edu.agh.fis.shop.repository.KlientRepository;

import java.util.List;

/**
 * Kontroler zajmujący się klientami
 */
@CrossOrigin
@RestController
@RequestMapping("/api/klient")
@AllArgsConstructor
public class KlientController {

    private final KlientRepository klientRepository;

    /**
     * Funkcja pobierająca wszystkich klientów z bazy danych
     * @return lista wszystkich klientów
     */
    @GetMapping
    public List<Klient> getAll() {
        return klientRepository.findAll();
    }

    /**
     * Funkcja pobierająca klienta po id
     * @param id id klienta do znalezienia
     * @return znaleziony klient z bazy danych
     */
    @GetMapping("/{id}")
    public Klient getById(@PathVariable int id) {
        return klientRepository.findById(id).orElse(new Klient());
    }

    /**
     * Funkcja zwracająca klienta po logini i haśle, używana do zalogowania się
     * @param loginForm login i hasło klienta z formularza
     * @return klient znaleziony w bazie danych
     */
    @PostMapping("/login")
    public Klient login(@RequestBody LoginForm loginForm) {
        return klientRepository.findKlientByEmailAndHaslo(loginForm.getEmail(), loginForm.getPassword());
    }

    /**
     * Funkcja tworząca nowego klienta, używana przy rejestracji
     * @param form dane klienta wpisane podczas rejestracji
     * @return id klienta jeśli operacja się udała albo 0 jeśli jest już taki klient
     */
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
