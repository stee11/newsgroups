/**Steven Kolln
LinkedSet*/
package javafoundations;
import javafoundations.exceptions.*;
import java.util.NoSuchElementException;

public class LinkedSet<T extends Comparable<T>> implements Set<T>
{
	private int count;
	private LinearNode<T> top;
	public LinkedSet()
	{
		count=0;
		top=null;
	}
	public void add(T element)
	{
		if ((this.contains(element))==false)
		{
			count++;
			LinearNode<T> newNode=new LinearNode<T>(element);
			if (top!=null)
				newNode.setNext(top);
			top=newNode;
		}
	}
	public T remove(T element) throws ElementNotFoundException
	{
		return element;
	}
	
	public boolean contains(T element)
	{
		boolean has=false;
		LinearNode<T> current=top;
		for (int c=0;c<count;c++)
		{
			if (current.getElement().compareTo(element)==0)
			{
				//System.out.println("true");
				has=true;
			}
			else
				current=current.getNext();
		}
		return has;
	}
	public boolean isEmpty()
	{
		if (count==0)
			return true;
		else
			return false;
	}
	public int size()
	{
		return count;
	}
	public T get(int num) throws NoSuchElementException 
	{
		if (num < 1 || num > count)
			throw new NoSuchElementException(num + " is not a valid element number of this set.");
		if (num==1)
			return top.getElement();
		else
		{
			LinearNode<T> item = top;
			for(int a = 1; a<num; a++)
				item = item.getNext();
			return item.getElement();
		}
	}
	public String toString()
	{
		String words = "";
		LinearNode<T> current = top;
		while (current!=null)
		{
			words+=current.getElement().toString() + "\n";
			current=current.getNext();
		}
		return words;
	}
}