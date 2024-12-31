package nc.opt.bp_api_code_postaux.boitepostale.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoitePostaleDTO {
    private String nomLocalite;
    private Integer codePostal;
    private String nomAgence;
}
