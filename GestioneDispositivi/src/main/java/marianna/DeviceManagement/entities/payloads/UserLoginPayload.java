package marianna.DeviceManagement.entities.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserLoginPayload {
    @NotNull
    String username;
    @NotNull
    String password;

}
