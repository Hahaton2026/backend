package ru.fsog.tmhHacaton2026.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "defectTypes")
public class DefectTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "defectTypes", cascade = CascadeType.ALL)
    private List<Defect> defects;
}
