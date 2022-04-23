package racing_team.entities;

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
@Table(name = "driver")
public class Driver {

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
    private Car car;

    //@JoinColumn(name = "teamId")
    @ManyToOne
    private Team team;


    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", nationality='" + nationality + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", salary=" + salary +

                '}';
    }
}
