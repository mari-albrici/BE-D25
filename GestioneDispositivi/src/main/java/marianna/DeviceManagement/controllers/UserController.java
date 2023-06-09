package marianna.DeviceManagement.controllers;

import marianna.DeviceManagement.entities.User;
import marianna.DeviceManagement.entities.payloads.UserRegistrationPayload;
import marianna.DeviceManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //********** GET ALL USERS **********
    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page){
        return (Page<User>) userService.find(page);
    }

    //********** POST NEW USER **********
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserRegistrationPayload body) {
        return userService.create(body);
    }

    //********** GET SINGLE USER **********
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) throws Exception {
        return userService.findById(username);
    }

    //********** PUT SINGLE USER **********
    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User body) throws Exception {
        return userService.findByIdAndUpdate(username, body);
    }

    //********** DELETE SINGLE USER **********
    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String username) throws Exception {
        userService.findByIdAndDelete(username);
    }

}
