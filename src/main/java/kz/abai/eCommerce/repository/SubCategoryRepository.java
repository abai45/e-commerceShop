package kz.abai.eCommerce.repository;

import kz.abai.eCommerce.entities.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {
}
