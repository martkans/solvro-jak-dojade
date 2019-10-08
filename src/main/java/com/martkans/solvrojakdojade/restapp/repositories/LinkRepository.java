package com.martkans.solvrojakdojade.restapp.repositories;

import com.martkans.solvrojakdojade.restapp.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {
}
