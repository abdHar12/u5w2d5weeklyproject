package harouane.u5w2d5weeklyproject.Controllers;

import harouane.u5w2d5weeklyproject.DTOs.EmployeePayload;
import harouane.u5w2d5weeklyproject.Entities.Employee;
import harouane.u5w2d5weeklyproject.Exceptions.BadRequestException;
import harouane.u5w2d5weeklyproject.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy
    ){
        return employeeService.getAll(page, size, orderBy);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Employee getSingleElement(@PathVariable int id){
        return employeeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee postNewElement(@RequestBody @Validated EmployeePayload newEmployee, BindingResult validation){
        if(validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return employeeService.saveNewElement(newEmployee);
    }

    @PutMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Employee modifyElement(@PathVariable int id, @RequestBody @Validated EmployeePayload modifiedEmployee, BindingResult validation){
        if(validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return employeeService.modifyElement(id, modifiedEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteElement(@PathVariable int id){
        employeeService.deleteElement(id);
    }
}
