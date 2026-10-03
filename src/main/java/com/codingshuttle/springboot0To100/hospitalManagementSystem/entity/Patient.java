package com.codingshuttle.springboot0To100.hospitalManagementSystem.entity;

import com.codingshuttle.springboot0To100.hospitalManagementSystem.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String email;

    private String gender;

    @Enumerated(value = EnumType.STRING)
    private BloodGroupType bloodGroup;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ToString.Exclude
    @OneToOne(cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="patient_ins_Id", unique = true)
    private Insurance insurance; // Owning Side

    //@ToString.Exclude
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // inverse side
    private Set<Appointment> appointments = new HashSet<>();
}