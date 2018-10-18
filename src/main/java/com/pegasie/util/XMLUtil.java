package com.pegasie.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import java.io.StringReader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import static com.pegasie.base.TestBase.logger;

public class XMLUtil {
    public static void getListFromXML(String sHtml, String sTag) {
        String sFunc = "getListFromXML";
        System.out.println("Function:" +sFunc+"--- Started");
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(sHtml));
            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName(sTag);



            Element e1 = (Element) nodes.item(0);
            NodeList g1 = e1.getElementsByTagName("g");
            Element e2 = (Element) g1.item(0);
            NodeList gs = e2.getChildNodes();
            int l = gs.getLength();
            System.out.println(l);
            for (int i = 0; i < l; i++) {
                Element e = (Element) gs.item(i);
                String temp = e.getTextContent();
                System.out.println(temp);
            }


        }
        catch (Exception e){
            System.out.println("<<" + e.getLocalizedMessage() + ">>");
            logger.error(e.getMessage());
            //logger.info("The requirement total number is:" + RequirementCount);
            logger.warn("Function name is:" + sFunc + "--Failed");
        }

    }

}
