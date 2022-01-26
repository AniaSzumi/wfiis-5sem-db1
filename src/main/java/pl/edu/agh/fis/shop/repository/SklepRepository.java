package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Sklep;

public interface SklepRepository extends JpaRepository<Sklep, Integer> {
}
