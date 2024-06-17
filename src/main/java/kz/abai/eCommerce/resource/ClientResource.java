package kz.abai.eCommerce.resource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kz.abai.eCommerce.domain.Response;
import kz.abai.eCommerce.dto.ClientRequestDto;
import kz.abai.eCommerce.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Collections.emptyMap;
import static kz.abai.eCommerce.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/client"})
@RequiredArgsConstructor
public class ClientResource {
    private final ClientService clientService;
    
    @PostMapping("/register")
    public ResponseEntity<Response> registerClient(@RequestBody @Valid ClientRequestDto client, HttpServletRequest request) {
        clientService.createNewClient(client.getFirstName(), client.getLastName(), client.getEmail(), client.getEmail(), client.getPhone(), client.getPassword());
        return ResponseEntity.ok().body(getResponse(request, emptyMap(), "Registered", OK));
    }
}
