package net.crimsoncube.compatibility.repository;

import net.crimsoncube.compatibility.entity.ERole;
import net.crimsoncube.compatibility.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
