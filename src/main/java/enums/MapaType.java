package enums;

/**
 * Enumeracao que representa o tipo de mapa. O mapa pode ser unidirecional ou bidirecional.
 */
public enum MapaType {

    /**
     * Mapa unidirecional
     */
    MAPA_UNIDIRECIONAL,
    /**
     * Mapa bidirecional
     */
    MAPA_BIDIRECIONAL;

    /**
     * Metodo que transmite para string o tipo de mapa.
     * @return tipo de mapa
     */
    @Override
    public String toString() {
        switch(this) {
            case MAPA_UNIDIRECIONAL: return "Mapa Unidirecional";
            case MAPA_BIDIRECIONAL: return "Mapa Bidirecional";
            default: throw new IllegalArgumentException();
        }
    }
}
