package harouane.u5w2d5weeklyproject.DTOs.DeviceDTOs;

import harouane.u5w2d5weeklyproject.Enums.DeviceState;

public class ModifyDevicePayload extends CreationDevicePayload{
    int employeeId;
    public ModifyDevicePayload(String type, String state, int employeeId) {
        super(type, state);
        this.employeeId=employeeId;
    }
}
