package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Klasa mapująca się na widok sklep_adres z bazy danych, pokazuje sklep łącznie z jego adresem
 */
@Entity
@Table(name = "sklep_adres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SklepView {
    @Id
    @Column(name = "id_sklep")
    private int sklepId;
    @Column(name = "id_adres")
    private int adresId;
    @Column(name = "id_manager")
    private int managerId;
    private String kraj;
    private String miasto;
    private String ulica;
    private String numer;

    @Override
    public String toString() {
        return sklepId + ". " + kraj + ", " + miasto + " " + ulica  + " " + numer;
    }
}
