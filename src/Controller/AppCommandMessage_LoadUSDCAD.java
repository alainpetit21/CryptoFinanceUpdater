package Controller;


//All Java import
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

//All external import
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//All internal import
import Model.IModelCountainer;
import View.IView;


public class AppCommandMessage_LoadUSDCAD extends AppCommandMessage {

    public AppCommandMessage_LoadUSDCAD(Object p_objSender, Object p_objReceiver){
		super(p_objSender, p_objReceiver);
	}
	

	@Override
	protected void execute(IControllerApp p_objApp, IModelCountainer p_objModelCoutainer, IView p_objView){
        try {
            DocumentBuilderFactory  objXMLFactory   = DocumentBuilderFactory.newInstance();
            DocumentBuilder         objXMLParser    = objXMLFactory.newDocumentBuilder();
            Document                objXMLDoc       = objXMLParser.parse("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml"); 

            XPath    objXPath    = XPathFactory.newInstance().newXPath();
            NodeList objNodeListUSD= (NodeList) objXPath.compile("//*[@currency='USD']/@rate").evaluate(objXMLDoc, XPathConstants.NODESET);
            NodeList objNodeListCAD= (NodeList) objXPath.compile("//*[@currency='CAD']/@rate").evaluate(objXMLDoc, XPathConstants.NODESET);
            
            p_objModelCoutainer.setEURUSD(objNodeListUSD.item(0).getTextContent());
            p_objModelCoutainer.setEURCAD(objNodeListCAD.item(0).getTextContent());
            p_objModelCoutainer.computeUSDCAD();
            
            p_objView.setUSDValue(p_objModelCoutainer.getUSDCAD());
            
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
    }
}
