package harouane.u5w2d5weeklyproject.DAOs;

import harouane.u5w2d5weeklyproject.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);
    Optional<Employee> findByEmail(String email);


}
