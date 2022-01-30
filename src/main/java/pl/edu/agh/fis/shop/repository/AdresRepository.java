package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Adres;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na tabeli adres
 */
public interface AdresRepository extends JpaRepository<Adres, Integer> {

}
