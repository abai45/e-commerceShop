package kz.abai.eCommerce.service;

import kz.abai.eCommerce.dto.ClientDto;
import kz.abai.eCommerce.enums.LoginType;

public interface ClientService {
    ClientDto createNewClient(String firstName, String lastName, String email, String phone, String password);
    void updateLoginAttempt(String email, LoginType loginType);
}
