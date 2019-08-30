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
            url: '/get_dayclient',
            dataType : "json",
            data: specDay
        }).done(function( msg ) {
            var beginDay = 9;
            for(var i = 0; i < msg.length; i++) {
                if(msg[i].id == null){
                    $("#outputDate")
                        .append('<div class="input-group">\n' +
                            '                    <div class="input-group-prepend">\n' +
                            '                        <div class="input-group-text">\n' +
                            '                            <input type="checkbox" id = "' + "indexOfDay" + i + '" value="'+i+'"  aria-label="Checkbox for following text input">\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                    <input disabled type="text" class="form-control"  aria-label="Text input with checkbox" value="'+ $('#datepicker').val() + " - " + (beginDay+msg[i].indexInDay) + ":00" + " - " + "Свободно" +'">\n' +
                            '                </div>')
                }else{
                    $("#outputDate")
                        .append('<div class="input-group">\n' +
                            '                    <div class="input-group-prepend">\n' +
                            '                        <div class="input-group-text">\n' +
                            '                            <input disabled type="checkbox" id = "' + "indexOfDay" + i + '" value="'+i+'"  aria-label="Checkbox for following text input">\n' +
                            '                        </div>\n' +
                            '                    </div>\n' +
                            '                    <input disabled type="text" class="form-control"  aria-label="Text input with checkbox" value="'+msg[i].dateOfAppointment + " - " + (beginDay+msg[i].indexInDay) + ":00" + " - " + "Занято" +'">\n' +
                            '                </div>')

                }



            }
        });

    }

}

$(function() {
    $('#writeOn').click(function() {
        if(confirm("Записатся на прием?")){
            var clearUsers = {};
            var box = '';
            clearUsers.spec = $('#specList').val();
            clearUsers.date = $('#datepicker').val();
            $('input:checkbox:checked').each(function(){
                box = "box" + $(this).val();
                clearUsers[box] = $(this).val();
            });

            $.ajax({
                type: "POST",
                url: '/write_on',
                dataType : "text",
                data: clearUsers
            }).done(function( msg ) {
                if(msg == 0){
                    alert("Вы Записались!");
                }else if (msg == 1){
                    alert("Произошла ошибка при записи!");
                }
                Change();
            });
        }
    });
});