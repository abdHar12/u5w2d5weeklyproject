package harouane.u5w2d5weeklyproject.Entities;

import harouane.u5w2d5weeklyproject.Enums.DeviceState;
import harouane.u5w2d5weeklyproject.Enums.DeviceType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Device {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    DeviceType type;
    @Enumerated(EnumType.STRING)
    DeviceState state;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;

    public Device(DeviceType type, DeviceState state) {
        this.type = type;
        this.state = state;
    }
}
