package kz.abai.eCommerce.service.impl;

import kz.abai.eCommerce.dto.ClientDto;
import kz.abai.eCommerce.enums.AuthorityEnum;
import kz.abai.eCommerce.mapper.ClientMapper;
import kz.abai.eCommerce.repository.ClientRepository;
import kz.abai.eCommerce.repository.RoleRepository;
import kz.abai.eCommerce.service.ClientService;
import kz.abai.eCommerce.utils.ClientUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final ClientUtils clientUtils;
    private final ClientMapper clientMapper;

    @Override
    public ClientDto createNewClient(String firstName, String lastName, String email, String phone, String password) {
        var role = roleRepository.findByNameIgnoreCase(AuthorityEnum.USER.name())
                .orElseThrow(() -> new RuntimeException("Role By Name not found"));
        var newClient = clientUtils.createNewClientEntity(firstName, lastName, email, password, phone, role);
        var clientDto = clientMapper.INSTANCE.toDto(newClient);
        return clientDto;
    }
}
