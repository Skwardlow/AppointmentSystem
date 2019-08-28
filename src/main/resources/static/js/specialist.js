$(document).ready(function(){
    $('#datepicker').datepicker({
        weekStart: 1,
        daysOfWeekHighlighted: "6,0",
        autoclose: true,
        todayHighlight: true,
    });
    $('#datepicker').datepicker("setDate", new Date());


    var array = ["Петров","Иванов","Васечкин","Петренко"];

    var selectList = document.getElementById("specList");

    for (var i = 0; i < array.length; i++) {
        var option = document.createElement("option");
        option.value = i;
        option.text = array[i];
        selectList.appendChild(option);
    }
});

$(function() {
    $('#deleteDay').click(function() {
        if(confirm("Удалить все записи у специалиста за это число?")){
            $.ajax({
                type: "POST",
                url: '/delete_day',
                dataType : "text",
                data: $('#datepicker').val()
            }).done(function( msg ) {
                if(msg == 0){
                    alert("Вы удалили все записи!");
                }else if (msg == 1){
                    alert("Произошла ошибка при удалении!");
                }
            });
        }
    });
});