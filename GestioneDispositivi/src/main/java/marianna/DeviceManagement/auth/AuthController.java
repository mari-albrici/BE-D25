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
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder bcrypt;


    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody @Validated UserRegistrationPayload body){

        body.setPassword(bcrypt.encode(body.getPassword()));
        User createdUser = userService.create(body);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationSuccessfulPayload> login(@RequestBody UserLoginPayload body)
            throws NotFoundException {

        User user = userService.findById(body.getUsername());

        String token;

        String plainPW = body.getPassword();
        String hashedPW = user.getPassword();

        if (!bcrypt.matches(plainPW, hashedPW))
            throw new UnauthorizedException("Credenziali non valide");

        token = JWTTools.createToken(user);

        return new ResponseEntity<>(new AuthenticationSuccessfulPayload(token), HttpStatus.OK);
        }


    }



