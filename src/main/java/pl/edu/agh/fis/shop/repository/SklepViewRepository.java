package pl.edu.agh.fis.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.fis.shop.model.SklepView;

import java.util.List;

public interface SklepViewRepository extends JpaRepository<SklepView, Integer> {
    List<SklepView> getAllByManagerId(int managerId);
}
