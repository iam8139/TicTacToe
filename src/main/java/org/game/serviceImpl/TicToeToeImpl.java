package org.game.serviceImpl;

import org.game.service.MatrixGame;

import java.util.InputMismatchException;

public class TicToeToeImpl implements MatrixGame {
    private int count = 0;
    private String user1;
    private String user2;
    private char[][] matrix = new char[3][3];
    public TicToeToeImpl() {
        System.out.println("Welcome to this Amazing Game!!");
        System.out.println("INSTRUCTIONS::Each Player have to select a box. Enter a number between 1-9 to mark the box.");
    }
    @Override
    public int getCount() {
        return count;
    }
    @Override
    public void setUser(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    @Override
    public void mark(int index) {
        if (user1 == null || user2 == null) throw new InputMismatchException("You are not allowed to play the game. First set the usernames...");
        if (index < 1 || index > 9) throw new IllegalArgumentException("Please Enter a valid Number");

        Value value = getValue(index);
        if (matrix[value.row][value.col] == getFirstCharacter(user1) ||
                matrix[value.row][value.col] == getFirstCharacter(user2)) {
            System.out.println("Sorry, this move is not allowed. Selected box is already filled...");
            return;
        }

        if (count%2==0) {
            matrix[value.row][value.col] = getFirstCharacter(user1);
            System.out.println("marked for " + user1);
        }
        else{
            matrix[value.row][value.col] = getFirstCharacter(user2);
            System.out.println("marked for " + user2);
        }
        count++;
    }
    @Override
    public boolean won(int index) {
        return checkRow(index) || checkCol(index) || checkDiagonals(index);
    }
    private boolean checkRow(int index) {
        Value value = getValue(index);
        int count1 = 0;
        for (int i = 0; i < 3; i++) {
            if (matrix[value.row][i] == matrix[value.row][value.col]) {
                count1++;
            } else return false;
        }
        return count1==3;
    }
    private boolean checkCol(int index) {
        Value value = getValue(index);
        int tempCount = 0;
        for (int i = 0; i < 3; i++) {
            if (matrix[i][value.col] == matrix[value.row][value.col]) {
                tempCount++;
            } else return false;
        }
        return tempCount==3;
    }
    private boolean checkDiagonals(int index) {
        if (index == 2 || index == 4 || index==6 || index==8) return false;

        Value value = getValue(index);
        if (index == 5) {
            return (matrix[value.row-1][value.col+1] == matrix[value.row][value.col]
                    && matrix[value.row+1][value.col-1] == matrix[value.row][value.col]) ||
                    (matrix[value.row-1][value.col-1] == matrix[value.row][value.col]
                            && matrix[value.row+1][value.col+1] == matrix[value.row][value.col]);
        }

        if (index == 1) {
            return (matrix[value.row+1][value.col+1] == matrix[value.row][value.col] &&
                    matrix[value.row+2][value.col+2] == matrix[value.row][value.col]);
        }

        if (index == 3) {
            return (matrix[value.row+1][value.col-1] == matrix[value.row][value.col] &&
                    matrix[value.row+2][value.col-2] == matrix[value.row][value.col]);
        }

        if (index == 7) {
            return (matrix[value.row-1][value.col+1] == matrix[value.row][value.col] &&
                    matrix[value.row-2][value.col+2] == matrix[value.row][value.col]);
        }

        if (index == 9) {
            return (matrix[value.row-1][value.col-1] == matrix[value.row][value.col] &&
                    matrix[value.row-2][value.col-2] == matrix[value.row][value.col]);
        }

        return false;
    }
    private char getFirstCharacter(String username) {
        return Character.toUpperCase(username.charAt(0));
    }
    @Override
    public void checkStatus() {
        System.out.println("Current Game Status");
        for (int i=0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    private Value getValue(int index) {
        return switch (index) {
            case 2 -> new Value(0, 1);
            case 3 -> new Value(0, 2);
            case 4 -> new Value(1, 0);
            case 5 -> new Value(1, 1);
            case 6 -> new Value(1, 2);
            case 7 -> new Value(2, 0);
            case 8 -> new Value(2, 1);
            case 9 -> new Value(2, 2);
            default -> new Value(0, 0);
        };
    }
    static class Value {
        private final int row;
        private final int col;
        public Value(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
