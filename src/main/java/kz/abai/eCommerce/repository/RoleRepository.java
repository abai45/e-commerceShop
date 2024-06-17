package kz.abai.eCommerce.repository;

import kz.abai.eCommerce.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
