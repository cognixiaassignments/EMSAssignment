package com.collabera.jump.ems.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtil {

	public static enum TYPES {
		INT, DECIMAL, WORD, LINE, DATE
	};

	private static Scanner scanner = new Scanner(System.in);

	private static Integer readInteger(String message, int range) {
		// printMessage(message);
		int result = 0;
		boolean flag = false;
		do {
			printMessage(message);
			if (scanner.hasNextInt()) {
				result = scanner.nextInt();
				scanner.nextLine();
				flag = false;
			} else {
				scanner.next();
				printMessage("Please try again!");
				flag = true;
			}

			if (range != -1 && result > range) {
				// scanner.next();
				printMessage("Please enter a valid selection!");
				flag = true;
			}

		} while (flag);
		return result;
	}

	private static Double readDecimal(String message){

		// double result = scanner.nextDouble();
		double result = 0.0;
		boolean flag = false;
		do {
			printMessage(message);
			if (scanner.hasNextDouble()) {
				result = scanner.nextDouble();
				scanner.nextLine();
				flag = false;
			} else {
				scanner.next();
				printMessage("Invalid input, please try again!");
				flag = true;
			}

		} while (flag);
		return result;

	}

	private static Date readDate(String message) {
		boolean flag = false;
		Date date = null;
		do {
			printMessage(message);
			String result = scanner.nextLine();

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			try {
				date = dateFormat.parse(result);
				flag = false;
			} catch (ParseException e) {
				printMessage("Please enter a date in Valid format!");
				flag = true;
			}
		} while (flag);

		return date;
	}

	private static void printMessage(String message) {
		System.out.println(message);
	}

	private static String readLine(String message){
		printMessage(message);
		String result = scanner.nextLine();
		return result;
	}

	private static String readWord(String message) {
		printMessage(message);
		String result = scanner.next();
		return result;
	}

	public static Result retryUntilSucceeds(String message, TYPES type, int range) {
		Result result;

		switch (type) {
		case INT:
			result = new Result(TYPES.INT, readInteger(message, range));
			break;
		case DECIMAL:
			result = new Result(TYPES.DECIMAL, readDecimal(message));
			break;
		case WORD:
			result = new Result(TYPES.WORD, readWord(message));
			break;
		case LINE:
			result = new Result(TYPES.LINE, readLine(message));
			break;
		case DATE:
			result = new Result(TYPES.DATE, readDate(message));
			break;
		default:
			throw new InputMismatchException("Something went terribly wrong");
			// break;
		}

		return result;
	}

	public static class Result {
		private TYPES type;
		private Object value;

		public Result(TYPES type, Object value) {
			super();
			this.type = type;
			this.value = value;
		}

		public TYPES getType() {
			return type;
		}

		public void setType(TYPES type) {
			this.type = type;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public Integer getValueAsInt() {
			if (this.type == TYPES.INT)
				return Integer.parseInt(value.toString());
			return 0;
		}

		public Double getValueAsDecimal() {
			if (this.type == TYPES.DECIMAL)
				return Double.parseDouble(value.toString());
			return 0.0;
		}

	}

	public static void close() {
		scanner.close();

	}
	
}
