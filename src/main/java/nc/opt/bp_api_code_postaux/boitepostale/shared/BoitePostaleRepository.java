package nc.opt.bp_api_code_postaux.boitepostale.shared;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoitePostaleRepository extends JpaRepository<BoitePostale, Long> {
    Page<BoitePostale> findBoitesPostalesByNumBP(String numBP, Pageable pageable);
}
