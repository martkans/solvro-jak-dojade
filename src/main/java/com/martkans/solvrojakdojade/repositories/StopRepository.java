package com.martkans.solvrojakdojade.repositories;

import com.martkans.solvrojakdojade.domain.Stop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository  extends CrudRepository<Stop, Integer> {
}
