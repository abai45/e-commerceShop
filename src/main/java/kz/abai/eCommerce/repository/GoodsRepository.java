package kz.abai.eCommerce.repository;

import kz.abai.eCommerce.entities.CategoryEntity;
import kz.abai.eCommerce.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findAllByCategory(CategoryEntity category);
    Optional<ProductEntity> findBySlug(String slug);
}
