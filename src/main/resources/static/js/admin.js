$(document).ready(function(){
    $('#datepicker').datepicker({
        weekStart: 1,
        daysOfWeekHighlighted: "6,0",
        autoclose: true,
        todayHighlight: true,
    });
    $('#datepicker').datepicker("setDate", new Date());

    $.get("/users_get",
        function(data){
            var selectList = document.getElementById("specList");
            for(var i = 0; i < data.length; i++) {
                var option = document.createElement("option");
                option.value = data[i].username ;
                option.text = data[i].name;
                selectList.appendChild(option);
            }
        }
    );
    $('#specList');

});


function Change(event) {
    if($('#specList').val() != null){
        $("#outputDate").html("");
        var specDay = {};
        specDay.spec = $('#specList').val();
        specDay.date = $('#datepicker').val();
        $.ajax({
            type: "POST",
            url: '/get_dayspec',
            dataType : "json",
            data: specDay
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

}


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
    $('#deleteSpec').click(function() {
        if(confirm("Удалить выбранного специалиста из базы данных?")){
            var specLogin = {};
            specLogin.login = $('#specList').val();
            $.ajax({
                type: "POST",
                url: '/delete_spec',
                dataType : "text",
                data: specLogin
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
            var dateDay = {};
            dateDay.data = $('#datepicker').val();
            dateDay.spec = $('#specList').val();
            $.ajax({
                type: "POST",
                url: '/delete_day',
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
    var toBD = {};
    var name = '';

    var invite = '';
    for ( var i = 0; i < amount; i++ ) {
        invite = makeinvite(10);
        toForm+= (i+1)+") " + invite + "\n";
        name = "invite" + i;
        toBD[name] = invite;
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