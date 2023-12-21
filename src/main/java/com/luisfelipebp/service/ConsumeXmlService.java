package com.luisfelipebp.service;

import com.luisfelipebp.config.ConfigurationRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumeXmlService {

    public List<String> consumeXml() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml");
        String response = restTemplate.getForObject(uri, String.class);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        ByteArrayInputStream input = new ByteArrayInputStream(response.getBytes("UTF-8"));
        Document doc = builder.parse(input);
        NodeList lista = doc.getElementsByTagName("codinome");

        List<String> liga = new ArrayList<>();

        for(int i = 0; i < lista.getLength(); i++){
            Node node = lista.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
               String codinome = node.getTextContent();
               liga.add(codinome);
            }
        }
        return liga;

    }
}
