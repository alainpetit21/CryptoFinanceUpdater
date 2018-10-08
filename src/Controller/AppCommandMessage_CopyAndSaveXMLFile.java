package Controller;


//All Java import

//All internal import
import Model.CryptoQuoteRecord;
import Model.IModelCountainer;
import View.JFrame_Main;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class AppCommandMessage_CopyAndSaveXMLFile extends AppCommandMessage {

    public AppCommandMessage_CopyAndSaveXMLFile(Object p_objSender, Object p_objReceiver){
		super(p_objSender, p_objReceiver);
	}
	
	@Override
	protected void execute(IControllerApp p_objApp, IModelCountainer p_objModelCoutainer, JFrame_Main p_objView){
        for(int i= 0; i < p_objModelCoutainer.getCryptoRecordsSize(); ++i){
            double fLastValue= executeInternal(p_objApp, p_objModelCoutainer, p_objView, p_objModelCoutainer.getCryptoIDX(i));
            
            p_objView.setXMLIdxValue(i, fLastValue);
        }
    }

	protected double executeInternal(IControllerApp p_objApp, IModelCountainer p_objModelCoutainer, JFrame_Main p_objView, String p_stID){
        try {
            DocumentBuilderFactory  objXMLFactory   = DocumentBuilderFactory.newInstance();
            DocumentBuilder         objXMLParser    = objXMLFactory.newDocumentBuilder();
//            String                  filename        = "/home/apetit/Documents/Alain Petit/Others/finances.xml";
            String                  filename        = "/home/apetit/Documents/Alain Petit/Others/financesTest.xml";
            Document                objXMLDoc       = objXMLParser.parse(filename); 

            CryptoQuoteRecord objRecord= p_objModelCoutainer.getCryptoRecord(p_stID);

            XPath    objXPath    = XPathFactory.newInstance().newXPath();
            NodeList objNodeListPricePair= (NodeList) objXPath.compile("//*[@to='CAD' and @from='"+objRecord.symbol+"']").evaluate(objXMLDoc, XPathConstants.NODESET);
                    
            
            //For Testing
            Element     elNodePricePair  = (Element) objNodeListPricePair.item(0);

            double fLastValue= Double.parseDouble(objRecord.price_usd) * p_objModelCoutainer.getUSDCAD();
            
            LocalDateTime now = LocalDateTime.now();
            int year = now.getYear();
            int month = now.getMonthValue();
            int day = now.getDayOfMonth();
            String test= String.format("%d/1000",  (int) (fLastValue*1000));

        
            Element objNewElement = objXMLDoc.createElement("PRICE");
            objNewElement.setAttribute("source", "CryptoFinanceUpdater");
            objNewElement.setAttribute("price", String.format("%d/1000",  (int) (fLastValue*1000)));
            objNewElement.setAttribute("date", String.format("%4d-%02d-%02d", year, month, day));
            elNodePricePair.insertBefore(objNewElement, null);
            
            TransformerFactory  objTransFactory = TransformerFactory.newInstance();
            Transformer         objTransformer  = objTransFactory.newTransformer();
            DOMSource           objSourceDOM    = new DOMSource(objXMLDoc);
            Writer              objFileWritter  = new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.ISO_8859_1);
            StreamResult        objStreamResult = new StreamResult(objFileWritter);

            objTransformer.setOutputProperty("encoding", "ISO-8859-1");
            objTransformer.transform(objSourceDOM, objStreamResult);        

            return fLastValue;
        
        }catch(ParserConfigurationException ex1){
            System.err.print("Error : Loading Parser Object");
        }catch(IOException ex2){
            System.err.print("Error : Unable to connect to default xml source");
        }catch(SAXException ex3){
            System.err.print("Error : Parsing error in the xml");
        }catch(IllegalArgumentException ex4){
            System.err.print("Error : Wrong Argument input for XML Parser");
        }catch(XPathExpressionException ex5){
            System.err.print("Error : Wrong Argument input for XPath");
        }catch(TransformerException ex6){
            System.err.print("Error : Wrong Argument input for Transformer");
        }
        
        return 0.0;
    }
    
}
