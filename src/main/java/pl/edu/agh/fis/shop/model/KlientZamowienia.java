package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * Klasa mapująca się na widok klient_zamowienia z bazy danych, pokazuje podsmowanie wysztskich zamówień dla klienta
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KlientZamowienia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_klient")
    private int id;
    private int zamowienia;
    private int produkty;
    private double cena;
}
