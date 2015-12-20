package edu.binghamton.my;

public class MySudokuSolver {
  
    private Board board;
    private static final String[] values = {".", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
  
    public static void solve(String[][] board) {
        new MySudokuSolver(board).solve();
    }
  
    private MySudokuSolver(String[][] arr) {
        board = new Board(arr);
    }
  
    private void solve() {
        solve(listEmptyPositions());
        /*int count = solutions.size();
        if (count == 0)
            System.out.println("no solution - invalid puzzle");
        else if (count == 1)
            System.out.println("unique solution - valid puzzle");
        else {
            System.out.println(count + " solutions - invalid puzzle");
            System.out.println("\ncommon part of all solutions:");
            findCommonSolution();
            board.show();
        }*/
    }
  
    private Position listEmptyPositions() {
        Position start = new Position(-1, -1);
        Position p = start;
        int count = 0;
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                if (board.get(x, y).equalsIgnoreCase(".")) {
                	count++;
                    Position p2 = new Position(x, y);
                    p.setNext(p2);
                    p = p2;
                }
            }
        }
        System.out.println("Total empty spaces: " + count);
        return start.getNext();
    }
  
    private void solve(Position p) {
        // If no remaining empty space, puzzle is solved
        if (p == null) {
            foundSolution();
            return;
        }
  
        // Try all possibile values at this position
        int x = p.getX();
        int y = p.getY();
        System.out.println("X: " + x + " Y: " + y);
        for (int n = 1; n <= 16; n++) {
            board.set(x, y, values[n]);
            if (isLegal(x, y)) {
            	//System.out.println("Solved: " + (++solvedCount));
            	board.show();
            	solve(p.getNext());
            }
        }
        // reset
        board.set(x, y, values[0]);
        return;
    }
  
    private void foundSolution() {
        board.show();
        return;
    }
  
    private boolean isLegal(int x, int y) {
        String n = board.get(x, y);
        return isLegalColumn(x, n) && isLegalRow(y, n) && isLegalBlock(x, y, n);
    }
  
    private boolean isLegalColumn(int x, String n) {
        boolean present = false;
        for (int j = 0; j < 16; j++) {
            if (board.get(x, j).equalsIgnoreCase(n)) {
                if (present)
                    return false;
                present = true;
            }
        }
        return true;
    }
  
    private boolean isLegalRow(int y, String n) {
        boolean present = false;
        for (int i = 0; i < 16; i++) {
            if (board.get(i, y).equalsIgnoreCase(n)) {
                if (present)
                    return false;
                present = true;
            }
        }
        return true;
    }
  
    private boolean isLegalBlock(int x, int y, String n) {
        boolean present = false;
        int x1 = x - (x % 4);
        int x2 = x1 + 4;
        int y1 = y - (y % 4);
        int y2 = y1 + 4;
  
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                if (board.get(i, j).equalsIgnoreCase(n)) {
                    if (present)
                        return false;
                    present = true;
                }
            }
        }
        return true;
    }
  
    public static void main(String[] argv) {
  
        String board[][] = {
                {"1", ".", ".", "6", "D", "C", "B", ".", ".", ".", ".", ".", "F", "A", "5", "8"},
                {".", ".", "0", ".", ".", "1", "F", ".", ".", ".", ".", "A", "2", ".", ".", "6"},
                {".", "E", ".", ".", "7", "9", ".", "A", ".", "8", ".", ".", "D", "C", "B", "."},
                {".", ".", ".", ".", ".", ".", "5", ".", ".", ".", "1", ".", "E", "4", ".", "."},
                
                {"3", ".", ".", ".", "1", "F", "A", ".", ".", "B", ".", ".", ".", ".", ".", "5"},
                {".", "9", ".", ".", "2", "E", ".", "3", "4", "A", ".", ".", "B", ".", ".", "."},
                {".", ".", ".", "C", ".", ".", "9", "B", ".", "E", "D", ".", "A", ".", ".", "4"},
                {"B", "0", ".", "F", ".", ".", ".", ".", ".", ".", ".", "C", ".", ".", ".", "."},
                
                {"C", ".", ".", ".", "3", "8", ".", ".", "B", ".", "5", ".", "4", "9", "7", "A"},
                {".", "5", ".", ".", "4", ".", ".", "F", "3", "7", ".", ".", ".", "E", ".", "."},
                {".", "F", ".", "8", "E", ".", ".", "D", "9", ".", "6", ".", ".", "B", ".", "C"},
                {".", ".", ".", "0", ".", ".", ".", "9", "8", ".", ".", "E", "3", ".", "D", "F"},
                
                {"E", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "F", ".", "."},
                {"4", ".", "6", "7", ".", ".", ".", ".", "E", "F", ".", ".", "C", ".", ".", "D"},
                {".", "B", ".", ".", ".", ".", ".", ".", ".", "4", ".", ".", ".", "7", "8", "."},
                {"9", "D", ".", ".", ".", "7", ".", ".", "A", ".", "C", "B", "1", "2", "4", "E"},
        };
  
        long t1 = System.currentTimeMillis();
        solve(board);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}
