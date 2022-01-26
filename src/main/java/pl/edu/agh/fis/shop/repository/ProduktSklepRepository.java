package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.ProduktSklep;

public interface ProduktSklepRepository extends JpaRepository<ProduktSklep, Integer> {
    ProduktSklep getBySklepIdAndProduktId(Integer sklepId, Integer produktId);
}
