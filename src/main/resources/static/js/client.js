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