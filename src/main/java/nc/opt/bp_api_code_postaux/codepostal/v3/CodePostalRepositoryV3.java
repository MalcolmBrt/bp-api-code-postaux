package nc.opt.bp_api_code_postaux.codepostal.v3;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CodePostalRepositoryV3 extends JpaRepository<CodePostalV3, Long>, JpaSpecificationExecutor<CodePostalV3> {
    @Query("SELECT DISTINCT TRIM(localite) FROM CodePostalV3 ORDER BY TRIM(localite)")
    List<String> getLocalites();

    @Query("SELECT DISTINCT TRIM(commune) FROM CodePostalV3 ORDER BY TRIM(commune)")
    List<String> getCommunes();
}
