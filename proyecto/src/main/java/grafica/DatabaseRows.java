package grafica;

final class DatabaseRows {
    private DatabaseRows() {
    }

    static String[] split(String row) {
        String normalized = stripTrailingSeparator(row);
        return normalized.split(":", -1);
    }

    static String stripTrailingSeparator(String value) {
        if (value != null && value.endsWith(":")) {
            return value.substring(0, value.length() - 1);
        }
        return value;
    }
}
