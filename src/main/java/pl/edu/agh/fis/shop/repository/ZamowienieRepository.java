package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Zamowienie;

public interface ZamowienieRepository extends JpaRepository<Zamowienie, Integer> {
}
