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
            String json = gson.toJson(namesExamenes, new TypeToken<List<String>>() {
            }.getType());
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
        modeloA.put("1", P2MA());
        modeloA.put("2", P3MA());
        modeloA.put("3", P4MA());
        modeloA.put("4", P5MA());
        modeloA.put("5", P6MA());
        modeloA.put("6", P7MA());
        modeloA.put("7", P8MA());
        modeloA.put("8", P9MA());
        modeloA.put("9", P10MA());

        return modeloA;
    }

    private Document getModeloB() {
        Document modeloB = new Document("Nombre", "Modelo B");

        modeloB.put("0", P1MB());

        modeloB.put("1", P2MB());
        modeloB.put("2", P3MB());
        modeloB.put("3", P4MB());
        modeloB.put("4", P5MB());
        modeloB.put("5", P6MB());
        modeloB.put("6", P7MB());
        modeloB.put("7", P8MB());
        modeloB.put("8", P9MB());
        modeloB.put("9", P10MB());

        return modeloB;
    }

    private Document getModeloC() {
        Document modeloC = new Document("Nombre", "Modelo C");

        modeloC.put("0", P1MC());
        modeloC.put("1", P1MC());
        modeloC.put("2", P1MC());
        modeloC.put("3", P1MC());
        modeloC.put("4", P1MC());
        modeloC.put("5", P1MC());
        modeloC.put("6", P1MC());
        modeloC.put("7", P1MC());
        modeloC.put("8", P1MC());
        modeloC.put("9", P1MC());

        return modeloC;
    }

    private Document P1MA() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "1. ¿En qué año gano España su primer Mundial de Futbol?");
        pregunta.put("correcta", 2);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "1985")
                .append("1", "1927")
                .append("2", "2010")
                .append("3", "Ninguna de las anteriores");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P2MA() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "2. Si tu conduces un autobus con 40 pasajeros y en la siguiente parada se bajan 13 pasajeros, ¿Quien conduce el autobus?");
        pregunta.put("correcta", "Yo");

        return pregunta;
    }

    private Document P3MA() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "3. Doscientos patos metidos en un cajón,¿cuantas patas y picos son?");
        pregunta.put("correcta", 0);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "2 picos y 4 patas")
                .append("1", "200 picos y 400 patas")
                .append("2", "Patata")
                .append("3", "Ninguna de las anteriores");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P4MA() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "select")
                .append("titulo", "4. ¿Que habilidad utiliza Naruto?");
        pregunta.put("correcta", 0);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Rasengan")
                .append("1", "Raikiri")
                .append("2", "Chidori")
                .append("3", "Pistola Agua");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P5MA() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "datalist")
                .append("titulo", "5. ¿Quien de los siguentes jugadores juega o ha jugado en el Real Madrid?");
        pregunta.put("correcta", 0);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Zidane")
                .append("1", "Michael Jordan")
                .append("2", "Godin")
                .append("3", "Lewandowski")
                .append("4", "Pau Gasol");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P6MA() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "6. Selecciona la palabra correcta:");
        pregunta.put("correcta", 2);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Havía")
                .append("1", "Abía")
                .append("2", "Había")
                .append("3", "Avía");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P7MA() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "7. ¿En qué mes una niña habladora habla menos?");
        pregunta.put("correcta", "Febrero");

        //add Respuestas.
        return pregunta;
    }

    private Document P8MA() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "8. ¿Cuántos meses tienen 28 dias ?:");
        pregunta.put("correcta", 0);
        pregunta.put("correcta", 1);
        pregunta.put("correcta", 2);
        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Enero, diciembre, agosto, junio.")
                .append("1", "Febrero, noviembre, octubre, julio.")
                .append("2", "Abril, marzo, mayo, septiembre.");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P9MA() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "select")
                .append("titulo", "9. Según la ley de Newton, la fuerza es proporcional a:");
        pregunta.put("correcta", 2);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "El tiempo")
                .append("1", "La velocidad")
                .append("2", "La aceleración")
                .append("3", "La posición");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P10MA() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "10. ¿Qué elementos forman el agua?");
        pregunta.put("correcta", 1);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "N, O y C")
                .append("1", "O y H")
                .append("2", "Tomates")
                .append("3", "Carbono y hidrógeno");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P1MB() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "1. ¿En qué año murió Jissus?");
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
