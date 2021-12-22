package com.example.hrms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany()
    @JoinTable(
            name = "cities_in_job_advertisement",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "job_advertisement_id")
    )
    @JsonIgnore
    @ToString.Exclude
    Set<JobAdvertisement> cities_in_job_advertisement = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        City city = (City) o;

        return Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return 39525063;
    }

    public City(String name) {
        this.name = name;
    }
}
