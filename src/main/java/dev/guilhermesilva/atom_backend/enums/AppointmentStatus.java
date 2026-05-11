package dev.guilhermesilva.atom_backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AppointmentStatus {
    AGENDADO("AGENDADO", "SCHEDULED"),
    CANCELADO("CANCELADO", "CANCELLED"),
    FINALIZADO("FINALIZADO", "COMPLETED");

    private final String value;
    private final String alt;

    AppointmentStatus(String value, String alt) {
        this.value = value;
        this.alt = alt;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static AppointmentStatus fromString(String v) {
        if (v == null) return null;
        String s = v.trim().toUpperCase();
        for (AppointmentStatus status : AppointmentStatus.values()) {
            if (status.value.equalsIgnoreCase(s) || status.alt.equalsIgnoreCase(s)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown AppointmentStatus: " + v);
    }

    public String getAlt() {
        return alt;
    }
}