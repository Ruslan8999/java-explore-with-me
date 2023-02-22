package ru.practicum.mainserver.events.model;

import ru.practicum.mainserver.exceptions.IllegalEnumStateException;

public enum State {
    PENDING,
    PUBLISHED,
    CANCELED;

    public static State stringToState(String stringState) {
        State state;
        try {
            if (stringState == null) {
                state = State.PENDING;
            } else {
                state = State.valueOf(stringState);
            }
        } catch (Exception e) {
            throw new IllegalEnumStateException("Unknown state: " + stringState);
        }
        return state;
    }
}
