package com.martkans.solvrojakdojade.restapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"links"})
public class Stop {

    @Id
    private Integer id;

    @NotNull
    private String stopName;

    @ManyToMany(mappedBy = "stops")
    private Set<Link> links = new HashSet<>();

    public void addLink(Link link) {
        links.add(link);
        link.getStops().add(this);
    }
}
