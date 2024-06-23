package kz.abai.eCommerce.utils;

import jakarta.annotation.PostConstruct;
import kz.abai.eCommerce.entities.ClientEntity;
import kz.abai.eCommerce.entities.RoleEntity;
import kz.abai.eCommerce.enums.AuthorityEnum;
import kz.abai.eCommerce.repository.ClientRepository;
import kz.abai.eCommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@Component
@RequiredArgsConstructor
public class ClientUtils {
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;

    public ClientEntity createNewClientEntity(String firstname, String lastname, String email, String password, String phone, RoleEntity role) {
        return ClientEntity.builder()
                .userId(UUID.randomUUID().toString())
                .firstName(firstname)
                .lastName(lastname)
                .email(email)
                .password(encoder.encode(password))
                .accountNonExpired(true)
                .enabled(false)
                .accountNonLocked(true)
                .phone(phone)
                .roles(role)
                .imageUrl("https://static.wikia.nocookie.net/avatar/images/1/10/%D0%9A1%D1%8509_%D0%92%D0%B7%D1%80%D0%BE%D1%81%D0%BB%D1%8B%D0%B9_%D0%90%D0%B0%D0%BD%D0%B3.jpg/revision/latest/scale-to-width/360?cb=20150327073432&path-prefix=ru")
                .balance(BigDecimal.ZERO)
                .lastLogin(now())
                .build();
    }
    @PostConstruct
    public void initRoles() {
        createRoleIfNotFound(AuthorityEnum.USER);
        createRoleIfNotFound(AuthorityEnum.MANAGER);
        createRoleIfNotFound(AuthorityEnum.ADMIN);
    }

    private void createRoleIfNotFound(AuthorityEnum roleEnum) {
        if(!roleRepository.existsByName(roleEnum.name())) {
            RoleEntity role = new RoleEntity();
            role.setName(roleEnum.name());
            role.setAuthorities(roleEnum);
            roleRepository.save(role);
        }
    }
}
