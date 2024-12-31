package nc.opt.bp_api_code_postaux.codepostal.v2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostal;

public interface CodePostalRepositoryV2 extends JpaRepository<CodePostal, Long>, JpaSpecificationExecutor<CodePostal> {
    @Query("SELECT DISTINCT TRIM(nomCommune) FROM CodePostal ORDER BY TRIM(nomCommune)")
    List<String> getCommunes();
}
