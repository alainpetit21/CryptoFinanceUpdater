package Model;

//All Java imports
import java.util.ArrayList;


/* The ModelCountainer shall countain the bulk of all data manipulation 
 * including disk serialization (import/export). 
 */
public class ModelCountainer implements IModelCountainer{
	ArrayList<IEvent> mArrayEvents= new ArrayList<>();

	
	@SuppressWarnings("OverridableMethodCallInConstructor")
	public ModelCountainer(){
		//buildTestModel();	//For Testing/debuging
	}

	@Override
	public IEvent getEvent(int p_idxEvent){
		if(mArrayEvents.isEmpty())
			return null;

		return mArrayEvents.get(p_idxEvent);
	}

	@Override
	public void addElement(IEvent p_objEvent){
            mArrayEvents.add(p_objEvent);
	}

	@Override
	public void removeElement(int p_idxEvent){
		if(p_idxEvent < size()){
			mArrayEvents.remove(p_idxEvent);
		}
	}

	public void removeAllElement(){
		mArrayEvents.clear();
	}

	@Override
	public int size(){
		return mArrayEvents.size();
	}

	//For Testing/debugging	
	public void buildTestModel(){
	}
}
