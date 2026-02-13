package ru.fsog.tmhHacaton2026.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="defect")
public class Defect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "type_id")
    private DefectTypes defectTypes;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "photo_id", nullable = false)
    private Photo photo;

    @Column(name = "comment")
    private String comment;

    @Column(name = "line")
    private String line;
}
