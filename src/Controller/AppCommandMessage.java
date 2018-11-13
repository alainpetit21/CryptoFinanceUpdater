package Controller;


//All internal import
import Model.IModelCountainer;
import View.IView;


/*
 * AppCommandMessage abstract class implement the Command Design Pattern, along
 * with the Controller App. However, in its standalone design, it demonstrate
 * the template design pattern of have an abstract class that does *some* 
 * generic operation, but the real bulk of the action (execute method) is done
 * at the overloaded level in the polymorphic derived version of the the Object.
 *
 * AppCommandMessage serve 2 purpose, it enable the View package to "notify" the
 * Controller package of a function (e.g. add, delete, etc.) And it also 
 * encapsulate all the proper execution for each command. in a OO polymorphic 
 * way. 
 */
public abstract class AppCommandMessage {
	Object mObjSender;
	Object mObjReceiver;


	public AppCommandMessage(Object p_objSender, Object p_objReceiver){
		mObjSender= p_objSender;
		mObjReceiver= p_objReceiver;
	}

	//Protected because it shall only be called withing the Controller package
	abstract protected void execute(IControllerApp p_objApp, IModelCountainer p_objModelCoutainer, IView p_objView);
}
