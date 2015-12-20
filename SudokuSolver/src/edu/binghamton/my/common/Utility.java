package edu.binghamton.my.common;

import static edu.binghamton.my.common.Constants.BOARD_SIZE;;

public class Utility {

	public static void echo(String message, boolean addLineBreak) {
		if(addLineBreak)
			System.out.println(message);
		else
			System.out.print(message);
	}

	public static void echoError(String errorMessage) {
		System.err.println(errorMessage);
	}

	public static void printSudokuBoard(String inputPuzzle) {
		int index = 0;
		for(int i = 0; i < BOARD_SIZE; i++) {
			if (i % 4 == 0)
                echo("+-------+-------+-------+-------+", true);

			for(int j = 0; j < BOARD_SIZE; j++) {
                echo((j % 4 == 0 ? "|" : " "), false);
				echo("" + inputPuzzle.charAt(index++), false);
			}

			echo("|", true);
		}
        echo("+-------+-------+-------+-------+", true);
	}
}
