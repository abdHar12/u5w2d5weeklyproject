package harouane.u5w2d5weeklyproject.Controllers;

import harouane.u5w2d5weeklyproject.DTOs.DeviceDTOs.CreationDevicePayload;
import harouane.u5w2d5weeklyproject.DTOs.EmployeePayload;
import harouane.u5w2d5weeklyproject.Entities.Device;
import harouane.u5w2d5weeklyproject.Entities.Employee;
import harouane.u5w2d5weeklyproject.Exceptions.BadRequestException;
import harouane.u5w2d5weeklyproject.Services.DeviceService;
import harouane.u5w2d5weeklyproject.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Page<Device> getAllElements(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy
    ){
        return deviceService.getAll(page, size, orderBy);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Device getSingleElement(@PathVariable int id){
        return deviceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device postNewElement(@RequestBody @Validated CreationDevicePayload newDevice, BindingResult validation){
        if(validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return deviceService.saveNewElement(newDevice);
    }

    @PutMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Device modifyElement(@PathVariable int id, @RequestBody @Validated CreationDevicePayload newDevice, BindingResult validation){
        if(validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return deviceService.modifyElement(id, newDevice);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteElement(@PathVariable int id){
        deviceService.deleteElement(id);
    }
}
