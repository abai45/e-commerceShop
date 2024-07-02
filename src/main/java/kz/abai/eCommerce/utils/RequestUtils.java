package kz.abai.eCommerce.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.abai.eCommerce.domain.ApiAuthentication;
import kz.abai.eCommerce.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.security.auth.login.CredentialExpiredException;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyMap;
import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class RequestUtils {
    private static final BiConsumer<HttpServletResponse, Response> writeResponse = (httpServletResponse, response) -> {
        try {
            var outputSream = httpServletResponse.getOutputStream();
            new ObjectMapper().writeValue(outputSream, response);
            outputSream.flush();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    };
    private static final BiFunction<Exception, HttpStatus, String> errorReason = (exception, status) -> {
        if(status.isSameCodeAs(FORBIDDEN)) {
            return "You Do not have enough permission";
        }
        if(status.isSameCodeAs(UNAUTHORIZED)) {
            return "You are not logged in";
        }
        if(exception instanceof LockedException || exception instanceof DisabledException || exception instanceof BadCredentialsException || exception instanceof CredentialExpiredException || exception instanceof RuntimeException) {
            return exception.getMessage();
        }
        if(status.is5xxServerError()) {
            return "An internal server error occurred";
        } else {
            return "An error occurred. Please try again";
        }
    };
    public static Response getResponse(HttpServletRequest request, Map<?, ?> data, String message, HttpStatus status) {
        return new Response(now().toString(), status.value(), request.getRequestURI(), HttpStatus.valueOf(status.value()), message, EMPTY, data);
    }
    public static void handleErrorResponse(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        if(exception instanceof AccessDeniedException) {
            Response apiResponse = getErrorResponse(request, response, exception, FORBIDDEN);
            writeResponse.accept(response, apiResponse);
        }
    }

    private static Response getErrorResponse(HttpServletRequest request, HttpServletResponse response, Exception exception, HttpStatus httpStatus) {
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(httpStatus.value());
        return new Response(now().toString(), httpStatus.value(), request.getRequestURI(), HttpStatus.valueOf(httpStatus.value()), errorReason.apply(exception, httpStatus), getRootCauseMessage(exception), emptyMap());
    }
}
