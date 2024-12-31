package nc.opt.bp_api_code_postaux.codepostal.v3;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CodePostalSpecification implements Specification<CodePostalV3> {
    private String value;

    @Override
    public Predicate toPredicate(Root<CodePostalV3> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate predicate1 = builder.like(root.get("commune"), "%" + value.toUpperCase() + "%");
        Predicate predicate2 = builder.like(root.get("localite"), "%" + value.toUpperCase() + "%");
        return builder.or(predicate1, predicate2);
    }
}
