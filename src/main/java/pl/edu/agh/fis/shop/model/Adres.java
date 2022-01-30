package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Klasa mapująca się na tabelę Adres z bazy danych
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adres")
    private int id;
    private String kraj;
    private String miasto;
    private String ulica;
    private String numer;
}
