package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Klasa mapująca się na widok ilosc_produktow z bazy danych, pokazuje ile jest produktów w danym sklepie
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IloscProduktow {
    @Id
    @Column(name = "id_sklep")
    private int id;
    private String kraj;
    private String miasto;
    private String ulica;
    private String numer;
    private int produkty;
}
