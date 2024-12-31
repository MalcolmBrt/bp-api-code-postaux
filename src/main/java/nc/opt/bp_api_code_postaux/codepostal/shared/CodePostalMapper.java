package nc.opt.bp_api_code_postaux.codepostal.shared;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CodePostalMapper {
    CodePostalDTO codePostalToCodePostalDTO(CodePostal codePostal);

    List<CodePostalDTO> toCodePostalDTOList(List<CodePostal> codePostal);
}
