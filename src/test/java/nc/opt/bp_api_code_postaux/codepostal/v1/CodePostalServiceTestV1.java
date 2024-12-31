package nc.opt.bp_api_code_postaux.codepostal.v1;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostal;
import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostalDTO;
import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostalMapper;
import nc.opt.bp_api_code_postaux.errors.NotFoundException;

class CodePostalServiceTestV1 {

    @Mock
    private CodePostalRepositoryV1 codePostalRepository;

    @Mock
    private CodePostalMapper codePostalMapper;

    @InjectMocks
    private CodePostalServiceV1 codePostalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCodePostalByNomCommune_ReturnsCodePostalDTOList() {
        // Préparation des données
        String nomCommune = "NOUMEA";
        CodePostal codePostal = new CodePostal();
        CodePostalDTO codePostalDTO = new CodePostalDTO();
        List<CodePostal> codePostalList = List.of(codePostal);
        List<CodePostalDTO> codePostalDTOList = List.of(codePostalDTO);

        // Configuration des mocks
        when(codePostalRepository.findCodePostalByNomCommune(nomCommune)).thenReturn(codePostalList);
        when(codePostalMapper.toCodePostalDTOList(codePostalList)).thenReturn(codePostalDTOList);

        // Appel de la méthode et vérification du résultat
        List<CodePostalDTO> result = codePostalService.findCodePostalByNomCommune(nomCommune);
        assertEquals(codePostalDTOList, result);
    }

    @Test
    void testFindCodePostalByNomCommune_ThrowsNotFoundException() {
        // Préparation des données
        String nomCommune = "Inconnue";

        // Configuration des mocks pour retourner une liste vide
        when(codePostalRepository.findCodePostalByNomCommune(nomCommune)).thenReturn(Collections.emptyList());
        when(codePostalMapper.toCodePostalDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Vérification que l'exception est levée
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            codePostalService.findCodePostalByNomCommune(nomCommune);
        });
        assertEquals("Aucun code postal ne correspond à cette commune.", exception.getMessage());
    }
}
