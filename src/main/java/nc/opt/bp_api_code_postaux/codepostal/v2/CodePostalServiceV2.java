package nc.opt.bp_api_code_postaux.codepostal.v2;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostal;
import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostalDTO;
import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostalMapper;
import nc.opt.bp_api_code_postaux.errors.NotFoundException;

@Service()
@AllArgsConstructor
public class CodePostalServiceV2 {
    private final CodePostalRepositoryV2 codePostalRepository;

    private final CodePostalMapper codePostalMapper;

    public List<CodePostalDTO> findCodePostalByNomCommune(String nomCommune) {
        CodePostalSpecification spec = new CodePostalSpecification("nomCommune", nomCommune);

        List<CodePostal> codesPostaux = codePostalRepository.findAll(spec);
        List<CodePostalDTO> codesPostauxDTO = codePostalMapper.toCodePostalDTOList(codesPostaux);
        if (codesPostauxDTO.isEmpty()) {
            throw new NotFoundException("Aucun code postal ne correspond à cette commune.");
        }
        return codesPostauxDTO;
    }

    public List<String> getCommunes() {
        List<String> communes = codePostalRepository.getCommunes();
        return communes;
    }
}
