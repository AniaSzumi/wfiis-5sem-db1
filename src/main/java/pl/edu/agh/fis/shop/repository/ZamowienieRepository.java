package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Zamowienie;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na tabeli zamowienie
 */
public interface ZamowienieRepository extends JpaRepository<Zamowienie, Integer> {
}
