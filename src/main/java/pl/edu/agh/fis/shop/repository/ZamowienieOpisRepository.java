package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.ZamowienieOpis;

import java.util.List;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na widoki zamowienie_opis
 */
public interface ZamowienieOpisRepository extends JpaRepository<ZamowienieOpis, Integer> {
    List<ZamowienieOpis> findAllByKlientId(Integer id);
}
