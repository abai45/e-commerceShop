package kz.abai.eCommerce.repository;

import kz.abai.eCommerce.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
