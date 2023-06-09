package marianna.DeviceManagement.runners;

import marianna.DeviceManagement.entities.Laptop;
import marianna.DeviceManagement.entities.Smartphone;
import marianna.DeviceManagement.entities.enums.Status;
import marianna.DeviceManagement.services.DeviceService;
import marianna.DeviceManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DeviceRunner implements CommandLineRunner {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        try{
                Laptop laptop = new Laptop();
                laptop.setState(Status.MAINTENANCE);
                laptop.setUser(null);
//                deviceService.create(laptop);

                Smartphone smartphone = new Smartphone();
                smartphone.setState(Status.DISMISSED);
                smartphone.setUser(null);
//                deviceService.create(smartphone);


        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
