package marianna.DeviceManagement.services;

import marianna.DeviceManagement.entities.User;
import marianna.DeviceManagement.entities.payloads.UserRegistrationPayload;
import marianna.DeviceManagement.exceptions.BadRequestException;
import marianna.DeviceManagement.exceptions.NotFoundException;
import marianna.DeviceManagement.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepo;

    public User create(User u){
        return userRepo.save(u);
    }

    public List<User> find(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return userRepo.findAll();
    }

    public User findById(String username) throws NotFoundException {
        return userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User findByIdAndUpdate(String username, User u) throws NotFoundException {
        User found = this.findById(username);

        found.setUsername(username);
        found.setName(u.getName());
        found.setLastname(u.getLastname());
        found.setEmail(u.getEmail());

        return userRepo.save(found);
    }

    public void findByIdAndDelete(String username) throws NotFoundException{
        User found = this.findById(username);
        userRepo.delete(found);
    }

    public User create(UserRegistrationPayload u){
        userRepo.findByUsername(u.getUsername()).ifPresent(user -> {
            throw new BadRequestException("Username " + user.getUsername() + " already in use!");
        });
        User newUser = new User(u.getUsername(), u.getName(), u.getLastname(), u.getEmail(), u.getPassword());
        return userRepo.save(newUser);
    }
}
