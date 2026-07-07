package ro.unibuc.fmi.mgp.e1.common;

public class PermisionDenied extends RuntimeException {
    public PermisionDenied(String message) {
        super(message);
    }
}
