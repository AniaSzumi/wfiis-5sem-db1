package pl.edu.agh.fis.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.agh.fis.shop.model.Kategoria;
import pl.edu.agh.fis.shop.repository.KategoriaRepository;

import java.util.List;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

//    @Autowired
//    KategoriaRepository kategoriaRepository;
//
//    @Override
//    public void run(String... strings) throws Exception {
//        kategoriaRepository.save(new Kategoria("RTV & AGD"));
//        List<Kategoria> kategorie = kategoriaRepository.findAll();
//        for(Kategoria k : kategorie) {
//            System.out.println(k.getNazwa());
//        }
//    }

}
