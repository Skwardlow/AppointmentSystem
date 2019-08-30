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

function Change(event) {
    $("#outputDate").html("");
    var myDay = {};
    myDay.date = $('#datepicker').val();
    $.ajax({
        type: "POST",
        url: '/get_myDay',
        dataType : "json",
        data: myDay
    }).done(function( msg ) {
        var beginDay = msg[0].beginWorkDayHour;
        for(var i = 0; i < msg.length; i++) {
            $("#outputDate")
                .append('<div class="input-group">\n' +
                    '                    <div class="input-group-prepend">\n' +
                    '                        <div class="input-group-text">\n' +
                    '                            <input type="checkbox" id = "' + "indexOfDay" + i + '" value="'+msg[i].id+'"  aria-label="Checkbox for following text input">\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                    <input disabled type="text" class="form-control"  aria-label="Text input with checkbox" value="'+msg[i].dateOfAppointment + " - " + (beginDay+msg[i].indexInDay) + ":00" + " - " + msg[i].cusername +'">\n' +
                    '                </div>')


        }
    });

}

$(function() {
    $('#clearUsers').click(function() {
        if(confirm("Удалить выбранные записи??")){
            var clearUsers = {};
            var box = '';
            $('input:checkbox:checked').each(function(){
                box = "box" + $(this).val();
                clearUsers[box] = $(this).val();
            });

            $.ajax({
                type: "POST",
                url: '/clear_users',
                dataType : "text",
                data: clearUsers
            }).done(function( msg ) {
                if(msg == 0){
                    alert("Вы удалили записи!");
                }else if (msg == 1){
                    alert("Произошла ошибка при удалении!");
                }
            });
        }
    });
});

$(function() {
    $('#deleteDay').click(function() {
        if(confirm("Удалить все записи у специалиста за это число?")){
            var dateDay = {};
            dateDay.data = $('#datepicker').val();
            $.ajax({
                type: "POST",
                url: '/delete_myDay',
                dataType : "text",
                data: dateDay
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
