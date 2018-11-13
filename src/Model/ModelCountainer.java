package Model;

//All Java imports
import java.util.HashMap;
import java.util.Map;


/* The ModelCountainer shall countain the bulk of all data manipulation 
 * including disk serialization (import/export). 
 */
public class ModelCountainer implements IModelCountainer{
    public double m_fEURUSD= 0.0;
    public double m_fEURCAD= 0.0;
    public double m_fUSDCAD= 0.0;
    
    public Map<String, CryptoQuoteRecord>   m_mapRecordCrypto;
    public String[] m_arID= {"bitcoin", "bitcoin-cash", "bitcoin-gold", "ethereum", "litecoin", "iota"};


	public ModelCountainer(){
        m_mapRecordCrypto= new HashMap<>(10);
	}

    @Override
	public void setEURUSD(double  p_fValue){
        m_fEURUSD= p_fValue;
    }

    @Override
	public void setEURUSD(String  p_stValue){
        setEURUSD(Double.parseDouble(p_stValue));
    }

    @Override
	public void setEURCAD(double  p_fValue){
        m_fEURCAD= p_fValue;
    }

    @Override
	public void setEURCAD(String  p_stValue){
        setEURCAD(Double.parseDouble(p_stValue));
    }

    @Override
	public void computeUSDCAD(){
        m_fUSDCAD= m_fEURCAD/m_fEURUSD;
    }

    @Override
	public double getUSDCAD(){
        return m_fUSDCAD;
    }

    
    @Override
    public int getCryptoRecordsSize(){
        return m_arID.length;
    }

    @Override
    public String getCryptoIDX(int p_nIdx) throws IndexOutOfBoundsException{
        if(p_nIdx >= m_arID.length)
            throw new IndexOutOfBoundsException();
        
        return m_arID[p_nIdx];
    }

    @Override
    public void setCryptoRecord(String p_stKey, CryptoQuoteRecord p_objValue){
        m_mapRecordCrypto.put(p_stKey, p_objValue);        
    }

    @Override
    public CryptoQuoteRecord getCryptoRecord(String p_stKey){
        return m_mapRecordCrypto.get(p_stKey);
    }

    @Override
    public CryptoQuoteRecord getCryptoRecordIdx(int p_nIdx){
        return getCryptoRecord(m_arID[p_nIdx]);
    }
}

