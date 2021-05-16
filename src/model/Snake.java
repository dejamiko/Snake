package model;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Stack;

/**
 * This class is responsible for the snake.
 *
 * @author mikolajdeja
 * @version 2021.05.14
 */
public class Snake {
    private final ArrayDeque<int[]> snake;
    private final Stack<int[]> prev;
    private char direction;
    private final static String directions = "NESW";

    /**
     * The constructor for the snake in a given coordinates.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Snake(int x, int y) {
        snake = new ArrayDeque<>();
        prev = new Stack<>();
        snake.add(new int[]{x, y});
        snake.add(new int[]{x, y + 1});
        snake.add(new int[]{x, y + 2});
        direction = 'N';
    }

    /**
     * Get the coordinates of the head of the snake.
     *
     * @return The coordinates of the head of the snake.
     */
    public int[] getHead() {
        return snake.peekFirst();
    }

    /**
     * Get the coordinates of the tail of the snake.
     *
     * @return The coordinates of the tail of the snake.
     */
    public int[] getTail() {
        return snake.peekLast();
    }

    /**
     * Get the snake (a collection of coordinates).
     *
     * @return The snake (a collection of coordinates).
     */
    public Collection<int[]> getSnake() {
        return snake;
    }

    /**
     * Turn right.
     */
    public void turnRight() {
        direction = directions.charAt((directions.indexOf(direction) + 1) % 4);
    }

    /**
     * Turn left.
     */
    public void turnLeft() {
        direction = directions.charAt((directions.indexOf(direction) + 3) % 4);
    }

    /**
     * Move forward one step.
     */
    public void moveForward() {
        int[] coords = getHead();
        prev.push(snake.removeLast());
        if (direction == 'N')
            snake.addFirst(new int[]{coords[0], coords[1] - 1});
        if (direction == 'E')
            snake.addFirst(new int[]{coords[0] + 1, coords[1]});
        if (direction == 'S')
            snake.addFirst(new int[]{coords[0], coords[1] + 1});
        if (direction == 'W')
            snake.addFirst(new int[]{coords[0] - 1, coords[1]});
    }

    /**
     * Eat an apple (and grow).
     */
    public void eat() {
        if (!prev.isEmpty())
            snake.addLast(prev.pop());
    }

    /**
     * Get the coordinates of the previous location of the snake.
     *
     * @return The coordinates of the previous location of the snake.
     */
    public int[] getPrev() {
        if (!prev.isEmpty())
            return prev.peek();
        return null;
    }

    /**
     * Get the direction the snake is moving in.
     *
     * @return The direction the snake is moving in.
     */
    public char getDirection() {
        return direction;
    }

    /**
     * Get the snake's length.
     *
     * @return The snake's length.
     */
    public int getLength() {
        return snake.size();
    }
}
