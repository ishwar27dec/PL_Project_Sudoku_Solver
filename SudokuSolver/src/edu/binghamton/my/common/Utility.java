package edu.binghamton.my.common;

import static edu.binghamton.my.common.Constants.BOARD_SIZE;

import java.util.ArrayList;
import java.util.List;

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

	public static String getRepresentableSudokuBoard(String inputPuzzle) {
		int index = 0;
		List<String> data = new ArrayList<>();
		for(int i = 0; i < BOARD_SIZE; i++) {
			if (i % 4 == 0)
                data.add("+-------+-------+-------+-------+\n");

			for(int j = 0; j < BOARD_SIZE; j++) {
				data.add((j % 4 == 0 ? "|" : " "));
				data.add(String.valueOf(inputPuzzle.charAt(index++)));
			}

			data.add("|\n");
		}
		data.add("+-------+-------+-------+-------+\n");
		
		String oneLineString = "";
		for(int i = 0; i < data.size(); i++)
			oneLineString += data.get(i);

		return oneLineString;
	}
}
