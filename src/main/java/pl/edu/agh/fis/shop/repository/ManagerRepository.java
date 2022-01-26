package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    Manager findManagerByEmail(String email);
    Manager findManagerByEmailAndHaslo(String email, String haslo);
}
