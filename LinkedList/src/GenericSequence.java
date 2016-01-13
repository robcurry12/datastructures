import java.util.*;

class GenericSequenceNode<E>
{
	E data;
	GenericSequenceNode<E> next; 
	GenericSequenceNode<E> prev;
	public GenericSequenceNode(E data)
	{
		this.data = data;
	}
	public E getData() 
	{
		return data;
	}
	public void setData(E data) 
	{
		this.data = data;
	}
	public GenericSequenceNode<E> getNext() 
	{
		return next;
	}
	public void setNext(GenericSequenceNode<E> next) 
	{
		this.next = next;
	}
	public GenericSequenceNode<E> getPrev() 
	{
		return prev;
	}
	public void setPrev(GenericSequenceNode<E> prev) 
	{
		this.prev = prev;
	}
}
public class GenericSequence<E> implements Iterator<E> 
{
	GenericSequenceNode<E> head;
	GenericSequenceNode<E> current;
	int numElements;
	
	/**
	 * Empty constructor
	 */
	public GenericSequence()
	{
		head = current = null;
	}
	
	/**
	 * Sets the current element to the head so it will be the first removed
	 */
	public void start()
	{
		current = head;
	}
	
	/**
	 * Adds the given element before the current element
	 * @param element The element to be added to the sequence
	 */
	public void addBefore(E element)
	{
		if((current == head) && (current == null))	//Empty list
		{
			GenericSequenceNode<E> newNode = new GenericSequenceNode<E>(element);
			head = newNode;
			newNode.next = null;
			current = head;
			numElements++;
		}
		else if (current != null && current != head)		//current points to an item in the middle of the list
		{
			GenericSequenceNode<E> newNode = new GenericSequenceNode<E>(element);
			newNode.next = current;
			newNode.prev = current.prev;
			current.prev.next = newNode;
			current.prev = newNode;
			numElements++;
		} 
		else if (current == head && current != null)	//current points to the non-null head
		{
			GenericSequenceNode<E> newNode = new GenericSequenceNode<E>(element);
			newNode.next = current;
			current.prev = newNode;
			head = newNode;
			numElements++;	
		}
		//current points to nothing but the list has elements
		//i.e., head is non-null
		else
		{
			System.out.println("ERROR! Cannot add anything before nothing!");	//ERROR! Can't add item  before nothing
		}
	}
	
	/**
	 * Adds the given element after the current element
	 * @param element The element to be added to the sequence
	 */
	public void addAfter(E element)
	{
		if (current == head && head == null) // empty list
		{
			GenericSequenceNode<E> newNode = new GenericSequenceNode<E>(element);
			newNode.next = null;
			current = newNode;
			head = newNode;
			numElements++;
		}
		else if (current == head && current != null) //current points to the non-null head
		{
			GenericSequenceNode<E> newNode = new GenericSequenceNode<E>(element);
			if (current.next == null)
			{
				newNode.next = null;
				current.next = newNode;
				newNode.prev = current;
				current = newNode;
				numElements++;
			}
			else
			{
				newNode.next = head.next;
				head.next.prev = newNode;
				newNode.prev = head;
				head.next = newNode;
				current = newNode;
				numElements++;
			}
			
		}
		else if (current != null && current != head) //current points to an item in the middle of a list
		{
			GenericSequenceNode<E> newNode = new GenericSequenceNode<E>(element);
			if (current.next == null)
			{
				newNode.next = null;
				newNode.prev = current;
				current.next = newNode;
				current = newNode;
				numElements++;
			}
			else
			{
				newNode.next = current.next;
				newNode.prev = current;
				current.next.prev = newNode;
				current = newNode;
				numElements++;
			}
		}
		else
		{
			System.out.println("ERROR! Cannot add anything after nothing!");	//ERROR! Can't add item  after nothing
		}
		
	}
	
	/**
	 * Checks if there is 
	 */
	public boolean hasNext()
	{
		return current != null;
	}
	
	/**
	 * Changes the current to the next value in the sequence
	 */
	public E next() 
	{
		E answer;
		if (!hasNext())
		{
			throw new NoSuchElementException("You're at the end of this list!!");
		}
		answer = current.getData();
		current = current.next;
		
		return answer;
	}
	
	/**
	 * Removes the current element in the sequence
	 */
	public void remove() 
	{
		if((current == head) && (current == null))			//Empty List
		{
			System.out.println("ERROR: Empty list!");
		}
		else if ((current != null) && (current != head))	//Current pointing to middle of list
		{
			if (current.next == null)
			{
				current.prev.next = null;
				current= null;
				numElements--;
			}
			else
			{
				current.prev.next = current.next;
				current.next.prev = current.prev;
				current = current.next;
				numElements--;
			}
		}
		else if ((current == head) && (current != null))	//Current  points to non-null head
		{
			head = current.next;
			current = head;
			numElements--;
		}
		else		
		{
			current.next.prev = current.prev;
			current.prev.next = current.next;
			current = current.next;
			numElements--;
		}
	}
	
	/**
	 * Utilizing a linear search, this method searches for the given element
	 * @param element the element that is being searched for in the sequence
	 * @return If the element that is being searched for exists in the sequence, true is returned. If not, false
	 */
	public boolean contains (E element)
	{
		boolean contains = false;
		GenericSequenceNode<E> curr = head;
		
		while(curr != null)
		{
			if (element == curr.getData())
			{
				contains = true;
				return contains;
			}
			else
				curr = curr.next;
		}
		return contains;
	}

	/**
	 * Adds the entire contents of one sequence to the end of the another
	 * @param other the sequence to be added
	 */
	public void addAll (GenericSequence<E> other)											//TESTED
	{
		GenericSequenceNode<E> node1 = head;
		GenericSequenceNode<E> node2 = other.head;
		GenericSequenceNode<E> curr = current;
		GenericSequenceNode<E> tail = head;
		boolean list1empty = false;
		boolean list2empty = false;
		
		while(node1 != null)
		{
			node1 = node1.next;
		}
		if ((node1 == null) && (node1 == head))
		{
			list1empty = true;
		}
		else if ((node1 == null) && (node1 != head))
		{
			tail = node1;
		}
		if ((node2 == null) && (node2 == head))
		{
			list2empty = true;
		}
		if (list1empty == true)			//Works properly
		{
			if (list2empty == true)
			{
				System.out.println("Adding two empty sequences does nothing. Nothing. NOTHING I TELL YOU!");
			}
			else
			{
				while(node2 != null)
				{
					addAfter(node2.getData());
					node2 = node2.next;
				}
			}
		}
		else
		{
			if (list2empty == true)
			{
				System.out.println("Adding an empty sequences does nothing. Nothing. NOTHING I TELL YOU!");
				System.out.println();
			}
			else
			{
				while(node2 != null)
				{
					addAfter(node2.getData());
					node2 = node2.next;
				}
			}
		}
		current = curr;
	}
	
	/**
	 * Displays the number of elements in the sequence
	 * @return the numElements of the sequence
	 */
	public int size()																			//TESTED
	{
		return numElements;
	}
	
	/**
	 * Prints the contents of the sequence
	 */
	public String toString()
	{
		String list = "";
		
		for(GenericSequenceNode<E> node = head; node != null; node = node.next)
		{
			list += (node.getData()).toString() + " ";
		}
		if (list == "")
		{
			System.out.print("Empty List");
		}
		return list;
	}
}
