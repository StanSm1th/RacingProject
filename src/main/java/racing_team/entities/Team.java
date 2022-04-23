package racing_team.entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "team")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Track> tracks;

}
