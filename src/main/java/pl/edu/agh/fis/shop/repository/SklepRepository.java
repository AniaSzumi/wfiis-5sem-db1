package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Sklep;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na tabeli sklep
 */
public interface SklepRepository extends JpaRepository<Sklep, Integer> {
}
