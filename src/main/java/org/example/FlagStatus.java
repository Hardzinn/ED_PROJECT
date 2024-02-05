package org.example;

public enum FlagStatus {

    CAPTURED,
    NOT_CAPTURED,
    CAPTURETOBASE;

    @Override
    public String toString() {
        switch(this) {
            case CAPTURED: return "Flag is captured";
            case NOT_CAPTURED: return "Flag is not captured";
            case CAPTURETOBASE: return "Flag is captured in the base";
            default: throw new IllegalArgumentException();
        }
    }
}
