package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Pracownik;

import java.util.List;

public interface PracownikRepository extends JpaRepository<Pracownik, Integer> {
    List<Pracownik> findAllByManagerId(Integer managerId);
    Pracownik findPracownikByEmail(String email);
    Pracownik findPracownikByEmailAndHaslo(String email, String haslo);
}
