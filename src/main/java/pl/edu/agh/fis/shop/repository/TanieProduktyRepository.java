package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.TanieProdukty;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na widoku tanie_produkty
 */
public interface TanieProduktyRepository extends JpaRepository<TanieProdukty, Integer> {
}
