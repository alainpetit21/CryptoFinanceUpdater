package View;

//All Java imports

import Controller.IControllerApp;



public interface IView {
    public void setController(IControllerApp p_objController);
    public void setUSDValue(double p_fValue);
    public void setCryptoIdxValue(int p_nIdx, double p_fValue);
    public void setXMLIdxValue(int p_nIdx, double p_fValue);
}
