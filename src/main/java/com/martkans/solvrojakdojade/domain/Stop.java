package com.martkans.solvrojakdojade.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Stop {

    @Id
    private Integer id;
    private String stopName;

    @OneToMany(mappedBy = "source")
    private Set<Link> sourceLinks = new HashSet<>();
    @OneToMany(mappedBy = "target")
    private Set<Link> targetLinks = new HashSet<>();
}
