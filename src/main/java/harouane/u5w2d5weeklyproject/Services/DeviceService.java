package harouane.u5w2d5weeklyproject.Services;

import harouane.u5w2d5weeklyproject.DAOs.DeviceDAO;
import harouane.u5w2d5weeklyproject.DTOs.DevicePayload;
import harouane.u5w2d5weeklyproject.Entities.Device;
import harouane.u5w2d5weeklyproject.Entities.Employee;
import harouane.u5w2d5weeklyproject.Enums.DeviceState;
import harouane.u5w2d5weeklyproject.Enums.DeviceType;
import harouane.u5w2d5weeklyproject.Exceptions.BadRequestException;
import harouane.u5w2d5weeklyproject.Exceptions.NotFoundElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {
    @Autowired
    DeviceDAO deviceDAO;
    @Autowired
    EmployeeService employeeService;
    public Page<Device> getAll(int page, int size, String orderBy) {
        if(size>100) size=100;
        Pageable pageable= PageRequest.of(page, size, Sort.by(orderBy));

        return deviceDAO.findAll(pageable);
    }

    public Device findById(int id) {
        return deviceDAO.findById(id).orElseThrow(()->new NotFoundElements("L'id "+ id+ " non è stato trovato"));
    }

    public Device saveNewElement(DevicePayload newDevice) {
        List<String> list1=new ArrayList<>(List.of("AVAILABLE", "ASSIGNED", "DISMISSED", "IN_MAINTENANCE"));
        if(!list1.contains(newDevice.getState().toUpperCase()))
            throw new BadRequestException("Gli stati da impostare possono essere scritti solo nei seguenti modi: AVAILABLE, ASSIGNED, DISMISSED, IN_MAINTENANCE");
        DeviceState state= DeviceState.valueOf(newDevice.getState().toUpperCase());
        List<String> list2=new ArrayList<>(List.of("TABLET", "PHONE", "PC"));
        if(!list2.contains(newDevice.getType().toUpperCase()))
            throw new BadRequestException("Il tipo di device può essere: TABLET, PHONE, PC");
        DeviceType type= DeviceType.valueOf(newDevice.getType().toUpperCase());
        Device device=new Device(type, state);
        return deviceDAO.save(device);
    }

    public Device modifyElement(int id, DevicePayload newDevice) {
        Device device=this.findById(id);
        List<String> list=new ArrayList<>(List.of("AVAILABLE", "ASSIGNED", "DISMISSED", "IN_MAINTENANCE"));
        if(!list.contains(newDevice.getState().toUpperCase()))
            throw new BadRequestException("Gli stati da impostare possono essere scritti solo nei seguenti modi: AVAILABLE, ASSIGNED, DISMISSED, IN_MAINTENANCE");
        DeviceState state= DeviceState.valueOf(newDevice.getState().toUpperCase());
        List<String> list2=new ArrayList<>(List.of("TABLET", "PHONE", "PC"));
        if(!list2.contains(newDevice.getType().toUpperCase()))
            throw new BadRequestException("Il tipo di device può essere: TABLET, PHONE, PC");
        device.setType(DeviceType.valueOf(newDevice.getType()));
        if(!(state==DeviceState.ASSIGNED)) device.setEmployee(null);
        device.setState(state);
        return deviceDAO.save(device);
    }
    public void deleteElement(int id) {
        Device device=this.findById(id);
        deviceDAO.delete(device);
    }

    public Device setNewOwner(int deviceId, int employeeId) {
        Device device=this.findById(deviceId);
        if(!(device.getState()==DeviceState.AVAILABLE))
            throw new BadRequestException("Il device non può essere assegnato. Si trova nello stato: " + device.getState());
        Employee employee=employeeService.findById(employeeId);
        device.setEmployee(employee);
        device.setState(DeviceState.ASSIGNED);
        return deviceDAO.save(device);
    }
}
