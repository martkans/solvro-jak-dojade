package com.martkans.solvrojakdojade.authentication.repositories;

import com.martkans.solvrojakdojade.authentication.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
