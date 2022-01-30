package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Klasa mapująca się na tabelę Sklep z bazy danych
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sklep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sklep")
    private int id;
    @Column(name = "id_adres")
    private int adresId;
    @Column(name = "id_manager")
    private int managerId;
}
