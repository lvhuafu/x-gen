package cn.javass.xgen.util.readxml;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**---还可以优化---相同的xml做缓存
 * Created by HASEE on 2017/6/29.
 */
public class XmlUtil {
    private XmlUtil(){

    }
    public static Document getDocument(String filePathName)throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(XmlUtil.class.getClassLoader().getResourceAsStream(filePathName));
        //去除空格
        doc.normalize();
        return doc;
    }
}
