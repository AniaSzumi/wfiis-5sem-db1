package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;


/**
 * Klasa mapująca się na widok produkty_dla_sklepu z bazy danych, pokazuje produkty w danym sklepie z ich opisem słownym
 */
@Entity
@Table(name = "`produkty_dla_sklepu`")
@Immutable
@Subselect("select gen_random_uuid() as id, ps.* from bd1.produkty_dla_sklepu ps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduktSklepView {
    @Id
    private String id;
    @Column(name = "id_sklep")
    private int sklepId;
    @Column(name = "id_produkt")
    private int produktId;
    private String nazwa;
    private String kategoria;
    private double cena;
    private int ilosc;

    @Override
    public String toString() {
        return nazwa;
    }
}
