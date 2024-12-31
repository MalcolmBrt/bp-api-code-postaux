package nc.opt.bp_api_code_postaux.agence;

import jakarta.persistence.Column;
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
@Table(name = "agence")
@AllArgsConstructor
@NoArgsConstructor
public class Agence {
    @Id
    private Long id;

    @Column(name = "nom")
    private String nom;
}
