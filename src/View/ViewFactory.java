package View;

/* This "Factory" is used to implement the Singleton design pattern for 
 * ModelCountainer not particularly taught in this class, but I found the 
 * pattern useful in many situation. 
 */
public class ViewFactory {
	private static IView mSingletonInstance= null;

	public static IView getGUIView(){
		if(mSingletonInstance != null)
			return mSingletonInstance;

		return mSingletonInstance= new JFrame_Main();
	}
}
