package com.github.umarvis.amal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "inStock")
    private Boolean inStock;
    @ManyToOne
    @JoinColumn(name = "users", referencedColumnName = "id")
    private User owner;
    @OneToOne
    @JoinColumn(name = "request", referencedColumnName = "id")
    private Request request;

}
