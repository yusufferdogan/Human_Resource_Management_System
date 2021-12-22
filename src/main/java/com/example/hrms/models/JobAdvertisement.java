package com.example.hrms.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class JobAdvertisement {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private int openPositionNumber;

    private LocalDate deadline;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_advertisement_id",referencedColumnName = "id")
    @JsonIgnore
    TaskMaster task_master;

    @ManyToMany(mappedBy = "positions_in_job_advertisement")
    @ToString.Exclude
    Set<Position> positions = new HashSet<>();

    String jobDescription;

    @ManyToMany(mappedBy = "cities_in_job_advertisement")
    @ToString.Exclude
    Set<City> cities = new HashSet<>();

    @ManyToMany(mappedBy = "applied_jobs")
    @ToString.Exclude
    @JsonIgnore
    Set<Candidate> candidates = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JobAdvertisement that = (JobAdvertisement) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public JobAdvertisement(String jobDescription, int openPositionNumber, LocalDate deadline) {
        this.jobDescription = jobDescription;
        this.openPositionNumber = openPositionNumber;
        this.deadline = deadline;
    }
}
