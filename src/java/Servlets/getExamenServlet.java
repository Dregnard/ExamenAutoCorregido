package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

/**
 *
 * @author dam2a26
 */
public class getExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Connect

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://fabianyjoan:redbull@cluster0-nua52.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);
        //Create Database
        MongoDatabase database = mongoClient.getDatabase("Examen");

        //Create collection
        List<Document> examenes = database.getCollection("Examenes").find().into(new ArrayList<>());
        List<Document> examenD = new ArrayList<>();
        for (Document examen : examenes) {
            if (examen.getString("Nombre").equals(request.getParameter("modelo"))) {
                examenD.add(examen);
            }
        }
        mongoClient.close();
        Gson gson = new Gson();
        String json = gson.toJson(examenD, new TypeToken<List<Document>>() {
        }.getType());
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
