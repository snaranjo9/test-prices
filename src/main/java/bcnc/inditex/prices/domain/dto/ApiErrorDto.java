package bcnc.inditex.prices.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDto {
    private HttpStatus status;
    private String message;
    private String error;
}