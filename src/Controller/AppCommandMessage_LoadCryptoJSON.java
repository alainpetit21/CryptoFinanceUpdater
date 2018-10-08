package Controller;


//All Java import
import Model.CryptoQuoteRecord;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


//All internal import
import Model.IModelCountainer;
import View.JFrame_Main;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class AppCommandMessage_LoadCryptoJSON extends AppCommandMessage {

    public AppCommandMessage_LoadCryptoJSON(Object p_objSender, Object p_objReceiver){
		super(p_objSender, p_objReceiver);
	}
	
    
	@Override
	protected void execute(IControllerApp p_objApp, IModelCountainer p_objModelCoutainer, JFrame_Main p_objView){
        for(int i= 0; i < p_objModelCoutainer.getCryptoRecordsSize(); ++i){
            CryptoQuoteRecord objRecord= executeInternal(p_objApp, p_objModelCoutainer, p_objView, p_objModelCoutainer.getCryptoIDX(i));
            
            p_objView.setCryptoIdxValue(i, Double.parseDouble(objRecord.price_usd) * p_objModelCoutainer.getUSDCAD());
        }
    }
    
	protected CryptoQuoteRecord executeInternal(IControllerApp p_objApp, IModelCountainer p_objModelCoutainer, JFrame_Main p_objView, String p_stID){
		URL website;
		ReadableByteChannel rbc;
		FileOutputStream fos;
		String filenameToSave= System.getProperty("user.dir") + "/fileOnLoad.json";
        String stWebsite= "https://api.coinmarketcap.com/v1/ticker/" + p_stID + "/";

		try {
			website = new URL(stWebsite);
			rbc = Channels.newChannel(website.openStream());
			fos = new FileOutputStream(filenameToSave);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            
            Reader reader = new InputStreamReader(new FileInputStream(filenameToSave));
            Gson gson = new GsonBuilder().create();
            CryptoQuoteRecord[] objRecord = gson.fromJson(reader, CryptoQuoteRecord[].class);
            
            p_objModelCoutainer.setCryptoRecord(p_stID, objRecord[0]);
            return objRecord[0];

		} catch (MalformedURLException ex) {
			System.err.print("Failed to connect to " + stWebsite + "....");

		} catch (IOException ex) {
			System.err.print("Failed to read from " + stWebsite + "....");
         
        } catch (JsonIOException | JsonSyntaxException ex) {
			System.err.print("Failed to reading or parsing " + stWebsite + "System will abort the operation");
		}
        
        return null;
    }
}



