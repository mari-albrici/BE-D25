package marianna.DeviceManagement.controllers;

import marianna.DeviceManagement.entities.*;
import marianna.DeviceManagement.entities.enums.Status;
import marianna.DeviceManagement.services.DeviceService;
import marianna.DeviceManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserService userService;

    //********** GET ALL DEVICES **********
    @GetMapping("")
    public List<Device> getDevice(){
        return deviceService.find();
    }

    //********** POST NEW SMARTPHONE **********
    @PostMapping("/smartphone")
    @ResponseStatus(HttpStatus.CREATED)
    public Smartphone saveSmartphone(@RequestBody Smartphone body) {
        Smartphone smart = new Smartphone();

        User userToAdd = userService.findById(body.getUser().getUsername());
        smart.setState(body.getState());
        smart.setUser(userToAdd);

        return (Smartphone) deviceService.create(smart);
    }

    //********** POST NEW TABLET **********
    @PostMapping("/tablet")
    @ResponseStatus(HttpStatus.CREATED)
    public Tablet saveTablet(@RequestBody Tablet body) {
        Tablet tab = new Tablet();

        User userToAdd = userService.findById(body.getUser().getUsername());
        tab.setState(body.getState());
        tab.setUser(userToAdd);

        return (Tablet) deviceService.create(body);
    }

    //********** POST NEW LAPTOP **********
    @PostMapping("/laptop")
    @ResponseStatus(HttpStatus.CREATED)
    public Laptop saveLaptop(@RequestBody Laptop body) {
        Laptop lap = new Laptop();

        User userToAdd = userService.findById(body.getUser().getUsername());
        lap.setState(body.getState());
        lap.setUser(userToAdd);

        return (Laptop) deviceService.create(body);
    }

    //********** GET SINGLE DEVICE **********
    @GetMapping("/{deviceId}")
    public Device getDevice(@PathVariable UUID deviceId) throws Exception {
        return deviceService.findById(deviceId);
    }

    //********** PUT SINGLE SMARTPHONE **********
    @PutMapping("/smartphone/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    public Smartphone updateSmartphone(@PathVariable UUID deviceId, @RequestBody Smartphone body) throws Exception {
        return (Smartphone) deviceService.findByIdAndUpdate(deviceId, body);
    }

    //********** DELETE SINGLE DEVICE **********
    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice(@PathVariable UUID deviceId) throws Exception {
        deviceService.findByIdAndDelete(deviceId);
    }
}
