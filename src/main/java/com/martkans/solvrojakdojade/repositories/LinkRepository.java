package com.martkans.solvrojakdojade.repositories;

import com.martkans.solvrojakdojade.domain.Link;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends CrudRepository<Link, Integer> {
}
