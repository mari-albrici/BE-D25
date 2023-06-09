package marianna.DeviceManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marianna.DeviceManagement.entities.enums.State;

import java.util.UUID;

@Entity
@Table(name = "devices")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Devices {

    @Id
    @GeneratedValue
    private UUID id;
    private State state;

}
