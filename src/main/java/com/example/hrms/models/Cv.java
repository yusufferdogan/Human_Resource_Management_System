package com.example.hrms.models;
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
    private Byte[] image;

    @OneToMany(mappedBy = "cv")
    private Set<Experience> experiences = new HashSet<>();

    @OneToMany(mappedBy = "cv_of_technology")
    private Set<Technology> technologies = new HashSet<>();
}
