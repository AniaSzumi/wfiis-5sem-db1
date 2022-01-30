package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Klasa mapująca się na widok zamowienie_opis z bazy danych, dostarcza wszystkich danych tekstowych w zamówieniu
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZamowienieOpis {
    @Id
    @Column(name = "id_zamowienie")
    private int id;
    @Column(name = "id_klient")
    private int klientId;
    private String nazwa;
    private double cena;
    private int ilosc;
    @Column(name = "id_sklep")
    private int sklepId;
    private String kraj;
    private String miasto;
    private String ulica;
    private String numer;
}
