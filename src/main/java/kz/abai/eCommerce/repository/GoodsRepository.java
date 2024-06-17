package kz.abai.eCommerce.repository;

import kz.abai.eCommerce.entities.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {
}
