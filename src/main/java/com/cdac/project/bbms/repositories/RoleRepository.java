package com.cdac.project.bbms.repositories;

import com.cdac.project.bbms.domain.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,  Long> {

    Optional<Role> findByName(ERole name);
}
