
public class GenericSequenceTester {

	public static void main(String[] args) 
	{
		GenericSequence g1 = new GenericSequence();
		GenericSequence g2 = new GenericSequence();
		GenericSequence g3 = new GenericSequence();
		GenericSequence g4 = new GenericSequence();
		GenericSequence g5 = new GenericSequence();
		GenericSequence g6 = new GenericSequence();
		
		g1.addBefore(34);
		g1.addBefore(45);
		g1.addBefore(58);
		g1.addBefore(67);
		System.out.println(g1.toString() + ": g1");		//CURRENT = 34
		
		g2.addAfter(43);
		g2.addAfter(56);
		g2.addAfter(87);
		g2.addBefore(23);
		g2.addAfter(12);
		System.out.println(g2.toString() + ": g2");		//CURRENT = 12
		
		g6.addBefore(100);
		g6.addBefore(103);
		g6.addBefore(45);
		g6.addBefore(0);
		System.out.println(g6.toString() + ": g6");		//CURRENT = 100
		
		System.out.println(g1.size() + " :size of g1");						//Testing
		System.out.println(g2.size() + " :size of g2");						//size
		System.out.println(g3.size() + " :size of g3");						//method
		
		g1.addAll(g2);														//Adding two filled sequences
		g3.addAll(g2);														//Adding one sequence to an empty one
		g4.addAll(g5);														//Adding two empty sequences
		
		System.out.println(g1.toString() + ": combining g1 and g2");		//Adding two filled sequences
		System.out.println(g3.toString() + ": combining g2 and g3");		//Adding one sequence to an empty one
		System.out.println(g4.toString() + ": combining g4 and g5");		//Adding two empty sequences
		System.out.println();
		
		//Testing contains method
		System.out.println("g3 contains the number 99: " + g3.contains(99));	//FALSE
		System.out.println("g3 contains the number 56: " + g3.contains(56));	//TRUE
		System.out.println("g5 contains the number 96: " + g5.contains(96));	//FALSE		//Sequence is empty
		System.out.println();
		
		//Testing hasNext method
		System.out.println("g1's current is followed by another element: " + g1.hasNext());		//TRUE		//adding two sequences did not change the current
		System.out.println("g4's current is followed by another element: " + g4.hasNext());		//FALSE		//two empty sequences added together
		System.out.println("g6's current is followed by another element: " + g6.hasNext());		//TRUE
		System.out.println();
		
		//Testing next method
		//System.out.println("g4's current should throw an exception: " + g4.next());			//Uncomment to see thrown exception
		System.out.println("g1's current should be 34: " + g1.next());
		System.out.println("g1's new current should be 43: " + g1.next());
		System.out.println("g6's current should be 100: " + g6.next());
		System.out.println();
		
		//Testing start and remove methods
		g2.remove();
		g4.remove();
		
		System.out.println("g2 having removed the last element: " + g2.toString());		//Should remove the 12		//End of list
		g2.start();
		g2.remove();
		System.out.println("g2 having removed the first element: " + g2.toString());	//Should remove the 43		//Beginning of list
		System.out.println(": g4 having removed a non-existent element: " + g4.toString());
		g2.next();
		g2.remove();
		System.out.println("g2 having remove a middle element: " + g2.toString());		//Should remove 23			//Middle of list
	}

}
