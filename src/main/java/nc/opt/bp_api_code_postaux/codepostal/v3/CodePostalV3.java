package nc.opt.bp_api_code_postaux.codepostal.v3;

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
@Table(name = "codes_postaux_view")
@AllArgsConstructor
@NoArgsConstructor
public class CodePostalV3 {
    @Id
    private Long id;

    private String commune;

    private String localite;

    private Integer code;

    private Boolean typeDomicile;
    
    private Boolean typeBp;
}
