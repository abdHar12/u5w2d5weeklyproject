package harouane.u5w2d5weeklyproject.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import harouane.u5w2d5weeklyproject.DAOs.EmployeeDAO;
import harouane.u5w2d5weeklyproject.DTOs.EmployeePayload;
import harouane.u5w2d5weeklyproject.Entities.Employee;
import harouane.u5w2d5weeklyproject.Exceptions.BadRequestException;
import harouane.u5w2d5weeklyproject.Exceptions.NotFoundElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    private Cloudinary cloudinaryUploader;
    public Page<Employee> getAll(int page, int size, String orderBy) {
        if(size>100) size=100;
        Pageable pageable= PageRequest.of(page, size, Sort.by(orderBy));

        return employeeDAO.findAll(pageable);
    }

    public Employee findById(int id) {
        return employeeDAO.findById(id).orElseThrow(()->new NotFoundElements("L'id "+ id+ " non è stato trovato"));
    }

    public Employee saveNewElement(EmployeePayload newEmployee) {
        employeeDAO.findByUsername(newEmployee.username()).ifPresent(user-> {
            throw new BadRequestException("L'username è gia esistente!");
        });
        employeeDAO.findByEmail(newEmployee.email()).ifPresent(user -> {
            throw new BadRequestException("L'email è gia esistente!");
        });
        Employee employee=new Employee(newEmployee.username(), newEmployee.email(), newEmployee.name(), newEmployee.surname());
        return employeeDAO.save(employee);
    }

    public Employee modifyElement(int id, EmployeePayload modifiedEmployee) {
        Employee employee=this.findById(id);
        employee.setName(modifiedEmployee.name());
        employee.setSurname(modifiedEmployee.surname());
        Optional<Employee> found=employeeDAO.findByEmail(modifiedEmployee.email());
        if(found.isPresent()) {
            if (!Objects.equals(found.get().getEmail(), findById(id).getEmail()))
                throw new BadRequestException("L'email è gia esistente!");
        }
        employee.setEmail(modifiedEmployee.email());
        found = employeeDAO.findByUsername(modifiedEmployee.username());
        if(found.isPresent()) {
            if (!Objects.equals(found.get().getUsername(), findById(id).getUsername()))
                throw new BadRequestException("L'username è gia esistente!");
        }
        employee.setUsername(modifiedEmployee.username());
        return employeeDAO.save(employee);
    }


    public void deleteElement(int id) {
        Employee employee=this.findById(id);
        employeeDAO.delete(employee);
    }

    public Employee uploadImage(MultipartFile image, int id) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(),
                ObjectUtils.emptyMap()).get("url");
        Employee employee=findById(id);
        employee.setAvatar(url);
        return employeeDAO.save(employee);
    }
}
