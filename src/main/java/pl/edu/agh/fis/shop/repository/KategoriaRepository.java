package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Kategoria;

import java.util.List;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na tabeli kategoria
 */
public interface KategoriaRepository extends JpaRepository<Kategoria, Integer> {
    Kategoria findKategoriaByNazwa(String nazwa);
}
