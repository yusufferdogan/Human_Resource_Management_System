package com.example.hrms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
@Table(name = "task_master")
public class TaskMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String companyName;
    private String website;
    private String email;
    private String password;
    private String phoneNumber;

    @OneToMany(mappedBy = "task_master")
    @ToString.Exclude
    Set<JobAdvertisement> publishedJobs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TaskMaster that = (TaskMaster) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1340746549;
    }

    public TaskMaster(String companyName, String website, String email, String password, String phoneNumber) {
        this.companyName = companyName;
        this.website = website;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
