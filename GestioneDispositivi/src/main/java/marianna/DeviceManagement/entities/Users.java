package marianna.DeviceManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String name;
    private String lastname;
    private String email;

    @OneToMany
    private List<Devices> devices;
    
}
