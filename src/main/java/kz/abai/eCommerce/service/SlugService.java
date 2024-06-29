package kz.abai.eCommerce.service;

import com.github.slugify.Slugify;
import org.springframework.stereotype.Service;

@Service
public class SlugService {
    private final Slugify slugify;

    public SlugService() {
        this.slugify = Slugify.builder().build();
    }

    public String createSlug(String input) {
        return slugify.slugify(input);
    }
}
