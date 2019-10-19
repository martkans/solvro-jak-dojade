package com.martkans.solvrojakdojade.authentication.repositories;

import com.martkans.solvrojakdojade.authentication.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRolesByName(String name);
}
