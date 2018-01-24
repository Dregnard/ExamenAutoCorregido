/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
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
        try {
            //Connect
            MongoClientURI uri = new MongoClientURI("mongodb+srv://fabianyjoan:redbull@cluster0-nua52.mongodb.net/test");
            MongoClient mongoClient = new MongoClient(uri);
            //Create Database
            MongoDatabase database = mongoClient.getDatabase("Examen");
            List<Document> examenes = database.getCollection("Examenes").find().into(new ArrayList<>());
            List<String> namesExamenes = new ArrayList<>();
            for (Document examen : examenes) {
                namesExamenes.add(examen.getString("Nombre"));
            }
            Gson gson = new Gson();
            String json = gson.toJson(namesExamenes, new TypeToken<List<String>>(){}.getType());
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                out.println(json);
            }
        } catch (Exception e) {
            System.out.println("Error todo guapo");
        }
    }

    @Override
    public void init() throws ServletException {
        try {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://fabianyjoan:redbull@cluster0-nua52.mongodb.net/test");
            MongoClient mongoo = new MongoClient(uri);

            MongoDatabase db = mongoo.getDatabase("Examen");

            MongoCollection<Document> collection = db.getCollection("Examenes");

            collection.drop();

            collection.insertOne(getModeloA());
            collection.insertOne(getModeloB());
            collection.insertOne(getModeloC());
            mongoo.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    //ejemplo
    private Document getModeloA() {
        Document modeloA = new Document("Nombre", "Modelo A");

        modeloA.put("0", P1MA());
        return modeloA;
    }

    private Document getModeloB() {
        Document modeloB = new Document("Nombre", "Modelo B");

        modeloB.put("0", P1MB());
        return modeloB;
    }

    private Document getModeloC() {
        Document modeloC = new Document("Nombre", "Modelo C");

        modeloC.put("0", P1MC());
        return modeloC;
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

    private Document P1MB() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿Capital de Bielorrusia?");
        pregunta.put("correcta", 2);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Dublin")
                .append("1", "Minsk")
                .append("2", "Paris")
                .append("3", "Moscu");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P1MC() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "¿5*20?");
        pregunta.put("correcta", 2);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "102")
                .append("1", "50")
                .append("2", "100")
                .append("3", "30");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

}

//FindIterable<Document> list = ex.find();
//            List<Document> namesExamenes = new ArrayList<>();
//            for (Document d : list) {
//
//                d.getString("Nombre");
//                System.out.println(d.getString("Nombre"));
//            }
//
//       List<Document> examenes = database.getCollection("Examenes").find().into(new ArrayList<>());
//        List<String> namesExamenes = new ArrayList<>();
//        for (Document examen : examenes) {
//            namesExamenes.add(examen.getString("Nombre"));
//        }
//        mongoClient.close();
//
//        Gson gson = new Gson();
//        String json = gson.toJson(namesExamenes);
//        response.setContentType("application/json");
//        try (PrintWriter out = response.getWriter()) {
//            out.println(json);
//        }
