package com.martkans.solvrojakdojade.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"stops"})
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Min(0)
    @NotNull
    private Integer distance;

    @ManyToMany
    @JoinTable(name = "link_stop", joinColumns = @JoinColumn(name = "link_id"),
            inverseJoinColumns = @JoinColumn(name = "stop_id"))
    @NotNull
    @Size(min = 2, max = 2)
    private Set<Stop> stops = new HashSet<>();
}
