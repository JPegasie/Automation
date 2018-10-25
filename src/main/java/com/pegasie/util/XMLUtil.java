package com.pegasie.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;




import static com.pegasie.base.TestBase.logger;

public class XMLUtil {
    public static List<String> getListFromXML(String sHtml, String sTag) {
        String sFunc = "getListFromXML";
        System.out.println("Function:" +sFunc+"--- Started");
       // List<String []> tableUI;
        List<String> tableUI = new ArrayList<>();

        try {

            //String results = StringEscapeUtils.escapeJava(sHtml);

            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(sHtml));
            Document doc = db.parse(is);


            NodeList g1 = doc.getElementsByTagName(sTag);
            Element e2 = (Element) g1.item(0);
            NodeList gs = e2.getChildNodes();
            int l = gs.getLength();
            //System.out.println(l);
            int iActualReq=0;
            for (int i = 0; i < l; i++) {
                Element e = (Element) gs.item(i);
                String temp = e.getTextContent();
                if (! temp.isEmpty()) {
                    System.out.println(temp);
                    //tableUI [iActualReq] =temp;
                    tableUI.add(temp);
                    iActualReq = iActualReq + 1;
                    //tableUI.add(iActualReq);
                }



            }

            //System.out.println("iActualReq number is:"+iActualReq);

            System.out.println("Function:"+sFunc+"----Ended");


        }
        catch (Exception e){
            System.out.println("<<" + e.getLocalizedMessage() + ">>");
            logger.error(e.getMessage());
            //logger.info("The requirement total number is:" + RequirementCount);
            logger.warn("Function name is:" + sFunc + "--Failed");
        }
        finally {
            return tableUI;
        }

    }

}
