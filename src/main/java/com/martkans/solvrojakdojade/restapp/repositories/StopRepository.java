package com.martkans.solvrojakdojade.restapp.repositories;

import com.martkans.solvrojakdojade.restapp.domain.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StopRepository  extends JpaRepository<Stop, Integer> {

    Optional<Stop> findById(Integer id);

}
