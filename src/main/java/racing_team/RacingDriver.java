package racing_team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "racingdriver")
public class RacingDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;

    private String name;
    private String surname;
    private String dateOfBirth;
    private String nationality;
    private String sponsor;
    private Integer salary;

    @JoinColumn(name = "carId")
    @OneToOne(cascade = CascadeType.ALL)

    private RacingCar racingCar;




}
