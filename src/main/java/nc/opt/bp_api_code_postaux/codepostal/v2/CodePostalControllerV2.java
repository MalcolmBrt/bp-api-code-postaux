package nc.opt.bp_api_code_postaux.codepostal.v2;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import nc.opt.bp_api_code_postaux.codepostal.shared.CodePostalDTO;

@RestController
@AllArgsConstructor
@Tag(name = "Codes postaux", description = "Recherche des codes postaux")
public class CodePostalControllerV2 {
    private final CodePostalServiceV2 codePostalService;

    @Operation(summary = "Lister les codes postaux en fonction d'une commune.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CodePostalDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Les paramètres ne sont pas corrects.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Aucun code postal ne correspond à cette commune.", content = @Content) })
    @GetMapping("/v2/codes-postaux/search")
    public ResponseEntity<List<CodePostalDTO>> getCodePostalByCommune(
            @Parameter(description = "La commune dont on cherche le code postal.") @RequestParam() String query) {
        return ResponseEntity.ok(codePostalService.findCodePostalByNomCommune(query));
    }

    @Operation(summary = "Récupérer la liste des communes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(type = "string"))) }),
    })
    @GetMapping("/v2/codes-postaux/communes")
    public ResponseEntity<List<String>> getCommunes() {
        return ResponseEntity.ok(codePostalService.getCommunes());
    }
}
