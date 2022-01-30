package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Klasa mapująca się na tabelę produkt_sklep z bazy danych
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduktSklep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_produkt")
    private int produktId;
    @Column(name = "id_sklep")
    private int sklepId;
    private int ilosc;
}
