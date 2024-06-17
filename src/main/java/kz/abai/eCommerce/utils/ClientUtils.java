package kz.abai.eCommerce.utils;

import kz.abai.eCommerce.entities.ClientEntity;
import kz.abai.eCommerce.entities.RoleEntity;
import kz.abai.eCommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientUtils {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientUtils(ClientRepository clientRepository, BCryptPasswordEncoder encoder) {
        this.clientRepository = clientRepository;
    }
    public static ClientEntity createNewClientEntity(String firstname, String lastname, String email, String password, String phone, RoleEntity role) {
        return ClientEntity.builder()
                .userId(UUID.randomUUID().toString())
                .firstName(firstname)
                .lastName(lastname)
                .email(email)
                .build();
    }
}
