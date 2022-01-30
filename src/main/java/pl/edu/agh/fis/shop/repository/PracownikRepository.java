package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Pracownik;

import java.util.List;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na tabeli pracownik
 */
public interface PracownikRepository extends JpaRepository<Pracownik, Integer> {
    List<Pracownik> findAllByManagerId(Integer managerId);
    Pracownik findPracownikByEmail(String email);
    Pracownik findPracownikByEmailAndHaslo(String email, String haslo);
}
