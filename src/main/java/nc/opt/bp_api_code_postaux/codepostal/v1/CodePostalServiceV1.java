package nc.opt.bp_api_code_postaux.codepostal.v1;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostal;
import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostalDTO;
import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostalMapper;
import nc.opt.bp_api_code_postaux.errors.NotFoundException;

@Service
@AllArgsConstructor
public class CodePostalServiceV1 {
    private final CodePostalRepositoryV1 codePostalRepository;

    private final CodePostalMapper codePostalMapper;

    public List<CodePostalDTO> findCodePostalByNomCommune(String nomCommune) {
        List<CodePostal> codesPostaux = codePostalRepository.findCodePostalByNomCommune(nomCommune);
        List<CodePostalDTO> codesPostauxDTO = codePostalMapper.toCodePostalDTOList(codesPostaux);
        if (codesPostauxDTO.isEmpty()) {
            throw new NotFoundException("Aucun code postal ne correspond à cette commune.");
        }
        return codesPostauxDTO;
    }

}
