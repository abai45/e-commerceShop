package kz.abai.eCommerce.domain;

public class RequestContext {
    private static final ThreadLocal<Long> CLIENT_ID = new ThreadLocal<>();
    private RequestContext() {}
    public static void start() {
        CLIENT_ID.remove();
    }
    public static void setClientId(Long clientId) {
        CLIENT_ID.set(clientId);
    }
    public static Long getClientId() {
        return CLIENT_ID.get();
    }
}
