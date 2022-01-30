package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Klasa mapująca się na tabelę Zamowienie z bazy danych
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zamowienie")
    private int id;
    @Column(name = "id_klient")
    private int klientId;
    @Column(name = "id_sklep")
    private int sklepId;
    @Column(name = "id_produkt")
    private int produktId;
    private int ilosc;
    private double suma;
}
