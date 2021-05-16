package model;

import javafx.scene.paint.Color;

/**
 * This enum holds all possible states and corresponding colours.
 *
 * @author mikolajdeja
 * @version 2021.05.14
 */
public enum State {
    HEAD(Color.color(26.0 / 255, 71.0 / 255, 42.0 / 255)),
    BODY(Color.color(42.0 / 255, 98.0 / 255, 61.0 / 255)),
    EMPTY(Color.TRANSPARENT),
    APPLE(Color.color(153.0 / 255, 2.0 / 255, 5.0 / 255)),
    WALL(Color.BLACK);

    private final Color color;

    /**
     * Constructor for the enum elements.
     *
     * @param color The given colour.
     */
    State(Color color) {
        this.color = color;
    }

    /**
     * Get the colour corresponding to a given state.
     *
     * @return The colour corresponding to a given state.
     */
    public Color getColor() {
        return color;
    }
}
