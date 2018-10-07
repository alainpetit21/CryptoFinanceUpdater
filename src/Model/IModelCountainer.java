package Model;

//All Java imports


/*
 * Using the interface IModelCountainer it is my implementation of the 
 * requirement to have a Observer design pattern. 
 */
public interface IModelCountainer {
	IEvent getEvent(int p_idxEvent);
	void addElement(IEvent p_objEvent);
	void removeElement(int p_idxEvent);
	public int size();

}
