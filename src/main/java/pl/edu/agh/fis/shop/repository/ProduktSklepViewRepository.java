package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.ProduktSklepView;

import java.util.List;

/**
 * Klasa do łączenia się z bazą danych i wykonywania operacji na widoku produkty_dla_sklepu
 */
public interface ProduktSklepViewRepository extends JpaRepository<ProduktSklepView, Integer> {
    List<ProduktSklepView> findAllBySklepId(int sklepId);
    List<ProduktSklepView> findAllBySklepIdAndAndKategoria(int sklepId, String kategoria);
}
