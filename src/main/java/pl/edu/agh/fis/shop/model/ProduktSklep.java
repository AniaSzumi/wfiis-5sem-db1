package pl.edu.agh.fis.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
//@Table(name = )
//@Subselect("select gen_random_uuid() as id, ps.* from bd1.produkt_sklep ps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduktSklep {
    @Id
//    @Type(type="org.hibernate.type.UUIDCharType")
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "id_produkt")
    private int produktId;
    @Column(name = "id_sklep")
    private int sklepId;
    private int ilosc;
}
