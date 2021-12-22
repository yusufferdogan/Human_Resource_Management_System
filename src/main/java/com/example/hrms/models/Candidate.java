package com.example.hrms.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;
    private Integer tcNo;
    private LocalDate birthDate;
    private String emailAddress;
    private String password;

    @ManyToMany()
    @JoinTable(
            name = "candidates_in_job_advertisement",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "job_advertisement_id")
    )
    @ToString.Exclude
    Set<JobAdvertisement> applied_jobs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Candidate candidate = (Candidate) o;

        return Objects.equals(id, candidate.id);
    }

    @Override
    public int hashCode() {
        return 703906644;
    }


}
