// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate


package proj2;

import java.util.*;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x)
    {
        root = insert( x, root, null );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        //if( isEmpty( ) )
            //throw new UnderflowException( );
        	
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        //if( isEmpty( ) )
            //throw new UnderflowException( );
        	
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree(int level)
    {
        if( isEmpty( ) ) {
            System.out.println( "Empty tree" );
        }
        else {
            printTree( root , level );
        }
    }
    
    public void findMedian ( ) {
    	
    	BinaryNode<AnyType> MedianElement = null;
    	
    	if( isEmpty( ) ) {
            System.out.println( "Empty tree" );
        }
       // else if (!isBalanced(root)) {
        //	MedianElement = findMedian(root);
       // }
    }
    
    private BinaryNode<AnyType> findMedian ( BinaryNode<AnyType> r) {
    	
    	int m = (r.weight + 1)/2;
    	int current_pos = 0;
    	
    	if (r.left != null) {
    		current_pos = r.left.weight + 1;
    	}	
    	
    	BinaryNode<AnyType> current_root = r;
    	
    	while (current_pos != m) {
    		if (current_pos > m) {
    			if (current_root.left != null) {
    				current_root = current_root.left;
    				if (current_root.right != null) {
    					current_pos = current_pos - current_root.right.weight - 1;
    				}
    				else {
    					current_pos = current_pos - 1;
    				}
    				
    			}
    		}
    		else {
    			if (current_root.right != null) {
    				current_root = current_root.right;
    				if (current_root.left != null)	{
    					current_pos = current_pos + current_root.left.weight + 1;
    				}
    				else {
    					current_pos = current_pos + 1;
    				}
    			}
    		}
    	}
    	
    	return current_root;
    }
    
    public void balance () {
    	root = balance(findMedian(root), root);
    }
    
    private BinaryNode<AnyType> balance(BinaryNode<AnyType> medianNode, BinaryNode<AnyType> r) {
    	BinaryNode<AnyType> temp;
    	
    	if (r.right == medianNode) {
    		temp = r;
    		r = medianNode;
    		r.parent = temp.parent;
    		r.weight = temp.weight;
    		if (medianNode.left != null) {
    			temp.right = medianNode.left;
    		}
    		else {
    			temp.right = null;
    		}
    		
    		temp.parent = medianNode.element;
    		
    		if (temp.left != null && temp.right != null) {
    			temp.weight = temp.left.weight + temp.right.weight + 1;
    		}
    		else {
    			if (temp.left != null) {
    				temp.weight = temp.left.weight + 1;
    			}
    			else if (temp.right != null) {
    				temp.weight = temp.right.weight + 1;
    			}
    			else {
    				temp.weight = 1;
    			}
    		}
    		
    		r.left = temp;
    	}
    	else if (r.left == medianNode) {
    		temp = r;
    		r = medianNode;
    		r.parent = temp.parent;
    		r.weight = temp.weight;
    		if (medianNode.right !=null) {
    			temp.left = medianNode.right;
    		}
    		else {
    			temp.left = null;
    		}
    		
    		temp.parent = medianNode.element;
    		
    		if (temp.left != null && temp.right != null) {
    			temp.weight = temp.left.weight + temp.right.weight + 1;
    		}
    		else {
    			if (temp.left != null) {
    				temp.weight = temp.left.weight + 1;
    			}
    			else if (temp.right != null) {
    				temp.weight = temp.right.weight + 1;
    			}
    			else {
    				temp.weight = 1;
    			}
    		}
    		
    		r.right = temp;
    	}
    	else {
    		
    		int compareResult = medianNode.element.compareTo( r.element );
    		
    		if ( compareResult < 0 ) {
    			temp = r;
    			r = balance(medianNode, r.left);
    			
    			r.parent = temp.parent;
    			if(r.right != null) {
    				temp.left = r.right; 
    				temp.left.parent = temp.element;
    			}
    			else {
    				temp.left = null;
    			}
    			
    			temp.parent = r.element;
    			
    			if (temp.left != null && temp.right != null) {
        			temp.weight = temp.left.weight + temp.right.weight + 1;
        		}
        		else {
        			if (temp.left != null) {
        				temp.weight = temp.left.weight + 1;
        			}
        			else if (temp.right != null) {
        				temp.weight = temp.right.weight + 1;
        			}
        			else {
        				temp.weight = 1;
        			}
        		}
    			
    			r.right = temp;
    		}
    		else if ( compareResult > 0) {
    			temp = r;
    			r = balance(medianNode, r.right);
    			r.parent = temp.parent;
    			if (r.left != null) {
    				temp.right = r.left;
    				temp.right.parent = temp.element;
    			}
    			else {
    				temp.right = null;
    			}
    			
    			temp.parent = r.element;
        		
        		if (temp.left != null && temp.right != null) {
        			temp.weight = temp.left.weight + temp.right.weight + 1;
        		}
        		else {
        			if (temp.left != null) {
        				temp.weight = temp.left.weight + 1;
        			}
        			else if (temp.right != null) {
        				temp.weight = temp.right.weight + 1;
        			}
        			else {
        				temp.weight = 1;
        			}
        		}
        		
        		r.left = temp;
    		}
    		else {
    			/*
    			if (r.right != null && r.left != null && r.weight == 3) {
    				
    			}
    			else {
    				if (r.right != null  && r.weight > 2) {
    					r.right = balance(findMedian(r.right), r.right);
    				}

    				if (r.left != null && r.weight > 2) {
    					r.left = balance(findMedian(r.left), r.left);
    				}
    			}
    			*/
    		}
    	}
    	
    	return r;
    }
    
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t, AnyType p )
    {
        if( t == null )
            return new BinaryNode<AnyType>( x, 1, p, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 ) {
        	t.weight += 1;
            t.left = insert( x, t.left, t.element );
        }
        else if( compareResult > 0 ) {
        	t.weight += 1;
            t.right = insert( x, t.right, t.element );
        }
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }
    
    private Boolean isBalanced ( BinaryNode<AnyType> r ) {
    	boolean balanced = false;
    	
    	if (r.weight ==  1) {
    		balanced = true;
    	}
    	
    	if (r.right != null && r.left != null) {
    		int defWeight = r.right.weight - r.left.weight;
    		
    		if (defWeight == -1 | defWeight == 0 | defWeight == 1) {
    			if (isBalanced(r.right) && isBalanced(r.left)) {
    				balanced = true;
    			}
    		}
    	}
    	else {
    		if (r.right != null && r.weight <= 2) {
    			balanced = true;
    		}
    		else if ( r.left != null && r.weight <= 2) {
    			balanced = true;
    		}
    	}
    	
    	return balanced;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> r, int level)
    {
    	Queue<BinaryNode<AnyType>> q = new LinkedList<BinaryNode<AnyType>>();
    	q.add(r);
    	int printLevel = 0;
    	int maxLeafCount = 1;
    	int currentLeaf = 1;
    	int fourPerLine = 1;
    	int maxNodePrints = r.weight;
    	int NodePrints = 0;
    	
    	System.out.println("   the tree contains " + r.weight + " nodes");
		System.out.println("   the height is " + height(r));
		System.out.println("   the median is " + findMedian(root).element);
		System.out.println("Tree contents up to level " + level);
    	
		
		// printLevel <= height(t)
    	while( NodePrints < maxNodePrints && printLevel <= level ) { 
    		BinaryNode<AnyType> n = q.poll();
    	
    		if (currentLeaf == maxLeafCount ) {
    			System.out.println("");
    			System.out.println("Level " + printLevel + ":");
    			printLevel += 1;
    			maxLeafCount = (int)Math.pow(2, printLevel);
    			fourPerLine = 1;
    		}
    		
    		if (fourPerLine == 5) {
    			System.out.println("");
    		}
    		
    		if (n != null) {
    			linePrint(n.parent, n.element, n.weight);
    			NodePrints += 1;
        		fourPerLine += 1;
        		
        		if (n.left !=null){
        			q.add(n.left);
        		}
        		else {
        			q.add(null);
        		}
        		
        		if (n.right !=null) {
        			q.add(n.right);
        		}
        		else {
        			q.add(null);
        		}
    		}
    		else {
    			q.add(null);
    			q.add(null);
    		}
    		
    		currentLeaf += 1;
    	}
    }
    
    private void linePrint(AnyType rootElement,  AnyType currentElement, int currentWeight) { 	
    	if (rootElement == null) {
    		System.out.print("   ( -1,"+ currentElement + "," + currentWeight + ")" );
    	}
    	else
    	{
    		System.out.print("   ("+ rootElement + ","+ currentElement + "," + currentWeight + ")" );
    	}
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement, int theWeight)
        {
            this( theElement, theWeight, null, null, null );
        }

        BinaryNode( AnyType theElement, int theWeight, AnyType p, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            weight 	 = theWeight;
            parent	 = p;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        int weight;					// The weight of the tree
        AnyType parent; 			// parent element
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<Integer>( );
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i);

        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( i );

        if( NUMS < 40 )
            t.printTree( 4 );
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }
    }
}
