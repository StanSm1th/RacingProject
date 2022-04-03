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
@Table(name = "racingcar")
public class RacingCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    private String carMake;
    private String carModel;
    private Integer carModelYear;
    private Integer topSpeed;
    private Integer price;

    @OneToOne(mappedBy = "racingCar", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
    private RacingDriver racingDriver;



}
