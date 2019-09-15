package com.martkans.solvrojakdojade.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"links"})
public class Stop {

    @Id
    private Integer id;
    private String stopName;

    @ManyToMany(mappedBy = "stops")
    private Set<Link> links = new HashSet<>();
}
