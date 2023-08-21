package com.parley.parley.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "categories", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name", unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "category")
    private List<Topic> topics;

    public Category(String name){
        this.name = name;
    }
}
