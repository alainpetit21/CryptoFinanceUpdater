package Model;

/* This "Factory" is used to implement the Singleton design pattern for 
 * ModelCountainer not particularly taught in this class, but I found the 
 * pattern useful in many situation. 
 */
public class ModelFactory {
	private static ModelCountainer mSingletonInstance= null;

	public static IModelCountainer getModelCountainer(){
		if(mSingletonInstance != null)
			return mSingletonInstance;

		return mSingletonInstance= new ModelCountainer();
	}
}
