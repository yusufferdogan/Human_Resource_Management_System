package com.example.hrms.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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
//    experiences Experience
//    image Image
    private String githubLink;
    private String stackOverFlowLink;
    private String coverLetter;

    @OneToMany
    private Set<Technology> technologies = new HashSet<>();
}
