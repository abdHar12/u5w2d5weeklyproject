package harouane.u5w2d5weeklyproject.DAOs;

import harouane.u5w2d5weeklyproject.Entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDAO extends JpaRepository<Device, Integer> {
}
