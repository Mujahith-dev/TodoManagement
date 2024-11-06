package com.java.TodoManagement.Entity;


import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @   GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    private boolean completed;
}
