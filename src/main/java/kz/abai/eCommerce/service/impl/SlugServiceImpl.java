package kz.abai.eCommerce.service.impl;

import kz.abai.eCommerce.service.SlugService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class SlugServiceImpl implements SlugService {
    private static final Map<Character, String> CYRILLIC_TO_LATIN_MAP = new HashMap<>();
    static {
        CYRILLIC_TO_LATIN_MAP.put('а', "a");
        CYRILLIC_TO_LATIN_MAP.put('б', "b");
        CYRILLIC_TO_LATIN_MAP.put('в', "v");
        CYRILLIC_TO_LATIN_MAP.put('г', "g");
        CYRILLIC_TO_LATIN_MAP.put('д', "d");
        CYRILLIC_TO_LATIN_MAP.put('е', "e");
        CYRILLIC_TO_LATIN_MAP.put('ё', "e");
        CYRILLIC_TO_LATIN_MAP.put('ж', "zh");
        CYRILLIC_TO_LATIN_MAP.put('з', "z");
        CYRILLIC_TO_LATIN_MAP.put('и', "i");
        CYRILLIC_TO_LATIN_MAP.put('й', "i");
        CYRILLIC_TO_LATIN_MAP.put('к', "k");
        CYRILLIC_TO_LATIN_MAP.put('л', "l");
        CYRILLIC_TO_LATIN_MAP.put('м', "m");
        CYRILLIC_TO_LATIN_MAP.put('н', "n");
        CYRILLIC_TO_LATIN_MAP.put('о', "o");
        CYRILLIC_TO_LATIN_MAP.put('п', "p");
        CYRILLIC_TO_LATIN_MAP.put('р', "r");
        CYRILLIC_TO_LATIN_MAP.put('с', "s");
        CYRILLIC_TO_LATIN_MAP.put('т', "t");
        CYRILLIC_TO_LATIN_MAP.put('у', "u");
        CYRILLIC_TO_LATIN_MAP.put('ф', "f");
        CYRILLIC_TO_LATIN_MAP.put('х', "kh");
        CYRILLIC_TO_LATIN_MAP.put('ц', "ts");
        CYRILLIC_TO_LATIN_MAP.put('ч', "ch");
        CYRILLIC_TO_LATIN_MAP.put('ш', "sh");
        CYRILLIC_TO_LATIN_MAP.put('щ', "shch");
        CYRILLIC_TO_LATIN_MAP.put('ы', "y");
        CYRILLIC_TO_LATIN_MAP.put('э', "e");
        CYRILLIC_TO_LATIN_MAP.put('ю', "yu");
        CYRILLIC_TO_LATIN_MAP.put('я', "ya");
        CYRILLIC_TO_LATIN_MAP.put('ь', "");
        CYRILLIC_TO_LATIN_MAP.put('ъ', "");

        CYRILLIC_TO_LATIN_MAP.put('ә', "a");
        CYRILLIC_TO_LATIN_MAP.put('ғ', "g");
        CYRILLIC_TO_LATIN_MAP.put('қ', "k");
        CYRILLIC_TO_LATIN_MAP.put('ң', "n");
        CYRILLIC_TO_LATIN_MAP.put('ө', "o");
        CYRILLIC_TO_LATIN_MAP.put('ұ', "u");
        CYRILLIC_TO_LATIN_MAP.put('ү', "u");
        CYRILLIC_TO_LATIN_MAP.put('і', "i");
    }

    @Override
    public String createSlug(String input) {
        StringBuilder slug = new StringBuilder();
        for (char c : input.toLowerCase().toCharArray()) {
            String latin = CYRILLIC_TO_LATIN_MAP.getOrDefault(c, String.valueOf(c));
            slug.append(latin);
        }
        return slug.toString()
                .replaceAll("[^\\w\\s-]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }
}
