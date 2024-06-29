package kz.abai.eCommerce.repository;

import kz.abai.eCommerce.entities.CategoryEntity;
import kz.abai.eCommerce.entities.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {
    Optional<GoodsEntity> findByGoodId(String goodId);
    Optional<GoodsEntity> findAllByCategory(CategoryEntity category);
}
