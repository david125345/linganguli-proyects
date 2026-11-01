package grafica;

final class ForeignKeyOption {
    private final String id;
    private final String label;

    ForeignKeyOption(String id, String label) {
        this.id = id;
        this.label = label;
    }

    String getId() {
        return id;
    }

    @Override
    public String toString() {
        return label;
    }
}
