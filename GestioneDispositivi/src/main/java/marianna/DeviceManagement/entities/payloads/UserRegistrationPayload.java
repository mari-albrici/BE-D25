package marianna.DeviceManagement.entities.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import marianna.DeviceManagement.entities.User;

@Setter
@Getter
public class UserRegistrationPayload {
    @NotNull
    @Size(min = 8, max = 30, message = "Username must be between 8 and 30 characters")
    String username;
    @NotNull(message = "Name is requested")
    @Size(min = 3, max = 30, message = "Min3 characters, max 30")
    String name;
    @NotNull(message = "Lastname is requested")
    String lastname;
    @Email(message = "Email address is invalid")
    String email;
    @NotNull(message = "Password is requested")
    String password;

}
