package marianna.DeviceManagement.auth.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationSuccessfulPayload {
    private String accessToken;
}

