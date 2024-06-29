package kz.abai.eCommerce.service;

import org.springframework.stereotype.Service;

@Service
public interface SlugService {
    String createSlug(String input);
}
