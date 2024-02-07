package enums;

/**
 * Enumeracao que representa o estado da flag. A flag pode estar capturada, nao capturada ou capturada na base.
 * @author : André Faria
 * @author : Daniela Mendes
 */
public enum FlagStatus {

    /**
     * Flag is captured
     */
    CAPTURED,

    /**
     * Flag is not captured
     */
    NOT_CAPTURED,

    /**
     * Flag is captured in the base
     */
    CAPTURETOBASE;

    /**
     * Metodo transmite para string o estado da flag.
     * @return estado da flag
     */
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
