package com.martkans.solvrojakdojade.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"source", "target"})
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer distance;

    @ManyToOne
    private Stop source;

    @ManyToOne
    private Stop target;
}
