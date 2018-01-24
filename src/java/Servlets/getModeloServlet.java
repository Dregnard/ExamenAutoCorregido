/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

/**
 *
 * @author dam2a26
 */
public class getModeloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Connect
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://fabianyjoan:monster123@cluster0-nua52.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);
        //Create Database
        MongoDatabase database = mongoClient.getDatabase("Examen");

        //MongoCollection para meter todos los documentos
        MongoCollection<Document> ex = database.getCollection("Examenes");

        List<Document> examenes = database.getCollection("Examenes").find().into(new ArrayList<>());
        List<String> namesExamenes = new ArrayList<>();
        for (Document examen : examenes) {
            namesExamenes.add(examen.getString("Modelo"));
        }
        mongoClient.close();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(namesExamenes);
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }

    @Override
    public void init() throws ServletException {
        try {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://fabianyjoan:monster123@cluster0-nua52.mongodb.net/test");
            MongoClient mongoo = new MongoClient(uri);

            MongoDatabase db = mongoo.getDatabase("Examen");

            MongoCollection<Document> collection = db.getCollection("Examenes");

            Document pregunta = new Document("pregunta", "select-1")
                    .append("tipo", "select")
                    .append("titulo", "¿Cuántos centímetros tiene un metro?");

            Document respuesta = new Document("h", 28)
                    .append("0", 10)
                    .append("1", 100)
                    .append("2", 100);

            pregunta.put("respuesta", respuesta);
            collection.insertOne(pregunta);

            collEx.insertOne(getModeloA());
            //collEx.insertOne(getModeloB());
            //collEx.insertOne(getModeloC());
            mongoo.close();

        } catch (Exception e) {

        }

    }

    //ejemplo
    private Document getModeloA() {
        Document modeloA = new Document("Nombre", "Modelo A");

        modeloA.put("0", P1MA());
        return modeloA;
    }

    private Document P1MA() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿1+1?");
        pregunta.put("correcta", 2);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "la suma es 1")
                .append("1", " es 2")
                .append("2", " es 5")
                .append("3", "es 8");
        pregunta.put("respuesta", respuesta);
        return pregunta;
    }

}
