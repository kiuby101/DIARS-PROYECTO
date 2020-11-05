$(document).ready(function () {
    var tipodetalle="<div class='col-6 mb-3'>"+"<input type='text' name='tipoDetalle[]' id='' class='form-control' placeholder='Ejm:otros tipo'>"+"</div>";
    var detalle="<div class='col-6 mb-3'>"+"<input type='text' name='detalle[]' id='' class='form-control' placeholder='Ejm:otros detalles'>"+"</div>";
    $('.btn-agregar').click(function () {
        var insertar=document.getElementById("insertardetalles");
        insertar.insertAdjacentHTML('beforeend' ,tipodetalle);
        insertar.insertAdjacentHTML('beforeend' ,detalle);


    })
})