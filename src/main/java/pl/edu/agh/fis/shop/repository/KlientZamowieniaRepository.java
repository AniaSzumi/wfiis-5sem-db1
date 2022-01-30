package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.KlientZamowienia;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na widoku klien_zamowienia
 */
public interface KlientZamowieniaRepository extends JpaRepository<KlientZamowienia, Integer> {
}
