package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Klient;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na tabeli klient
 */
public interface KlientRepository extends JpaRepository<Klient, Integer> {
    Klient findKlientByEmail(String email);
    Klient findKlientByEmailAndHaslo(String email, String haslo);
}
