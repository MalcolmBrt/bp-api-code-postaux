package nc.opt.bp_api_code_postaux.codepostal.v2;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostal;

@AllArgsConstructor
public class CodePostalSpecification implements Specification<CodePostal> {
    private String key;
    private String value;

    @Override
    public Predicate toPredicate(Root<CodePostal> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(root.<String>get(key), "%" + value.toUpperCase() + "%");
    }
}
