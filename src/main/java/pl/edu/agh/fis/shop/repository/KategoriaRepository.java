package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Kategoria;

import java.util.List;

public interface KategoriaRepository extends JpaRepository<Kategoria, Integer> {
    Kategoria findKategoriaByNazwa(String nazwa);
}
