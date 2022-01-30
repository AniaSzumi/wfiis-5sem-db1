package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Klasa mapująca się na tabelę Kaegoria z bazy danych
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kategoria")
    private int id;
    private String nazwa;

    public Kategoria(String nazwa) {
        this.nazwa = nazwa;
    }
}
