package Model;


public class ModelFactory {
	private static ModelCountainer mSingletonInstance= null;

	public static IModelCountainer getModelCountainer(){
		if(mSingletonInstance != null)
			return mSingletonInstance;

		return mSingletonInstance= new ModelCountainer();
	}
}
