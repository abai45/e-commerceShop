package kz.abai.eCommerce.service;

import kz.abai.eCommerce.dto.ClientDto;

public interface ClientService {
    ClientDto createNewClient(String firstName, String lastName, String email, String phone, String password);
}
