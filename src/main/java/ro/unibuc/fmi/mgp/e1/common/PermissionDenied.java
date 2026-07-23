package ro.unibuc.fmi.mgp.e1.common;

public class PermissionDenied extends RuntimeException {
    public PermissionDenied(String message) {
        super(message);
    }
}
