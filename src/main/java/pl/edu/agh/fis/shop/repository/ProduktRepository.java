package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Produkt;

public interface ProduktRepository extends JpaRepository<Produkt, Integer> {
    Produkt getProduktByNazwa(String nazwa);
}
