package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ParseOtherTypeAction {
    public static void main(String[] args) {
        //1.创建DocumentBuilderFactory对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.创建DocumentBuilder对象
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document d = builder.parse("src/main/resources/mainline.xml");
            NodeList sList = d.getElementsByTagName("Placemark");
            element(sList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用Element方式
    public static void element(NodeList list) {
        DataBean bean = new DataBean();
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            NodeList childNodes = element.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    //获取节点
//                    System.out.print(childNodes.item(j).getNodeName() + ":");
                    //获取节点值
//                    if (childNodes.item(j).getNodeName().equals("name")||childNodes.item(j).getNodeName().equals("coordinates")){
//                        System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
//                    }

                    if (childNodes.item(j).getNodeName().equals("name")) {
                        String value = childNodes.item(j).getFirstChild().getNodeValue();
                        if (value.substring(0, 1).equals("K") && !value.contains("(") && !value.contains(")")) {
                            bean.setName(childNodes.item(j).getFirstChild().getNodeValue());
                        }
                    }

                    if (childNodes.item(j).getNodeName().equals("Point")) {
                        String[] locData = childNodes.item(j).getChildNodes().item(0).getNextSibling().getFirstChild().getNodeValue().split(",");
                        bean.setLon(locData[0]);
                        bean.setLat(locData[1]);
                        System.out.println(bean.toString());
                    }
                }
            }
        }
    }
}
