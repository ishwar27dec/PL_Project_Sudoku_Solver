package edu.binghamton.my.main;

import static edu.binghamton.my.common.Utility.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static edu.binghamton.my.common.Constants.*;

public class Driver {

	private static String INPUT_STRING;

	private void execute() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		echo(START_MESSGAE, true);

		boolean keepGoing = true;
		try {
			while(keepGoing) {
				printMenu();
				int choice = Integer.parseInt(reader.readLine());
				switch (choice) {
				case 1: //Enter input string
					echo(ENTER_INPUT_MESSAGE, false);
					INPUT_STRING = reader.readLine();
					printSudokuBoard(INPUT_STRING);
					echo(INPUT_TAKEN_MESSAGE, true);
					break;

				case 2: //Solve Sudoku puzzle
					boolean keepSolving = true;
					echo(SUB_MENU_TITLE_MESSAGE, false);
					while(keepSolving) {
						printSubMenu();
						int option = Integer.parseInt(reader.readLine());
						switch (option) {
						case 1:
						case 2:
						case 3:
						case 4:
						case 5:
							solve(option);
							break;

						case 6:
							keepSolving = false;
							break;

						default:
							break;
						}
					}
					break;

				case 3:
					echo(END_MESSAGE, true);
					keepGoing = false;
					break;
					
				default:
					break;
				}
			}
		} catch(Exception allExceptions) {
			echoError(ERROR_MESSAGE + allExceptions.getMessage());
		} finally {
			try {
				reader.close();
			} catch(Exception e) {
				//
			}
		}
	}

	private void solve(int solveBy) {
		long startTime = System.currentTimeMillis();
		long endTime;
		switch (solveBy) {
		case 1:
			solveJava();
			break;
		case 2:
			solveJS();
			break;
		case 3:
			solvePython();
			break;
		case 4:
			solveC();
			break;
		case 5:
			solveProlog();
			break;
		default:
			break;
		}

		endTime = System.currentTimeMillis();
		printTimeInSeconds((endTime - startTime));
	}

	private void solveProlog() {
		echo(PROLOG_MESSAGE, true);		
	}

	private void solveC() {
		echo(C_MESSAGE, true);		
	}

	private void solvePython() {
		echo(PYTHON_MESSAGE, true);
	}

	private void solveJS() {
		echo(JS_MESSAGE, true);
	}

	private void solveJava() {
		echo(JAVA_MESSAGE, true);
		Hexadoku hexadoku = new Hexadoku(INPUT_STRING);
		hexadoku.solve();
	}

	private void printTimeInSeconds(long timeTakenInMillis) {
		double thousand = 1000;
		double timeTakenInSeconds = timeTakenInMillis / thousand;
		echo(TIME_TAKEN_MESSAGE + timeTakenInSeconds + " sec", true);
	}

	private void printSubMenu() {
		echo(SOLVE_SUB_MENU, true);
		echo(CHOICE_MESSAGE, false);
	}

	private void printMenu() {
		echo(START_MENU, true);
		echo(CHOICE_MESSAGE, false);
	}

	private static void doExecute() {
		(new Driver()).execute();
	}

	public static void main(String[] args) {
		doExecute();
	}

}
