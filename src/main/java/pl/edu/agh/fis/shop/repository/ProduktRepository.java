package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Produkt;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na tabeli produkt
 */
public interface ProduktRepository extends JpaRepository<Produkt, Integer> {
    Produkt getProduktByNazwa(String nazwa);
}
