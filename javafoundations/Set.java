/**Steven Kolln
Project 2
3/30/2012
CSC 230 02
Interface Set*/
package javafoundations;
import javafoundations.exceptions.*;
import java.util.NoSuchElementException;

public interface Set<T> 
{
// Adds the specified element to the set
public void add(T element);
// Removes the specified element from the set
public T remove(T element) throws ElementNotFoundException;
// Returns a true value if the specified element is a member of the set
public boolean contains(T element);
// Returns true if the set contains no elements and false otherwise.
public boolean isEmpty();
// Returns the number of elements in the set.
public int size();
// Returns a string representation of the set.
public String toString();
// Returns the data from
public T get(int elementNum) throws NoSuchElementException;
}