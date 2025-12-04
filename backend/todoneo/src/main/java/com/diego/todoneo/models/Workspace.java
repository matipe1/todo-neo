package com.diego.todoneo.models;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "workspace")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name", nullable = false, length = 100)
    String name;
}
