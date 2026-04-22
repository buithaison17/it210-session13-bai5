package org.example.bai5.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prescriptions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Prescription {
    @Id
    private Long id;
    @Column(name = "doctor_name")
    private String doctorName;
    private String patientName;
    private String description;
    private Integer quantity;
}
