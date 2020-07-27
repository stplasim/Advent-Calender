package net.bitnt.advent.calender;

public enum DayStatus {
    NONE(0),
    EMPTY(1),
    READY(2);

    private final int value;

    /**
     * Day status enum constructor
     *
     * @param value - Numeric value
     */
    private DayStatus(int value) {
        this.value = value;
    }

    /**
     * Get numeric value for enum state
     *
     * @return int
     */
    public int getValue() {
        return value;
    }

    /**
     * Get enum state for numeric value
     *
     * @param value - State id
     * @return DayStatus
     */
    public static DayStatus getStatus(int value) {
        for(DayStatus s : values()) {
            if(s.getValue() == value) {
                return s;
            }
        }
        return null;
    }
}
