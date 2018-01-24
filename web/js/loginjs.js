/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function () {

    cargarExamen();

    $("#comenzar").click(function () {
        var modelo = $('#modeloE').val();
        var dni = $("#dni").val();
        guardarLS(modelo, dni);
        window.location = "prueba1.html";
    });





};




function nif(dni) {
    var numero
    var letr
    var letra
    var expresion_regular_dni

    expresion_regular_dni = /^\d{8}[a-zA-Z]$/;

    if (expresion_regular_dni.test(dni) == true) {
        numero = dni.substr(0, dni.length - 1);
        letr = dni.substr(dni.length - 1, 1);
        numero = numero % 23;
        letra = 'TRWAGMYFPDXBNJZSQVHLCKET';
        letra = letra.substring(numero, numero + 1);
        if (letra != letr.toUpperCase()) {
            alert('Dni erroneo, la letra del NIF no se corresponde');
        } else {
            alert('Dni correcto');
        }
    } else {
        alert('Dni erroneo, formato no válido');
    }
}

function cargarExamen() {
    var emess = "Error desconocido";


    $.ajax({
        type: "GET",
        url: "getModeloServlet",
        dataType: "json",

        success: function (jsn) {
            $.each(jsn, function (i, modelo) {
                jQuery('<option/>', {html: modelo}).appendTo($('#modeloE'));
            });

        },
        error: function (e) {
            $('#modeloE').html('<option id="-1">none available</option>');
            alert(e["responseJSON"]["error"]);
        }
    });

}
//
//function hacerExamen() {
//    var emess = "Error desconocido";
//    var modelo = $('#modeloE').val();
//    var dni = $("#dni").val();
//
//    $.ajax({
//        type: "POST",
//        url: "getExamenServlet",
//        dataType: "json",
//        data: {modelo: modelo},
//        success: function (u) {
//            if (u["mess"] === "el dni es incorrecto") {
//                $("#dni").focus();
//                alert(u["mess"]);
//                return;
//            }
//            //location.reload();
//            guardarLS(modelo, dni);
//            window.location = "prueba1.html";
//        },
//        error: function (e) {
//
//
//            alert(e["responseJSON"]["error"]);
//        }
//    });
//
//}





function guardarLS(modelo, dni) {
    sessionStorage.removeItem("_dni");
    sessionStorage.removeItem("_modelo");

    sessionStorage.setItem("_modelo", modelo);
    sessionStorage.setItem("_dni", dni);
}