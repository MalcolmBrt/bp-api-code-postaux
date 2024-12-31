package nc.opt.bp_api_code_postaux.codepostal.v1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostal;

public interface CodePostalRepositoryV1 extends JpaRepository<CodePostal, Long> {
    List<CodePostal> findCodePostalByNomCommune(String nomCommune);
}
