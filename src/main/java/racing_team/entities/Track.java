package racing_team.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trackId;

    private String country;
    private Float recordLapTime;
    private Integer seatCapacity;

//    @ManyToMany
//    private Team team;

}
