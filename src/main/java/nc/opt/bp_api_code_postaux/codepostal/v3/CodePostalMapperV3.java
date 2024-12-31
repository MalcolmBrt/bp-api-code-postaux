package nc.opt.bp_api_code_postaux.codepostal.v3;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CodePostalMapperV3 {
    public CodePostalDTO toCodePostalDTO(CodePostalV3 codePostal, List<List<String>> intervalles) {
        CodePostalDTO dto = new CodePostalDTO();
        dto.setId(codePostal.getId());
        dto.setCommune(codePostal.getCommune());
        dto.setLocalite(codePostal.getLocalite());
        dto.setCode(codePostal.getCode());
        dto.setTypeDomicile(codePostal.getTypeDomicile());
        dto.setTypeBp(codePostal.getTypeBp());
        dto.setIntervalles(intervalles);
        return dto;
    }
}
