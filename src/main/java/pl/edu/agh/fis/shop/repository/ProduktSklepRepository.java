package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.ProduktSklep;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na tabeli produkt_sklep
 */
public interface ProduktSklepRepository extends JpaRepository<ProduktSklep, Integer> {
    ProduktSklep getBySklepIdAndProduktId(Integer sklepId, Integer produktId);
}
