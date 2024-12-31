package nc.opt.bp_api_code_postaux.alive;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class Alive {
    private String status;
    private LocalDateTime timestamp;
}
