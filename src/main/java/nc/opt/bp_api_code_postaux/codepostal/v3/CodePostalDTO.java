package nc.opt.bp_api_code_postaux.codepostal.v3;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodePostalDTO {
    private Long id;

    private String commune;

    private String localite;

    private Integer code;

    private Boolean typeDomicile;
    
    private Boolean typeBp;

    private List<List<String>> intervalles;
}
