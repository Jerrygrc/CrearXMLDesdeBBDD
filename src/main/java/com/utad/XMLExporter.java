package com.utad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class XMLExporter {

    public static void main(String[] args) {
        String filePath = "./src/main/java/com/utad/regalos.xml";
        exportDataToXML(filePath);
    }

    public static void exportDataToXML(String fileName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection(); // Obtenemos la conexión
            stmt = conn.createStatement();
            String query = "SELECT * FROM regalos";
            rs = stmt.executeQuery(query);

            // Creamos un documento XML vacío
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Elemento raíz <regalos>
            Element rootElement = doc.createElement("regalos");
            doc.appendChild(rootElement);

            // Procesamos cada fila de la consulta y agregamos elementos XML
            while (rs.next()) {
                // Elemento <regalo>
                Element regaloElement = doc.createElement("regalo");

                // Creamos elementos para cada columna y los añadimos
                Element nombreElement = doc.createElement("nombre");
                nombreElement.appendChild(doc.createTextNode(rs.getString("nombre")));
                regaloElement.appendChild(nombreElement);

                Element tipoRegaloElement = doc.createElement("tipo");
                tipoRegaloElement.appendChild(doc.createTextNode(rs.getString("regalo")));
                regaloElement.appendChild(tipoRegaloElement);

                Element precioElement = doc.createElement("precio");
                precioElement.appendChild(doc.createTextNode(String.valueOf(rs.getDouble("euros"))));
                regaloElement.appendChild(precioElement);

                // Añadimos <regalo> al elemento raíz <regalos>
                rootElement.appendChild(regaloElement);
            }

            // Convertimos el documento XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Configuración para que el XML generado esté indentado
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");

            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(fileName));

            transformer.transform(domSource, streamResult);
            System.out.println("Archivo XML generado exitosamente: " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
