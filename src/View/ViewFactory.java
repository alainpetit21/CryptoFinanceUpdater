package View;


public class ViewFactory {
	private static IView mSingletonInstance= null;

	public static IView getGUIView(){
		if(mSingletonInstance != null)
			return mSingletonInstance;

		return mSingletonInstance= new JFrame_Main();
	}
}
