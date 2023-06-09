package marianna.DeviceManagement.auth;

import marianna.DeviceManagement.auth.payload.AuthenticationSuccessfulPayload;
import marianna.DeviceManagement.entities.User;
import marianna.DeviceManagement.entities.payloads.UserLoginPayload;
import marianna.DeviceManagement.entities.payloads.UserRegistrationPayload;
import marianna.DeviceManagement.exceptions.NotFoundException;
import marianna.DeviceManagement.exceptions.UnauthorizedException;
import marianna.DeviceManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody @Validated UserRegistrationPayload body){
        User createdUser = userService.create(body);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationSuccessfulPayload> login(@RequestBody UserLoginPayload body)
            throws NotFoundException {

        User user = userService.findById(body.getUsername());

        String token;
        if (!body.getPassword().matches(user.getPassword())) {
            throw new UnauthorizedException("Username or password are invalid");
        } else {

            token = JWTTools.createToken(user);

            return new ResponseEntity<>(new AuthenticationSuccessfulPayload(token), HttpStatus.OK);
        }


    }

}

