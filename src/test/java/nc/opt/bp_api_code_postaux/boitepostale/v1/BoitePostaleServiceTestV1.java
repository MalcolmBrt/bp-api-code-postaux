package nc.opt.bp_api_code_postaux.boitepostale.v1;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import nc.opt.bp_api_code_postaux.errors.NotFoundException;

import nc.opt.bp_api_code_postaux.boitepostale.shared.BoitePostale;
import nc.opt.bp_api_code_postaux.boitepostale.shared.BoitePostaleDTO;
import nc.opt.bp_api_code_postaux.boitepostale.shared.BoitePostaleMapper;
import nc.opt.bp_api_code_postaux.boitepostale.shared.BoitePostaleRepository;
import nc.opt.bp_api_code_postaux.boitepostale.shared.BoitePostaleService;

class BoitePostaleServiceTestV1 {

    @Mock
    private BoitePostaleRepository boitePostaleRepository;

    @Mock
    private BoitePostaleMapper boitePostaleMapper;

    @InjectMocks
    private BoitePostaleService boitePostaleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindBoitesPostalesByNumBP_ReturnsBoitePostaleDTOPage() {
        // Préparation des données
        String numeroBP = "12345";
        Pageable pageable = PageRequest.of(0, 10);
        BoitePostale boitePostale = new BoitePostale();
        BoitePostaleDTO boitePostaleDTO = new BoitePostaleDTO();
        Page<BoitePostale> boitePostalePage = new PageImpl<>(List.of(boitePostale));
        Page<BoitePostaleDTO> boitePostaleDTOPage = new PageImpl<>(List.of(boitePostaleDTO));

        // Configuration des mocks
        when(boitePostaleRepository.findBoitesPostalesByNumBP(numeroBP, pageable)).thenReturn(boitePostalePage);
        when(boitePostaleMapper.toBoitePostaleDTO(boitePostale)).thenReturn(boitePostaleDTO);

        // Appel de la méthode et vérification du résultat
        Page<BoitePostaleDTO> result = boitePostaleService.findBoitesPostalesByNumBP(numeroBP, 0, 10);
        assertEquals(boitePostaleDTOPage.getContent(), result.getContent());
    }

    @Test
    void testFindBoitesPostalesByNumBP_ThrowsNotFoundException() {
        // Préparation des données
        String numeroBP = "99999";
        Pageable pageable = PageRequest.of(0, 10);

        // Configuration des mocks pour retourner une page vide
        when(boitePostaleRepository.findBoitesPostalesByNumBP(numeroBP, pageable)).thenReturn(Page.empty());

        // Vérification que l'exception est levée
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            boitePostaleService.findBoitesPostalesByNumBP(numeroBP, 0, 10);
        });
        assertEquals("Aucune boite postale ne correspond à ce numéro.", exception.getMessage());
    }

    @Test
    void testFindBoitesPostalesByNumBP_ShortNumBP() {
        // Préparation des données
        String shortNumBP = "123";
        String numBP = "00123";
        Pageable pageable = PageRequest.of(0, 10);
        BoitePostale boitePostale = new BoitePostale();
        BoitePostaleDTO boitePostaleDTO = new BoitePostaleDTO();
        Page<BoitePostale> boitePostalePage = new PageImpl<>(List.of(boitePostale));

        // Configuration des mocks
        when(boitePostaleRepository.findBoitesPostalesByNumBP(numBP, pageable)).thenReturn(boitePostalePage);
        when(boitePostaleMapper.toBoitePostaleDTO(boitePostale)).thenReturn(boitePostaleDTO);

        // Appel de la méthode et vérification du résultat
        Page<BoitePostaleDTO> result = boitePostaleService.findBoitesPostalesByNumBP(shortNumBP, 0, 10);

        // Vérifications
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
    }
}
