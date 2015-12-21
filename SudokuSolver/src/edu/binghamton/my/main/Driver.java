package edu.binghamton.my.main;

import static edu.binghamton.my.common.Constants.*;
import static edu.binghamton.my.common.Utility.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Driver {

	private static String INPUT_STRING;
	private static BufferedWriter OUT_WRITER = null;

	private void execute() {
		BufferedReader reader = null;
		echo(START_MESSGAE, true);

		boolean keepGoing = true;
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			OUT_WRITER = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));

			while(keepGoing) { //It's and infinite loop until user hits Exit option
				//Display selection menu
				printMenu();

				int choice = Integer.parseInt(reader.readLine());
				switch (choice) {
				case 1: //Enter input file name
					echo(ENTER_INPUT_MESSAGE, false);

					String inputFileName = reader.readLine();
					boolean success = readInputFile(inputFileName);

					if(success) {
						echo(getRepresentableSudokuBoard(INPUT_STRING), true);
						echo(INPUT_TAKEN_MESSAGE, true);
					} else {
						echoError(FILE_READ_ERROR_MESSAGE);
						INPUT_STRING = null;
					}
					break;

				case 2: //Solve Sudoku puzzle
					if(INPUT_STRING == null) {
						echo(ENTER_INPUT_PUZZLE_MESSAGE, true);
						break;
					}

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
			allExceptions.printStackTrace();
			echoError(ERROR_MESSAGE + allExceptions.getMessage());
		} finally {
			try {
				reader.close();
				OUT_WRITER.close();
			} catch(Exception e) {
				//
			}
		}
	}

	private boolean readInputFile(String inputFileName) {
		File file = null;
		BufferedReader reader = null;
		try {
			file = new File(inputFileName);
			reader = new BufferedReader(new FileReader(file));
			String temp = null;
			while((temp = reader.readLine()) != null) {
				INPUT_STRING = temp;
			}
		} catch(IOException ioe) {
			echoError(ioe.getMessage());
			return false;
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
				//Do nothing
			}
		}
		return true;
	}

	private void solve(int solveBy) throws IOException {
		long startTime = System.currentTimeMillis();
		long endTime, timeTaken;
		String solvedPuzzle = "", solver = "";
		switch (solveBy) {
		case 1:
			solver = JAVA_MESSAGE;
			solvedPuzzle = solveJava();
			break;
		case 2:
			solver = JS_MESSAGE;
			solvedPuzzle = solveJS();
			break;
		case 3:
			solver = PYTHON_MESSAGE;
			solvedPuzzle = solvePython();
			break;
		case 4:
			solver = C_MESSAGE;
			solvedPuzzle = solveC();
			break;
		case 5:
			solver = PROLOG_MESSAGE;
			solvedPuzzle = solveProlog();
			break;
		default:
			break;
		}

		endTime = System.currentTimeMillis();
		timeTaken = (endTime - startTime);
		outputSolvedPuzzle(solver, solvedPuzzle, timeTaken);
	}

	private String solveProlog() {
		echo(PROLOG_MESSAGE, true);
		return null;
	}

	private String solveC() {
		echo(C_MESSAGE, true);
		return null;
	}

	private String solvePython() {
		echo(PYTHON_MESSAGE, true);
		String outputString = "";
		try {
			ProcessBuilder pb = new ProcessBuilder("python", "hexadoku.py", INPUT_STRING);
			Process p = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String output = "";
			List<String> outputList = new ArrayList<>();
			while((output = reader.readLine()) != null) {
				output = output.replaceAll("\\+", "").replaceAll("\\|", "").replaceAll("\\-", "").replaceAll(" ", "");
				if(!"".equalsIgnoreCase(output))
					outputList.add(output);
			}

			int returnValue = p.waitFor();
			if(returnValue == 0) { //Successful termination
				for(int i = 0; i < outputList.size(); i++)
					outputString += outputList.get(i);
			} else {
				echoError(PYTHON_INTEGRATION_ERROR);
			}

		} catch(Exception e) {
			e.printStackTrace();
			echoError(e.getMessage());
		}
		
		return outputString;
	}

	private String solveJS() {
		echo(JS_MESSAGE, true);
		return null;
	}

	private String solveJava() {
		echo(JAVA_MESSAGE, true);
		Hexadoku hexadoku = new Hexadoku(INPUT_STRING);
		String solvedString = hexadoku.solve();
		return solvedString;
	}

	private void outputSolvedPuzzle(String solver, String solvedPuzzle, long timeTakenInMillis) throws IOException {
		double thousand = 1000;
		double timeTakenInSeconds = timeTakenInMillis / thousand;
		String timeTakenMessage = TIME_TAKEN_MESSAGE + timeTakenInSeconds + " sec\n"; 
		solvedPuzzle = getRepresentableSudokuBoard(solvedPuzzle);
		echo(solvedPuzzle, true);
		echo(timeTakenMessage, true);

		OUT_WRITER.write(solver);
		OUT_WRITER.write(solvedPuzzle);
		OUT_WRITER.write(timeTakenMessage);

		OUT_WRITER.flush();
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
