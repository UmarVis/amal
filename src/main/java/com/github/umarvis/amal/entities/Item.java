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
@Table(name = "items")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "in_stock")
    private Boolean inStock;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;


}
