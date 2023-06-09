package marianna.DeviceManagement.runners;
import java.util.Locale;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import marianna.DeviceManagement.entities.User;
import marianna.DeviceManagement.services.UserService;

@Component
public class UserRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Override
    public void run(String... args){

        Faker faker = new Faker(new Locale("it"));

        for (int i = 0; i < 10; i++)
            try {
                User user = new User();
                user.setUsername(faker.name().username());
                user.setName(faker.name().firstName());
                user.setLastname(faker.name().lastName());
                user.setEmail(faker.internet().emailAddress());
                user.setPassword(faker.internet().password());
//                userService.create(user);
            } catch (Exception e) {
                System.out.println(e);
            }

    }

}
