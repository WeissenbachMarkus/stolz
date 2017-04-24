/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stolz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author markus
 */
public class ReadXML
{

    String xmlPath;

    public void setXmlPath(String xmlPath)
    {
        this.xmlPath = xmlPath;
    }

    public List<Employee> read()
    {
        // Get the DOM Builder Factory
        DocumentBuilderFactory factory
                = DocumentBuilderFactory.newInstance();

        try
        {
            //Get the DOM Builder
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();

        //Load and Parse the XML document
            //document contains the complete XML as a Tree.
            org.w3c.dom.Document document;
            document = builder.parse("C:\\Users\\markus\\Desktop\\employees.xml");
            List<Employee> empList = new ArrayList<>();

            //Iterating through the nodes and extracting the data.
            NodeList nodeList = document.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++)
            {

                //We have encountered an <employee> tag.
                Node node = nodeList.item(i);
                if (node instanceof Element)
                {
                    Employee emp = new Employee();
                    emp.id = node.getAttributes().
                            getNamedItem("id").getNodeValue();

                    NodeList childNodes = node.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++)
                    {
                        Node cNode = childNodes.item(j);

                        //Identifying the child tag of employee encountered. 
                        if (cNode instanceof Element)
                        {
                            String content = cNode.getLastChild().
                                    getTextContent().trim();
                            switch (cNode.getNodeName())
                            {
                                case "firstName":
                                    emp.firstName = content;
                                    break;
                                case "lastName":
                                    emp.lastName = content;
                                    break;
                                case "location":
                                    emp.location = content;
                                    break;
                            }
                        }
                    }
                    empList.add(emp);
                }

            }
            return empList;

        } catch (ParserConfigurationException ex)
        {
            Logger.getLogger(ReadXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex)
        {
            Logger.getLogger(ReadXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(ReadXML.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            return null;
        }
    }

}

class Employee
{

    String id;
    String firstName;
    String lastName;
    String location;

    @Override
    public String toString()
    {
        return firstName + " " + lastName + "(" + id + ")" + location;
    }
}
