
package proj2;

import proj2.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Proj2 {

	public static void main(String[] args) throws IOException {
		
		/*
		 // Importing of the ant Dargs value
		String filepath = args[0];
		int levelPrint = Integer.parseInt(args[1]);
		*/
		
		// For Eclipse Testing ONLY!
		
		String filepath = "a1.cmds";
		int levelPrint = 4;
		
		
		insertToTree (filepath);
		
	}
	
	static void insertToTree (String filename) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader(filename));
		
	}
	
	
	
}

