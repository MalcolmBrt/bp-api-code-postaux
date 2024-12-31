package nc.opt.bp_api_code_postaux.boitepostale.shared;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import nc.opt.bp_api_code_postaux.errors.NotFoundException;
import nc.opt.bp_api_code_postaux.errors.InvalidPageException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoitePostaleService {
    private final BoitePostaleRepository boitePostaleRepository;

    private final BoitePostaleMapper boitePostaleMapper;

    public Page<BoitePostaleDTO> findBoitesPostalesByNumBP(
            String numBP,
            Integer pageNo,
            Integer pageSize) {

        pageSize = (pageSize >= 10 && pageSize <= 100) ? pageSize : 10;

        // puisque le numéro d'une bp est un varchar(5) dans la bdd
        numBP = numBP.length() < 5 ? "0".repeat(5 - numBP.length()) + numBP : numBP;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<BoitePostale> boitesPostales = boitePostaleRepository.findBoitesPostalesByNumBP(numBP, pageable);

        if (boitesPostales.getTotalElements() == 0) {
            throw new NotFoundException("Aucune boite postale ne correspond à ce numéro.");
        }
        if (pageNo >= boitesPostales.getTotalPages()) {
            throw new InvalidPageException("Le numéro de la page demandé est trop grand.");
        }
        return boitesPostales.map(boitePostaleMapper::toBoitePostaleDTO);
    }
}
