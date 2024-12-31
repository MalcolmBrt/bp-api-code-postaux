package nc.opt.bp_api_code_postaux.boitepostale.v3;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoitePostaleRepositoryV3 extends JpaRepository<BoitePostaleV3, Long> {
    Page<BoitePostaleV3> findBoitesPostalesByNumero(String numero, Pageable pageable);

    List<BoitePostaleV3> findBoitesPostalesByCodePostal(Integer codePostal);
}
