package kz.abai.eCommerce.service.impl;

import kz.abai.eCommerce.repository.ClientRepository;
import kz.abai.eCommerce.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public void createNewClient(String firstName, String lastName, String email, String password, String phone) {

    }
}
