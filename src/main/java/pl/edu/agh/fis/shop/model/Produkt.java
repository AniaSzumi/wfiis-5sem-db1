package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Klasa mapująca się na tabelę Produkt z bazy danych
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produkt")
    private int id;
    private String nazwa;
    @Column(name = "id_kategoria")
    private int kategoriaId;
    private double cena;
}
