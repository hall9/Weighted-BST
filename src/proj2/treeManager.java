package proj2;

import proj2.BinarySearchTree;

public class treeManager {
	
	BinarySearchTree tree = new BinarySearchTree();
	
	
	public treeManager () {
		
	}
	
	public void insertElement( int x){
		tree.insert(x);
	}
}
