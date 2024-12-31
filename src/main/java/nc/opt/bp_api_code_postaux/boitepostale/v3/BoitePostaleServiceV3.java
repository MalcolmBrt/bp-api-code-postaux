package nc.opt.bp_api_code_postaux.boitepostale.v3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nc.opt.bp_api_code_postaux.errors.InvalidPageException;
import nc.opt.bp_api_code_postaux.errors.NotFoundException;

@Service
@AllArgsConstructor
public class BoitePostaleServiceV3 {
    private final BoitePostaleRepositoryV3 boitePostaleRepository;

    public Page<BoitePostaleV3> findBoitesPostalesByNumero(
            String numBP,
            Integer pageNo,
            Integer pageSize) {

        pageSize = (pageSize >= 10 && pageSize <= 100) ? pageSize : 10;

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<BoitePostaleV3> boitesPostales = boitePostaleRepository.findBoitesPostalesByNumero(numBP, pageable);

        if (boitesPostales.getTotalElements() == 0) {
            throw new NotFoundException("Aucune boite postale ne correspond à ce numéro.");
        }
        if (pageNo >= boitesPostales.getTotalPages()) {
            throw new InvalidPageException("Le numéro de la page demandé est trop grand.");
        }
        return boitesPostales;
    }

    public List<String> findBoitesPostalesByCodePostal(Integer codePostal) {
        List<BoitePostaleV3> boitesPostales = boitePostaleRepository.findBoitesPostalesByCodePostal(codePostal);

        // Récupérer tous les numéros BPs qui ne contiennent pas de caractères alphabétiques
        List<String> numerosBPsNumeriques = boitesPostales.stream()
                .map(BoitePostaleV3::getNumero)
                .filter(numero -> numero.chars().noneMatch(Character::isAlphabetic))
                .collect(Collectors.toList());
        Collections.sort(numerosBPsNumeriques, (a, b) -> Integer.compare(Integer.parseInt(a), Integer.parseInt(b)));

        // Récupérer tous les numéros BPs qui contiennent des caractères alphabétiques
        List<String> numerosBPsAlpha = boitesPostales.stream()
                .map(BoitePostaleV3::getNumero)
                .filter(numero -> numero.chars().anyMatch(Character::isAlphabetic))
                .collect(Collectors.toList());
        Collections.sort(numerosBPsAlpha);

        List<String> numerosBPs = new ArrayList<>();
        numerosBPs.addAll(numerosBPsNumeriques);
        numerosBPs.addAll(numerosBPsAlpha);

        return numerosBPs;
    }
}
