
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
		int levelPrint = 10;
		
		
		treeManager tree = insertToTree (filepath);
		System.out.println(tree.getCount() + " integers were read from " + filepath);
		System.out.println("");
		System.out.println("Before balancing");
		tree.treePrint(levelPrint);
		
		System.out.println("");
		tree.blanceTree();
		System.out.println("");
		System.out.println("After balancing");
		tree.treePrint(levelPrint);
		
	}
	
	static treeManager insertToTree (String filename) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader(filename));
		
		treeManager tree = new treeManager();
		
		String line;
		String element;
		
		while((line = r.readLine()) != null) {
			StringTokenizer token;
			token = new StringTokenizer (line);

			if (token.hasMoreTokens()) {
				element = token.nextToken();
		
				if ( element == "#") {
					// do nothing
				}
				
				if ( element.equals("#")) {
					// Do nothing skip line
				}
				else {
					tree.insertElement(Integer.parseInt(element));
					tree.setCount();
				}
			}
		}
		
		return tree;
	}
}

