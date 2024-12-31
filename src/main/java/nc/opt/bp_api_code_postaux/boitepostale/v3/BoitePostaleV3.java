package nc.opt.bp_api_code_postaux.boitepostale.v3;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "boites_postales_view")
@AllArgsConstructor
@NoArgsConstructor
public class BoitePostaleV3 {
    @Id
    private Long id;

    private String agence;

    private String numero;

    private Integer codePostal;

    private String localite;
}
