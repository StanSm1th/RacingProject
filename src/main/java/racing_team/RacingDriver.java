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
public class RacingDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;

    private String name;
    private Integer age;
    private String nationality;
    private String outfitColor;
    private Long salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carId")
    private RacingCar racingCar;




}
