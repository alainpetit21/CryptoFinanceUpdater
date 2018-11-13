package Model;


public interface IModelCountainer {
	public void setEURUSD(double  p_fValue);
	public void setEURUSD(String  p_stValue);
	public void setEURCAD(double  p_fValue);
	public void setEURCAD(String  p_stValue);
	public void computeUSDCAD();
	public double getUSDCAD();

    public int getCryptoRecordsSize();
    public String getCryptoIDX(int p_nIdx);
    public void setCryptoRecord(String p_stKey, CryptoQuoteRecord p_objValue);
    public CryptoQuoteRecord getCryptoRecord(String p_stKey);
    public CryptoQuoteRecord getCryptoRecordIdx(int p_nIdx);
}
