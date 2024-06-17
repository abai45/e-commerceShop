package kz.abai.eCommerce.utils;

import jakarta.servlet.http.HttpServletRequest;
import kz.abai.eCommerce.domain.Response;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.apache.logging.log4j.util.Strings.EMPTY;

public class RequestUtils {
    public static Response getResponse(HttpServletRequest request, Map<?, ?> data, String message, HttpStatus status) {
        return new Response(now().toString(), status.value(), request.getRequestURI(), HttpStatus.valueOf(status.value()), message, EMPTY, data);
    }
}
