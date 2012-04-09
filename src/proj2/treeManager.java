package proj2;

import java.util.Comparator;

import proj2.BinarySearchTree;

public class treeManager {
	
	BinarySearchTree tree = new BinarySearchTree();
	int count = 0;
	
	public treeManager () {
		
	}
	
	public void insertElement(int x){
		tree.insert(x);
	}

	public void treePrint(int level) {
		tree.printTree(level);
	}
	
	public void setCount () {
		count = count + 1;
	}
	
	public int getCount () {
		return count;
	}
	
	public void blanceTree() {
		
		tree.balance();
	}
	
}
