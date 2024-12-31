package nc.opt.bp_api_code_postaux.alive;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Alive", description = "Vérifier l'état de santé")
public class AliveController {
    @Operation(summary = "Vérifier l'état de santé de l'application.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
    })
    @GetMapping("/alive")
    public ResponseEntity<Alive> getAlive() {
        LocalDateTime now = LocalDateTime.now();
        Alive alive = new Alive("alive", now);
        return ResponseEntity.ok(alive);
    }
}
