package nc.opt.bp_api_code_postaux.codepostal.v3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nc.opt.bp_api_code_postaux.boitepostale.v3.BoitePostaleServiceV3;
import nc.opt.bp_api_code_postaux.errors.NotFoundException;

@Service()
@AllArgsConstructor
public class CodePostalServiceV3 {
    private final CodePostalRepositoryV3 codePostalRepository;
    private final CodePostalMapperV3 codePostalMapper;
    private final BoitePostaleServiceV3 boitePostaleService;

    public List<CodePostalDTO> findCodePostalByLocalite(String value) {
        CodePostalSpecification spec = new CodePostalSpecification(value);

        List<CodePostalV3> codesPostaux = codePostalRepository.findAll(spec);
        if (codesPostaux.isEmpty()) {
            throw new NotFoundException("Aucun code postal ne correspond à cette localité.");
        }

        List<CodePostalDTO> codesPostauxDTO = new ArrayList<>();
        for (CodePostalV3 codePostal : codesPostaux) {
            List<String> numerosBPs = boitePostaleService.findBoitesPostalesByCodePostal(codePostal.getCode());
            List<List<String>> intervalles = getIntervalles(numerosBPs);
            CodePostalDTO codePostalDTO = codePostalMapper.toCodePostalDTO(codePostal, intervalles);
            codesPostauxDTO.add(codePostalDTO);
        }
        return codesPostauxDTO;
    }

    public List<String> getLocalites() {
        List<String> localites = codePostalRepository.getLocalites();
        List<String> communes = codePostalRepository.getCommunes();

        Set<String> localitesSet = new HashSet<>();
        localitesSet.addAll(localites);
        localitesSet.addAll(communes);

        List<String> localitesList = new ArrayList<>(localitesSet);
        Collections.sort(localitesList);

        return localitesList;
    }

    // Fonction principale pour calculer les intervalles
    public List<List<String>> getIntervalles(List<String> numerosBPs) {
        List<List<String>> intervalles = new ArrayList<>();
        if (numerosBPs == null || numerosBPs.isEmpty()) {
            return intervalles;
        }

        // Initialiser le début et la fin du premier intervalle
        String start = numerosBPs.get(0);
        String prev = start;

        for (int i = 1; i < numerosBPs.size(); i++) {
            String current = numerosBPs.get(i);

            if (!areConsecutive(prev, current)) {
                // Ajouter l'intervalle précédent
                intervalles.add(List.of(start, prev));
                start = current;
            }
            prev = current;
        }

        // Ajouter le dernier intervalle
        intervalles.add(List.of(start, prev));

        return intervalles;
    }

    private boolean areConsecutive(String prev, String current) {
        // Vérifier si les deux chaînes ont le même préfixe alphabétique
        String prevPrefix = getAlphaPrefix(prev);
        String currentPrefix = getAlphaPrefix(current);

        if (!prevPrefix.equals(currentPrefix)) {
            return false;
        }

        // Extraire les parties numériques
        String prevNum = prev.substring(prevPrefix.length());
        String currentNum = current.substring(currentPrefix.length());

        try {
            int prevNumber = Integer.parseInt(prevNum);
            int currentNumber = Integer.parseInt(currentNum);
            return currentNumber == prevNumber + 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String getAlphaPrefix(String str) {
        int i = 0;
        while (i < str.length() && Character.isLetter(str.charAt(i))) {
            i++;
        }
        return str.substring(0, i);
    }
}
