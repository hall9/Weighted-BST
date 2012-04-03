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
        root = insert( x, root );
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
            printTree( root, null, level );
        }
    }
    
    public void findMedian ( ) {
    	BinaryNode<AnyType> newNode = findMedian(root);
    }
    
    private BinaryNode<AnyType> findMedian ( BinaryNode<AnyType> t) {
    	
    	int m = (t.weight +1)/2;
    	int current_pos = 0;
    	
    	if (t.left != null) {
    		current_pos = t.left.weight + 1;
    	}	
    	
    	BinaryNode<AnyType> current_root = t;
    	
    	while (current_pos != m && t.left != null && t.right != null) {
    		
    		if (current_pos > m && current_root.left != null ) {
    			current_root = current_root.left;
    			current_pos = current_pos - current_root.right.weight - 1;
    		}
    		else {
    			current_root = current_root.right;
    			current_pos = current_pos + current_root.left.weight + 1 ;
    		}
    	}
    	
    	System.out.println("M = " + current_root.element);
    	return current_root;
    }
    
    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<AnyType>( x, 1, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 ) {
        	t.weight += 1;
            t.left = insert( x, t.left );
        }
        else if( compareResult > 0 ) {
        	t.weight += 1;
            t.right = insert( x, t.right );
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
    private void printTree( BinaryNode<AnyType> t, AnyType rootElement, int level)
    {
    	int levelPrint = 0;
    	if (t != null)
    	{
    		if (rootElement == null) {
				System.out.println("   the tree contains " + t.weight + " nodes");
				System.out.println("   the height is " + height(t));
				System.out.println("   the median is " + 999);
				System.out.println("Tree contents up to level " + level);
				System.out.println("Level " + 0 + ":");

				System.out.println("   ("+ -1 + ","+ t.element + "," + t.weight + ")" );
			}
    		
    	
    		BinaryNode<AnyType> rootNode = t;
    		System.out.println("Level " + 1 + ":");
    		eachLevelPrint(rootNode.element, rootNode);
    		
    		System.out.println("");
    		System.out.println("Level " + 2 + ":");
    		eachLevelPrint(rootNode.left.element, rootNode.left);
    		eachLevelPrint(rootNode.right.element, rootNode.right);
    		
    		System.out.println("");
    		System.out.println("Level " + 3 + ":");
    		eachLevelPrint(rootNode.left.left.element, rootNode.left.left);
    		eachLevelPrint(rootNode.left.left.element, rootNode.left.right);
    		
    		System.out.println("");
    		eachLevelPrint(rootNode.right.left.element, rootNode.right.left);
    		eachLevelPrint(rootNode.right.right.element, rootNode.right.right);
   
    	
    		/*
    		for (int i = 1; i < level+1; i++) {
    			
    			System.out.println("Level " + i + ":");
    			if (leftNode != null)
    			linePrint(rootNode.element, leftNode.element, leftNode.weight);
    			
    			if (rightNode != null)
    			linePrint(rootNode.element, rightNode.element, rightNode.weight);
    			
    			leftNode = leftNode.left;
    			rightNode = leftNode.right;
    			System.out.println("");
    		}
    		
  
    		
    		if (height(t) < level+1) {
    			levelPrint = height(t);
    		}
    		else {
    			levelPrint = level;
    		}
    		
    		BinaryNode<AnyType> leftNode = t.left;
    		BinaryNode<AnyType> rightNode = t.right;
    		
    		for (int i=1; i < levelPrint; i++) {   
    				System.out.println("Level " + i + ":");
    				
    				for (int k=0; k < Math.pow(i,2) ; k++) {
    					eachLevelPrint(t.element, leftNode);
    					eachLevelPrint(t.element, rightNode);
    					leftNode = leftNode.left;
    					rightNode = rightNode.right;
    				}
    		}
    		
    		
    		*/
    		
    	}
    }
    
    
    
    private void eachLevelPrint (AnyType rootElement, BinaryNode<AnyType> node) {
    	if (node.left != null) {
    		linePrint(rootElement, node.left.element, node.left.weight);
    	}
    	
    	if (node.right != null) {
    	linePrint(rootElement, node.right.element,node.right.weight);
    	}
    }
    
    private void linePrint(AnyType rootElement,  AnyType currentElement, int currentWeight) {
   
    	System.out.print("   ("+ rootElement + ","+ currentElement + "," + currentWeight + ")" );
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
        BinaryNode( AnyType theElement, int theWeight )
        {
            this( theElement, theWeight, null, null );
        }

        BinaryNode( AnyType theElement, int theWeight, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            weight = theWeight;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        int weight;					// The weight of the tree
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
