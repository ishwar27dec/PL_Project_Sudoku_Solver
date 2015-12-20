package edu.binghamton.my;

import java.util.Arrays;

public class Board {
	private final String[] array = new String[256];
	  
    private Board() { }
  
    public Board(String[][] values) {
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
                array[16 * j + i] = values[i][j];
    }
  
    public String get(int i, int j) { return array[16 * j + i]; }
  
    public void set(int i, int j, String n) { array[16 * j + i] = n; }
  
    public boolean equals(Object obj) {
        return obj instanceof Board && Arrays.equals(array, ((Board) obj).array);
    }
  
    public int hashCode() {
        return Arrays.hashCode(array);
    }
  
    public Board copy() {
        Board b = new Board();
        System.arraycopy(array, 0, b.array, 0, array.length);
        return b;
    }
  
    public void show() {
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 0)
                System.out.println("+-------+-------+-------+-------+");
            for (int j = 0; j < 16; j++) {
                System.out.print((j % 4 == 0 ? "|" : " "));
                //int n = get(i, j);
                //System.out.print(n == 0 ? " " : n);
                String n = get(i, j);
                System.out.print(n);
            }
            System.out.println("|");
        }
        System.out.println("+-------+-------+-------+-------+");
    }
}
