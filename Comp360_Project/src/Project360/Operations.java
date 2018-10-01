package Project360;

import java.util.ArrayList;

public class Operations {

	ArrayList<String> lexemes = new ArrayList<String>();
	ArrayList<String> tokens = new ArrayList<String>();
	private String errorMsg = "";

	// checks if statement opens correctly
	public int openStatement(String x) {

		if (x.equals("") || x.equals(" ")) {
			return 1;
		}
		if (x.equals("}")) {
			isToken(x);
			return 1;
		}
		if (x.equals("double")) {
			isToken(x);
			return 1;
		}

		if (x.equals("int")) {
			isToken(x);
			return 1;
		}

		if (x.equals("char")) {
			isToken(x);
			return 1;
		}

		if (x.equals("String")) {
			isToken(x);
			return 1;
		}

		if (x.equals("float")) {
			isToken(x);

			return 1;
		}

		if (x.equals("long")) {
			isToken(x);
			return 1;
		}
		if (x.contains("(") || x.contains(")") || x.contains("{") || x.contains("}")) {
			return 2;
		}

		isToken(x);
		return 0;

	}

	/*
	 * Examines to see if the program is properly closed
	 */
	int endStatement(String theStr) {
		if (theStr.equals("") || theStr.equals(" ")) {
			return 1;
		} else {
			char last = theStr.charAt(theStr.length() - 1);

			// if statement that determines if statement has ended properly
			if (last == ';' || last == '{' || last == '}') {
				String d = Character.toString(last);
				isToken(d);
				return 1;
			} else {
				return 0;
			}
		}

	}

	//Looks to see if the symbol is a special character
	int isSymbol(String h) {

		try {

			if (h.equals("+") || h.equals("-") || h.equals("*") || h.equals("/") || h.equals("=")) {
				isToken(h);
				return 1;
			}
			if (h.equals(";")) {
				isToken(h);
				return 1;
			}

			if (h.contains("(){")) {
				isToken("(");
				isToken(")");
				isToken("{");
				return 1;
			}

			if (h.contains("*")) {
				isToken("*");
				return 1;
			}
			if (h.contains("/")) {
				isToken("/");
				return 1;
			}
			if (h.contains("-")) {
				isToken("-");
				return 1;
			}
			if (h.contains("+")) {
				isToken("+");
				return 1;
			}

			if (h.contains("(") || h.equals("(") || h.contains(")") || h.equals(")")) {
				return 0;
			}

			isToken(h);
			return 1;

		} catch (Exception e) {

			System.out.println("Program will not run because of this symbol = " + h);
		}
		return 0;
	}

	// Examines the structure of the code
	int isStructure(String[] h, int start) {
		for (int i = start; i < h.length; i++) {
			if (openStatement(h[i]) == 0) {// if variable
				i++;
				if (i < (h.length - 1)) { // if not last input
					if (isSymbol(h[i]) == 0) { //
						System.out.println(h[i]);
						errorMsg = "inncorrect structure";
						return 0; // will not run
					}
				} else if (i == (h.length - 1)) {// if last input
					if (isSymbol(h[i]) == 0) { // if not operand or ends on variable
						System.out.println(h[i]);
						errorMsg = "unknown operand or ends on variable";
						return 0; // will not run
					}
				}
			} else if (openStatement(h[i]) == 2) {
				System.out.println(h[i]);
				errorMsg = "inncorrect structure";
				return 0; // will not run
			} else {
				System.out.println(h[i] + " " + h[i + 1]);
				errorMsg = "cant have keywords side by side";
				return 0; // if not a variable will not run
			}
		}
		return 1;
	}

	// add token and lexeme to array list
	void isToken(String p) {
		int n = 0;
		// switch statement that finds lexemes and tokens and add them to an arrayList
		String x = p;
		switch (x) {

		case "int":
			lexemes.add("int");
			tokens.add("DATA_TYPE");
			break;

		case "double":
			lexemes.add("double");
			tokens.add("DATA_TYPE");
			break;

		case "<":
			lexemes.add("<");
			tokens.add("LESS_OP");
			break;

		case "{":
			lexemes.add("{");
			tokens.add("OPEN_CURLB");
			break;

		case "String":
			lexemes.add("String");
			tokens.add("DATA_TYPE");
			break;

		case "char":
			lexemes.add("char");
			tokens.add("DATA_TYPE");
			break;

		case "=":
			lexemes.add("=");
			tokens.add("ASSIGN_OP");
			break;

		case "float":
			lexemes.add("float");
			tokens.add("DATA_TYPE");
			break;

		case "-":
			lexemes.add("-");
			tokens.add("SUB_OP");
			break;

		case "+":
			lexemes.add("+");
			tokens.add("ADD_OPP");
			break;

		case "*":
			lexemes.add("*");
			tokens.add("MUL_OP");
			break;

		case "/":
			lexemes.add("/");
			tokens.add("DIV_OP");
			break;

		case "%":
			lexemes.add("%");
			tokens.add("MOD_OP");
			break;

		case ">":
			lexemes.add(">");
			tokens.add("GREAT_OP");
			break;

		case "}":
			lexemes.add("}");
			tokens.add("CLOSE_CULRB");
			break;

		case "[":
			lexemes.add("[");
			tokens.add("OPEN_BRACK");
			break;

		case ":":
			lexemes.add(":");
			tokens.add("COLON");
			break;

		case "]":
			lexemes.add("]");
			tokens.add("CLOSED_BRACK");
			break;

		case "(":
			lexemes.add("(");
			tokens.add("OPEN_PAR");
			break;

		case ",":
			lexemes.add(",");
			tokens.add("COMMA");
			break;

		case ")":
			lexemes.add(")");
			tokens.add("CLOSED_PAR");
			break;

		case ";":
			lexemes.add(";");
			tokens.add("SEMICOLON");
			break;

		default:
			lexemes.add(x);
			tokens.add("IDENT");
			break;
		}
	}

	// Calls the print function
	void print() {
		Operations.print(lexemes, tokens);
	}

	// Prints the tokens and lexemes
	public static void print(ArrayList<String> lexeme, ArrayList<String> token) {
		String Lexes = "Lexemes";
		String Tokens = "Tokens";
		String divider = "**************************************";
		System.out.printf("%-15s %-15s %n", Lexes, Tokens);
		System.out.println(divider);
		for (int i = 0; i < lexeme.size(); i++) {
			System.out.printf("%-15s %-15s %n", lexeme.get(i), token.get(i));
		}
		System.out.println(divider);
	}

	// Gets the error message
	public String getErrorMsg() {
		return errorMsg;
	}

	// Creates the error msg
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
