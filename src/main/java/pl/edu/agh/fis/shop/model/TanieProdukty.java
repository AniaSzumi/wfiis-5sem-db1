package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Klasa mapująca się na widok tanie_produkty z bazy danych, pokazuje ilość produktów z ceną mniejszą niż 15 zł w danej kategorii
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TanieProdukty {
    @Id
    @Column(name = "id_kategoria")
    private int id;
    private String nazwa;
    private int produkty;
}
