package nc.opt.bp_api_code_postaux.boitepostale.shared;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BoitePostaleMapper {
    @Mapping(target = "nomLocalite", source = "codePostal.nomCommune")
    @Mapping(target = "codePostal", source = "codePostal.codePostal")
    @Mapping(target = "nomAgence", source = "codePostal.agence.nom")
    BoitePostaleDTO toBoitePostaleDTO(BoitePostale boitePostale);

    List<BoitePostaleDTO> toBoitePostaleDTOList(List<BoitePostale> boitePostales);
}
