package marianna.DeviceManagement.repositories;

import marianna.DeviceManagement.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DevicesRepository extends JpaRepository<Device, UUID> {
}
