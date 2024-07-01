package kz.abai.eCommerce.service.impl;

import kz.abai.eCommerce.cache.CacheStore;
import kz.abai.eCommerce.domain.RequestContext;
import kz.abai.eCommerce.dto.ClientDto;
import kz.abai.eCommerce.entities.ClientEntity;
import kz.abai.eCommerce.enums.AuthorityEnum;
import kz.abai.eCommerce.enums.LoginType;
import kz.abai.eCommerce.mapper.ClientMapper;
import kz.abai.eCommerce.repository.ClientRepository;
import kz.abai.eCommerce.repository.RoleRepository;
import kz.abai.eCommerce.service.ClientService;
import kz.abai.eCommerce.utils.ClientUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final ClientUtils clientUtils;
    private final CacheStore<String, Integer> cacheStore;
    private final ClientMapper clientMapper;

    @Override
    public ClientDto createNewClient(String firstName, String lastName, String email, String phone, String password) {
        clientUtils.initRoles();
        var role = roleRepository.findByNameIgnoreCase(AuthorityEnum.USER.name())
                .orElseThrow(() -> new RuntimeException("Role By Name not found"));
        var newClient = clientRepository.save(clientUtils.createNewClientEntity(firstName, lastName, email, password, phone, role));
        var clientDto = clientMapper.INSTANCE.toDto(newClient);
        return clientDto;
    }

    @Override
    public void updateLoginAttempt(String email, LoginType loginType) {
        var clientEntity = getClientByEmail(email);
        RequestContext.setClientId(clientEntity.getId());
        switch (loginType) {
            case LOGIN_ATTEMPT -> {
                if(cacheStore.get(clientEntity.getEmail()) == null) {
                    clientEntity.setLoginAttempts(0);
                    clientEntity.setAccountNonLocked(true);
                }
                clientEntity.setLoginAttempts(clientEntity.getLoginAttempts()+1);
                cacheStore.put(clientEntity.getEmail(), clientEntity.getLoginAttempts());
                if(cacheStore.get(clientEntity.getPhone()) > 3) {
                    clientEntity.setAccountNonLocked(false);
                }
            }
            case LOGIN_SUCCESS -> {
                clientEntity.setAccountNonLocked(true);
                clientEntity.setLoginAttempts(0);
                clientEntity.setLastLogin(now());
                cacheStore.evict(clientEntity.getEmail());
            }
        }
        clientRepository.save(clientEntity);
    }

    private ClientEntity getClientByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User by email not found"));
    }
}
