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
        modeloC.put("1", P2MC());
        modeloC.put("2", P3MC());
        modeloC.put("3", P4MC());
        modeloC.put("4", P5MC());
        modeloC.put("5", P6MC());
        modeloC.put("6", P7MC());
        modeloC.put("7", P8MC());
        modeloC.put("8", P9MC());
        modeloC.put("9", P10MC());

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
                .append("titulo", "1. ¿En qué año empezó la guerra civil española?");
        pregunta.put("correcta", 2);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "1985")
                .append("1", "1927")
                .append("2", "1936")
                .append("3", "Ninguna de las anteriores");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P2MB() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "2. ¿Cuánto son 2 + 2?");
        pregunta.put("correcta", "4");

        return pregunta;
    }

    private Document P3MB() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "3. ¿400/100?");
        pregunta.put("correcta", 0);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "4")
                .append("1", "2")
                .append("2", "0")
                .append("3", "Ninguna de las anteriores");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P4MB() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "select")
                .append("titulo", "4. ¿Que habilidad utiliza Picacku?");
        pregunta.put("correcta", 0);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Impactrueno")
                .append("1", "Surf")
                .append("2", "Ratata")
                .append("3", "Rasengan");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P5MB() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "datalist")
                .append("titulo", "5. ¿Quien de los siguentes jugadores juega o ha jugado en el Barsa?");
        pregunta.put("correcta", 0);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Etoo")
                .append("1", "Fabian")
                .append("2", "Casillas")
                .append("3", "Raul")
                .append("4", "Helguera");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P6MB() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "6. Selecciona la palabra incorrecta:");
        pregunta.put("correcta", 2);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Sabía")
                .append("1", "Había")
                .append("2", "Savía")
                .append("3", "Haber");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P7MB() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "7. ¿En qué año acabo la primera guerra Mundial?");
        pregunta.put("correcta", "1918");

        //add Respuestas.
        return pregunta;
    }

    private Document P8MB() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "8. ¿Cuántos meses tienen 25 dias ?:");
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

    private Document P9MB() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "select")
                .append("titulo", "9. ¿En que año nació Jissus?:");
        pregunta.put("correcta", 2);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "El tiempo")
                .append("1", "1")
                .append("2", "1996")
                .append("3", "7");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P10MB() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "10. ¿Qué elementos forman el agua oxigenada?");
        pregunta.put("correcta", 1);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "H y Fe")
                .append("1", "O y H")
                .append("2", "Hidrógeno y carbono")
                .append("3", "C, O y Fe");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

        private Document P1MC() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "1. ¿En qué año murió Jissus?");
        pregunta.put("correcta", 2);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Ninguna de las anteriores")
                .append("1", "1933")
                .append("2", "210")
                .append("3", "1");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P2MC() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "2. ¿Cuánto son 10 menos 10?");
        pregunta.put("correcta", "0");

        return pregunta;
    }

    private Document P3MC() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "3. ¿200/100?");
        pregunta.put("correcta", 0);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "2")
                .append("1", "200")
                .append("2", "0")
                .append("3", "Ninguna de las anteriores");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P4MC() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "select")
                .append("titulo", "4. ¿Que habilidad no utiliza Pikachu?");
        pregunta.put("correcta", 0);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Surf")
                .append("1", "Rayo")
                .append("2", "Cola Férrea")
                .append("3", "Trueno");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P5MC() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "datalist")
                .append("titulo", "5. ¿Quien de los siguentes jugadores juega o ha jugado en el Mallorca?");
        pregunta.put("correcta", 0);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Etoo")
                .append("1", "Jordan")
                .append("2", "Godin")
                .append("3", "Lewandowski")
                .append("4", "Pere Cantó");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P6MC() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "6. Selecciona la palabra alemana:");
        pregunta.put("correcta", 2);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Hello")
                .append("1", "Hola")
                .append("2", "Hallo")
                .append("3", "Holla");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P7MC() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "text")
                .append("titulo", "7. ¿En qué mes Jissus creó java?");
        pregunta.put("correcta", "Marzo");

        //add Respuestas.
        return pregunta;
    }

    private Document P8MC() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "checkbox")
                .append("titulo", "8. ¿Que meses son primavera ?:");
        pregunta.put("correcta", 0);
        pregunta.put("correcta", 1);
        pregunta.put("correcta", 2);
        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Marzo, abril, mayo, junio.")
                .append("1", "Julio, mayo, abril, marzo.")
                .append("2", "Mayo, marzo, junio, abril.");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P9MC() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "select")
                .append("titulo", "9. Según la ley de Newton, la fuerza es inversamente proporcional a:");
        pregunta.put("correcta", 2);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "La tierra")
                .append("1", "La masa")
                .append("2", "Al cuadrado de la distancia que los separa")
                .append("3", "La luz");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }

    private Document P10MC() {
        //add Pregunta
        Document pregunta = new Document()
                .append("tipo", "radio")
                .append("titulo", "10. ¿Qué es la luz?");
        pregunta.put("correcta", 1);

        //add Respuestas.
        Document respuesta = new Document()
                .append("0", "Materia y energía")
                .append("1", "Matería")
                .append("2", "Energía")
                .append("3", "Carbono");
        pregunta.put("respuesta", respuesta);

        return pregunta;
    }
    
}
