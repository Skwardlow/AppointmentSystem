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

function PrintText() {
    var lines = $('#inviteList').val().replace(/^[\n\r]+|[\n\r]+$/g,'').split(/[\n\r]+/);
    var text = "<p>Приглашения</p>";
    for (var i=0; i< lines.length; i++){
        text += "<p>" + lines[i] + "</p>";
    }
    var printWindow = window.open();
    printWindow.document.open('text/html');
    printWindow.document.write(text);
    printWindow.document.close();
    printWindow.focus();
    printWindow.print();
    printWindow.close();
}


$(function() {
    $('#print').click(function() {
        PrintText();
    });
});

function Change(event) {
    var selectElement = event.target;
    var value = selectElement.value;
    alert(value);
}

$(function() {
    $('#deleteSpec').click(function() {
        if(confirm("Удалить выбранного специалиста из базы данных?")){
            $.ajax({
                type: "POST",
                url: '/delete_spec',
                dataType : "text",
                data: $('#specList').val()
            }).done(function( msg ) {
                if(msg == 0){
                    alert("Вы удалили специалиста!");
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

$(function() {
    $('#reset').click(function() {
        if(confirm("Удалить все неиспользованные приглашения?")){
            $.ajax({
                type: "POST",
                url: '/invite_reset',
                dataType : "text",
            }).done(function( msg ) {
                if(msg == 0){
                    alert("Вы удалили все неиспользованные приглашения!");
                }else if (msg == 1){
                    alert("Произошла ошибка при удалении!");
                }
            });
        }
    });
});

$(function() {
    $('#giveOut').click(function() {
        var req = generateInvite($('#number').val());
        $.ajax({
            type: "POST",
            url: '/invite_create',
            dataType : "text",
            data: req
        }).done(function( msg ) {
            if(msg == 0){
                alert("Вы успешно создали приглашения!");
            }else if (msg == 1){
                alert("Произошла ошибка при добавлении!");
            }
        });
    });
});

function generateInvite(amount) {
    var toForm = '';
    var toBD = '';
    var invite = '';
    for ( var i = 0; i < amount; i++ ) {
        invite = makeinvite(10);
        toForm+= (i+1)+") " + invite + "\n";
        toBD+= invite + "&";
    }
    $('#inviteList').val(toForm);
    return toBD;
}

function makeinvite(length) {
    var result           = '';
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < length; i++ ) {
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
}