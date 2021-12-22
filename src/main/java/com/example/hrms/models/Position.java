package com.example.hrms.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String jobTitle;

    private int maxSalary;
    private int minSalary;

    @ManyToMany()
    @JoinTable(
            name = "positions_in_job_advertisement",
            joinColumns = @JoinColumn(name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "job_advertisement_id")
    )
    @ToString.Exclude
    @JsonIgnore
    Set<JobAdvertisement> positions_in_job_advertisement = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Position position = (Position) o;

        return Objects.equals(id, position.id);
    }

    @Override
    public int hashCode() {
        return 735845859;
    }



    public Position(String jobTitle, int maxSalary, int minSalary) {
        this.jobTitle = jobTitle;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
    }
}
