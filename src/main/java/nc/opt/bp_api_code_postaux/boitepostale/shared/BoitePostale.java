package nc.opt.bp_api_code_postaux.boitepostale.shared;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostal;

@Entity
@Getter
@Setter
@Table(name = "produit")
@AllArgsConstructor
@NoArgsConstructor
public class BoitePostale {
    @Id
    private Long id;

    @Column(name = "num_bp")
    private String numBP;

    @ManyToOne
    @JoinColumn(name = "code_postal_id")
    private CodePostal codePostal;
}
