package api;

public enum MapaType {

    MAPA_UNIDIRECIONAL, MAPA_BIDIRECIONAL;

    @Override
    public String toString() {
        switch(this) {
            case MAPA_UNIDIRECIONAL: return "Mapa Unidirecional";
            case MAPA_BIDIRECIONAL: return "Mapa Bidirecional";
            default: throw new IllegalArgumentException();
        }
    }
}
