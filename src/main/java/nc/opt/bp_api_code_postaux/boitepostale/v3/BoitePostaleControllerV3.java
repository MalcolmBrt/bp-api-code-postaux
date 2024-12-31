
package nc.opt.bp_api_code_postaux.boitepostale.v3;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Tag(name = "Boîtes postales", description = "Recherche des boîtes postales")
public class BoitePostaleControllerV3 {
    private final BoitePostaleServiceV3 boitePostaleService;

    @Operation(summary = "Lister les informations liées à une boite postale.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Les paramètres ne sont pas corrects.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Aucune boîte postale ne correspond à ce numéro.", content = @Content) })
    @GetMapping("v3/boites-postales/search")
    public ResponseEntity<Page<BoitePostaleV3>> getCodePostalByCommune(
            @Parameter(description = "Le numéro de la boite postale dont on cherche les informations.") @RequestParam() String query,
            @Parameter(description = "Numéro de la page.") @RequestParam(defaultValue = "0") Integer pageNo,
            @Parameter(description = "Nombre maximum de résultats par page (entre 10 et 100).") @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(boitePostaleService.findBoitesPostalesByNumero(query, pageNo, pageSize));
    }

}