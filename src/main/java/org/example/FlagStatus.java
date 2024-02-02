package org.example;

public enum FlagStatus {

    CAPTURED,
    NOT_CAPTURED;

    @Override
    public String toString() {
        switch(this) {
            case CAPTURED: return "Flag is captured";
            case NOT_CAPTURED: return "Flag is not captured";
            default: throw new IllegalArgumentException();
        }
    }
}
