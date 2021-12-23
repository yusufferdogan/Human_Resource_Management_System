package com.example.hrms.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String schoolName;
    private String department;
    private LocalDate graduationYear;
    private String githubLink;
    private String stackOverFlowLink;
    private String coverLetter;

    @Lob
    @JsonIgnore
    private Byte[] image;

    @OneToMany(mappedBy = "cv")
    @ToString.Exclude
    private Set<Experience> experiences = new HashSet<>();

    @OneToMany(mappedBy = "cv_of_technology")
    @ToString.Exclude
    private Set<Technology> technologies = new HashSet<>();
}
