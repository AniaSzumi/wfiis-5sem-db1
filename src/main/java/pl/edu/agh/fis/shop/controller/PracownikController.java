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

/**
 * Kontroler zajmujący się pracownikani
 */
@CrossOrigin
@RestController
@RequestMapping("/api/pracownik")
@AllArgsConstructor
public class PracownikController {
    private final PracownikRepository pracownikRepository;

    /**
     * Funkcja zwracająca wszystkich pracowników z bazy
     * @return lista wszystkich pracowników
     */
    @GetMapping
    public List<Pracownik> getAll() {
        return pracownikRepository.findAll();
    }

    /**
     * Funkcja zwracająca danego pracownika po id z bazy
     * @param id id pracownika do wyszukania
     * @return znaleziony pracownik
     */
    @GetMapping("/{id}")
    public Pracownik getById(@PathVariable int id) {
        return pracownikRepository.findById(id).orElse(new Pracownik());
    }

    /**
     * Funkcja znajdująca wszystkich pracowników których zatrudnił manager o danym id
     * @param id id managera
     * @return lista pracowników zatrudnionych przez danego managera
     */
    @GetMapping("/manager/{id}")
    public List<Pracownik> getByManagerId(@PathVariable int id) {
        return pracownikRepository.findAllByManagerId(id);
    }

    /**
     * Funkcja zwracająca pracownika z bazy po emailu i haśle, używana przy logowaniu
     * @param loginForm email i hasło wpisane przy logowaniu
     * @return znaleziony pracownik
     */
    @PostMapping("/login")
    public Pracownik login(@RequestBody LoginForm loginForm) {
        return pracownikRepository.findPracownikByEmailAndHaslo(loginForm.getEmail(), loginForm.getPassword());
    }

    /**
     * Funkcja tworząca nowego pracownika
     * @param form dane pracownika wpisane przy rejestracji
     * @return id pracownika jeśli udało się go zapisać, 0 jeśli nie
     */
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
