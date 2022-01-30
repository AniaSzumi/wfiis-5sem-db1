package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.IloscProduktow;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na widoku ilosc_produktow
 */
public interface IloscProduktowRepository extends JpaRepository<IloscProduktow, Integer> {
}
