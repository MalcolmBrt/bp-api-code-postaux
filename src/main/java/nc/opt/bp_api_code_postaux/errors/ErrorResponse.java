package nc.opt.bp_api_code_postaux.errors;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatusCode statusCode;
    private String message;
}
