package ru.fsog.tmhHacaton2026.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    @Column(name = "image")
    private byte[] photo;

    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @ToString.Exclude
    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    private List<Defect> defects;
}
