/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
            //Connect
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://fabianyjoan:monster123@cluster0-nua52.mongodb.net/test");
            MongoClient mongoClient = new MongoClient(uri);
            //Create Database
            MongoDatabase database = mongoClient.getDatabase("Examen");

            //Create collection
            MongoCollection<Document> collEx = database.getCollection("Examenes");
            collEx.drop();
            collEx = database.getCollection("Examenes");

            //collEx.insertOne(getModeloA());
            //collEx.insertOne(getModeloB());
            //collEx.insertOne(getModeloC());
            mongoClient.close();

        } catch (Exception e) {

        }

    }
}
