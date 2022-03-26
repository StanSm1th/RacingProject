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
public class RacingCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;

    private String brand;
    private String color;
    private Integer topSpeed;
    private Integer price;

    @OneToOne(mappedBy = "racingCar")
    private RacingDriver racingDriver;



}
