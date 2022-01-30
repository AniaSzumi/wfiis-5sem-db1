package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Klasa mapująca się na tabelę Pracownik z bazy danych
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pracownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pracownik")
    private int id;
    private String imie;
    private String nazwisko;
    private String email;
    private String haslo;
    @Column(name = "id_manager")
    private int managerId;
    @Column(name = "id_sklep")
    private int sklepId;

    public Pracownik(String imie, String nazwisko, String email, String haslo, int managerId, int sklepId) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.haslo = haslo;
        this.managerId = managerId;
        this.sklepId = sklepId;
    }
}
