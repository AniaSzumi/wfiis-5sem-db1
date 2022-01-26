package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.ZamowienieOpis;

public interface ZamowienieOpisRepository extends JpaRepository<ZamowienieOpis, Integer> {
    ZamowienieOpis findZamowienieOpisByKlientId(Integer id);
}
