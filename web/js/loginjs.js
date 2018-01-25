/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var booleanDni = false;

window.onload = function () {

    cargarExamen();

    $("#comenzar").click(function () {
        var modelo = $('#modeloE').val();
        var dni = $("#dni").val();
        nif(dni);
        if (modelo != null) {
            if (booleanDni === true) {
                guardarLS(modelo, dni);
                window.location = "prueba1.html";
            }
        } else {
            alert("Espera a que cargue el modelo")
        }

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
            booleanDni = false;
        } else {
            alert('Dni correcto');
            booleanDni = true;
        }
    } else {
        alert('Dni erroneo, formato no válido');
        booleanDni = false;
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





function guardarLS(modelo, dni) {
    sessionStorage.removeItem("_dni");
    sessionStorage.removeItem("_modelo");

    sessionStorage.setItem("_modelo", modelo);
    sessionStorage.setItem("_dni", dni);
}