package marianna.DeviceManagement.services;

import marianna.DeviceManagement.entities.Device;
import marianna.DeviceManagement.entities.User;
import marianna.DeviceManagement.entities.enums.Status;
import marianna.DeviceManagement.exceptions.NotFoundException;
import marianna.DeviceManagement.repositories.DevicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceService {

    @Autowired
    private DevicesRepository deviceRepo;

    @Autowired
    private UserService userService;

    public Device create(Device d){
        return deviceRepo.save(d);
    }

    public List<Device> find(){
        return deviceRepo.findAll();
    }

    public Device findById(UUID id) throws NotFoundException {
        return deviceRepo.findById(id).orElseThrow(() -> new NotFoundException("Device not found"));
    }


    public void findByIdAndDelete(UUID id) throws NotFoundException{
        Device found = this.findById(id);
        deviceRepo.delete(found);
    }

    public Device findByIdAndUpdate(UUID id, Device body) throws NotFoundException {
        Device found = this.findById(id);
//        String username = body.getUser().getUsername();
//        User user = userService.findById(username);
        found.setUser(userService.findById(body.getUser().getUsername()));
        found.setState(body.getState());
        found.setId(id);
        return found;
    }
}
