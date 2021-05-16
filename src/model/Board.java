package model;

import java.util.Random;

/**
 * A class responsible for the underlying board of the snake application.
 *
 * @author mikolajdeja
 * @version 2021.05.14
 */
public class Board {
    private final State[][] board;
    private final Snake snake;

    /**
     * Initialise the board with a given width and height.
     *
     * @param width The given width.
     * @param height The given height.
     */
    public Board(int width, int height) {
        board = new State[width][height];

        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                board[i][j] = State.EMPTY;
            }
        }

        for (int i = 0; i < board.length; i++) {
            board[i][0] = State.WALL;
            board[i][board[i].length - 1] = State.WALL;
        }

        for (int j = 0; j < board[0].length; j++) {
            board[0][j] = State.WALL;
            board[board.length - 1][j] = State.WALL;
        }

        snake = new Snake(width / 2, height / 2);

        drawSnake();

        putApple();
    }

    /**
     * Move the snake forward.
     *
     * @return True if the snake is in a legal place, false otherwise.
     */
    public boolean moveForward() {
        snake.moveForward();
        if (board[snake.getHead()[0]][snake.getHead()[1]] != State.WALL && board[snake.getHead()[0]][snake.getHead()[1]] != State.BODY) {
            if (board[snake.getHead()[0]][snake.getHead()[1]] == State.APPLE) {
                snake.eat();
                drawSnake();
                putApple();
            } else {
                drawSnake();
            }
        }
        else
            return false;
        return true;
    }

    /**
     * Put the apple in a random empty position in the grid.
     */
    private void putApple() {
        int num = 0;
        for (State[] states : board) {
            for (State state : states) {
                if (state == State.EMPTY)
                    num++;
            }
        }
        Random random = new Random();
        num = random.nextInt(num);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == State.EMPTY)
                    num--;
                if (num == 0) {
                    board[i][j] = State.APPLE;
                    return;
                }
            }
        }
    }

    /**
     * Draw snake onto the board.
     */
    private void drawSnake() {
        int[] prev = snake.getPrev();
        if (prev != null)
            board[prev[0]][prev[1]] = State.EMPTY;
        for(int[] coords : snake.getSnake())
            board[coords[0]][coords[1]] = State.BODY;
        board[snake.getHead()[0]][snake.getHead()[1]] = State.HEAD;
    }

    /**
     * React to the user's clicking of the left arrow.
     */
    public void leftArrow() {
        if (snake.getDirection() == 'N')
            snake.turnLeft();
        else if (snake.getDirection() == 'S')
            snake.turnRight();
    }

    /**
     * React to the user's clicking of the right arrow.
     */
    public void rightArrow() {
        if (snake.getDirection() == 'N')
            snake.turnRight();
        else if (snake.getDirection() == 'S')
            snake.turnLeft();
    }

    /**
     * React to the user's clicking of the up arrow.
     */
    public void upArrow() {
        if (snake.getDirection() == 'E')
            snake.turnLeft();
        else if (snake.getDirection() == 'W')
            snake.turnRight();
    }

    /**
     * React to the user's clicking of the down arrow.
     */
    public void downArrow() {
        if (snake.getDirection() == 'E')
            snake.turnRight();
        else if (snake.getDirection() == 'W')
            snake.turnLeft();
    }

    /**
     * Get the state of a given cell on the board.
     *
     * @param i The given column.
     * @param j The given row.
     * @return The state of the given cell.
     */
    public State getState(int i, int j) {
        return board[i][j];
    }

    /**
     * Get the current score.
     *
     * @return The current score.
     */
    public int getScore() {
        return snake.getLength();
    }
}
