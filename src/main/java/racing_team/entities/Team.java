package racing_team.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teamId;

    private String teamName;
    private Integer budget;
    private Integer numberOfWins;

    @OneToMany(mappedBy = "team")
    private List<Driver> drivers;

}
