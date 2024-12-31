package nc.opt.bp_api_code_postaux.codepostal.shared;

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
import nc.opt.bp_api_code_postaux.agence.Agence;

@Entity
@Getter
@Setter
@Table(name = "code_postal")
@AllArgsConstructor
@NoArgsConstructor
public class CodePostal {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "localite")
    private String nomCommune;

    @Column(name = "code", length = 5, unique = true)
    private Integer codePostal;

    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agence agence;
}
