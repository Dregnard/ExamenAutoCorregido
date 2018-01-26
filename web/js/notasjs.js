$(document).ready(function () {
    $("#btnAtras").click(function () {
        window.location = "index.html";
    });
    cargarNotas();
});

function cargarNotas() {
    var url = "notasServlet";
    $("#carga").show();
    $.ajax({
        method: "GET",
        url: url,
        data: {},
        success: function (jsn) {
            //vaciar tabla para una new:
            $("#tablaNotas > tbody").empty();
            $.each(jsn, function (i, item) {
                var dniR = (item.dni).substring(0, 3) + " * * * * " + (item.dni).charAt(item.dni.length - 2)+ (item.dni).charAt(item.dni.length - 1);
                //https://es.stackoverflow.com/questions/17308/enmascarar-input-text-javascript
                var modeloER = item.modeloE;
                var notaR = item.nota;
                var row = "<tr><td>" + dniR + "</td><td>" + modeloER + "</td><td>" + notaR + "</td></tr>";
                $("#tablaNotas > tbody").append(row);
            });
            $("#carga").hide();
        },
        error: function (e) {
            $("#carga").hide();
            if (e["responseJSON"] === undefined) {
                alert("error desconocido");
            } else {
                alert(e["responseJSON"]["error"]);
            }
        }
    });
}