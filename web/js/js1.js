//cnt
var RADIO = "radio";
var CHECKBOX = "checkbox";
var DATALIST = "datalist";
var SELECT = "select";
var TEXT = "text";

//var
var http = null;
var xmlDOC = null;
var form = null;
var nPreguntas = 0;
var raton = 0;
var notaF = 0;
var nota = 0;
var tipo = [];
var respuestas = [];
var soluciones = [];

$(document).ready(function () {
    tituloModelo();
    cargarPreguntas();
    $("#corregir").click(function () {
        corregir2();
    });
});

function tituloModelo() {
    $("#tituloEx").html(sessionStorage.getItem("_modelo").toString());
}

function cargarPreguntas() {
    var emess = "Error desconocido";

    var dni = sessionStorage.getItem("_dni");
    var modelo = sessionStorage.getItem("_modelo");

    $("#carga").show();
    $.ajax({
        type: "POST",
        url: "getExamenServlet",
        dataType: "json",
        data: {modelo: modelo},
        success: function (jsn) {
            $.each(jsn[0], function (i, tipo) {
                soluciones.push(tipo.correcta);
                var type = tipo.tipo;
                switch (type) {
                    case "checkbox":
                        typeCheckbox2(tipo, i);
                        break;
                    case "datalist":
                        typeDatalist2(tipo, i);
                        break;
                    case "radio":
                        typeRadio2(tipo, i);
                        break;
                    case "select":
                        typeSelect2(tipo, i);
                        break;
                    case "text":
                        typeText2(tipo, i);
                        break;
                }

            });

            $("#carga").hide();
            playTime();
        },
        error: function (e) {
            alert("Error");

            $("#carga").hide();
            alert(e["responseJSON"]["error"]);
        }
    });

}

function typeRadio2(preg, i) {
    var aa = i;
    var $div = $("<div />").add("pregunta");
    $div.append("<br>Pregunta: " + preg.titulo + "<br>");
    $.each(preg.respuesta, function (i, tipo) {
        $div.append($("<br><input type='radio' name='" + preg.tipo+aa + "'required> " + tipo + "<br>"));
    });
    $("#a").append($div);
}

function typeCheckbox2(preg, i) {
    var aa = i;
    var $div = $("<div />").add("pregunta");
    $div.append("<br>Pregunta: " + preg.titulo + "<br>");
    $.each(preg.respuesta, function (i, tipo) {
        $div.append($("<br><input type='checkbox' name='" + preg.tipo+aa + "'>" + tipo + "<br>"));
    });
    $("#a").append($div);
}

function typeSelect2(preg, i) {
    var $div = $("<div />").add("pregunta");
    $div.append("<br>Pregunta: " + preg.titulo + "<br>");
    var $select = $("<br><select class='selectN'" + i + "/><br>");
    $select.append($("<br><option>Selecciona</option><br>"));
    $.each(preg.respuesta, function (i, resp) {
        $select.append($("<br><option>" + resp + "</option><br>"));
    });
    $div.append($select);
    $("#a").append($div);
}

function typeText2(preg, i) {
    var aa = i;
    var $div = $("<div />").add("pregunta");
    $div.append("<br>Pregunta: " + preg.titulo + "<br>");
    var $text = $("<br><input type='text' name='" + preg.tipo+aa + "' required><br>");
    $div.append($text);
    $("#a").append($div);
}

function typeDatalist2(preg, i) {
    var aa = i;
    var $div = $("<div />").add("pregunta");
    $div.append("<br>Pregunta: " + preg.titulo + "<br>");
    var $sel = $("<select multiple required/>");
    $.each(preg.respuesta, function (i, resp) {
        $sel.append($("<option>" + resp + "</option>"));
    });
    $div.append($sel);
    $("#a").append($div);
}

function corregir2() {
    //Corregir examen
    enviarNota();
    var dni = sessionStorage.getItem("_dni");
    var modelo = sessionStorage.getItem("_modelo");
    $.each(jsn[0], function (i, tipo) {
        var aa = i;
        switch (tipo) {
            case "radio":
                if ($("input[name=radio0]:checked").val() === soluciones[0]) {
                    nota++;
                }
                if ($("input[name=radio5]:checked").val() === soluciones[5]) {
                    nota++;
                }
                if ($("input[name=radio9]:checked").val() === soluciones[9]) {
                    nota++;
                }
                break;
            case "text":
                if ($("#text1").val() === soluciones[1]) {
                    nota++;
                }
                if ($("#text6").val() === soluciones[6]) {
                    nota++;
                }
                break;
            case "checkbox":
                if ($("input[name=checkbox2]:checked").val() === soluciones[2]) {
                    nota++;
                }
                if ($("input[name=checkbox7]:checked").val() === soluciones[7]) {
                    nota++;
                }
                break;
            case "selectN":
                if ($("#selectN")[0].selectedIndex - 1 === soluciones[3]) {
                    nota++;
                }
                break;
            case "multiple":
                var array = $("#multiple").find('option').get();
                for (var j = 0; j < array.length; j++) {
                    if (array[j].selected === soluciones[4]) {
                        nota++;
                    }
                }
                break;
        }
    });
    alert("NOTA: " + nota);
    enviarNota();
}

function enviarNota() {
    alert("nota enviada");
    var dni = sessionStorage.getItem("_dni");
    var modelo = sessionStorage.getItem("_modelo");
    var url = "notasServlet";
    $.ajax({
        method: "POST",
        url: url,
        data: {dni: dni, modeloE: modelo, nota: nota},
        success: function (rsp) {
            alert("Guardado Correctamente", "Nota: " + nota);
            window.location = "notas.html";
        },
        error: function (e) {
            if (e["responseJSON"] === undefined) {
                alert("ERROR DESCONOCIDO", "Inténtelo más tarde");
            } else {
                alert(e["responseJSON"]["error"]);
            }
        }
    });
}

function playTime() {
    var t;
    var temp = new temps(720, 0);
    total = setInterval(function () {
        t = temp.tratar();
        var mm = temp.getMinutos();
        if (mm < 10) {
            mm = "0" + mm;
        }
        var ss = temp.getSegundos();
        if (ss < 10) {
            ss = "0" + ss;
        }

        document.getElementById("tempM").innerHTML = mm;
        document.getElementById("tempS").innerHTML = ss;
        if (t === false) {
            corregir2();
        }
    }, 10);
}