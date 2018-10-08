package Controller;


//All Java import

//All internal import
import Model.CryptoQuoteRecord;
import Model.IModelCountainer;
import View.JFrame_Main;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class AppCommandMessage_LoadKMyMoneyXML extends AppCommandMessage {

    public AppCommandMessage_LoadKMyMoneyXML(Object p_objSender, Object p_objReceiver){
		super(p_objSender, p_objReceiver);
	}
	
	@Override
	protected void execute(IControllerApp p_objApp, IModelCountainer p_objModelCoutainer, JFrame_Main p_objView){
        for(int i= 0; i < p_objModelCoutainer.getCryptoRecordsSize(); ++i){
            double fLastValue= executeInternal(p_objApp, p_objModelCoutainer, p_objView, p_objModelCoutainer.getCryptoRecordIdx(i).symbol);
            
            p_objView.setXMLIdxValue(i, fLastValue);
        }
    }

	protected double executeInternal(IControllerApp p_objApp, IModelCountainer p_objModelCoutainer, JFrame_Main p_objView, String p_strSymbol){
        try {
            DocumentBuilderFactory  objXMLFactory   = DocumentBuilderFactory.newInstance();
            DocumentBuilder         objXMLParser    = objXMLFactory.newDocumentBuilder();
//            Document                objXMLDoc       = objXMLParser.parse("/home/apetit/Documents/Alain Petit/Others/financesTest.xml"); 
            Document                objXMLDoc       = objXMLParser.parse("/home/apetit/Documents/Alain Petit/Others/finances.xml"); 

            XPath    objXPath    = XPathFactory.newInstance().newXPath();
            NodeList objNodeListPricePair= (NodeList) objXPath.compile("//*[@to='CAD' and @from='"+p_strSymbol+"']").evaluate(objXMLDoc, XPathConstants.NODESET);
            
            
            //For Testing
            Element     elNodePricePair  = (Element) objNodeListPricePair.item(0);
            NodeList    objNodeListPrices= elNodePricePair.getChildNodes();
                    
            double fLastValue= 0.0;
            for (int i = 0; i < objNodeListPrices.getLength(); ++i) {
                if(objNodeListPrices.item(i).getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element     elNodePrice   = (Element) objNodeListPrices.item(i);
                
                String stEquation= elNodePrice.getAttribute("price");
                String[] arstOperands= stEquation.split("/");
                double left=  Double.parseDouble(arstOperands[0]);
                double right=  Double.parseDouble(arstOperands[1]);
                
                fLastValue= left/right;
            }
            
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
        }
        
        return 0.0;
    }
    
}
