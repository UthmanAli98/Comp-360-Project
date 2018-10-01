package Project360;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Operations op = new Operations();
		System.out.println("What is the File path of the txt file?");

		try {
			
			Scanner name = new Scanner(System.in);
			String g = name.next();
			System.out.println();
			File file = new File(g);
			Scanner sc = new Scanner(file);
			int nError = 1;

			String datatype = sc.nextLine();
			while (sc.hasNext() && nError == 1) {
				datatype = sc.nextLine();

				String[] u = datatype.split(" ");

				if (op.openStatement(u[0]) == 1) {
					nError = op.isStructure(u, 1);
				} else if (u[1].equals("=")) {
					nError = op.isStructure(u, 0);
				} else {
					op.setErrorMsg("The beginning notation is wrong!");
					nError = 0;
				}

				if (op.endStatement(datatype) == 0) {
					op.setErrorMsg("The end notation is incorrect!");
					nError = 0;
				}
				// System.out.println();
			}

			System.out.println();

			if (nError == 1) {
				System.out.println("The Program Runs!");
				System.out.println();
				op.print();
			} else if (nError == 0) {
				System.out.println("This program will not run!!");
				System.out.println("error: " + op.getErrorMsg());
			}

		} catch (IOException e) {
			System.out.println("File not found!");
		}
	}
}
