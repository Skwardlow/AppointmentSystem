function checkValid(formValid){
    $('input').each(function() {
        if ($('#materialClient').prop("checked")){
            if($(this).attr("id") != "key"){
                var formGroup = $(this).parents('.form-group');
                var glyphicon = formGroup.find('.form-control-feedback');
                if (this.checkValidity()) {
                    formGroup.addClass('has-success').removeClass('has-error');
                    glyphicon.addClass('glyphicon-ok').removeClass('glyphicon-remove');
                } else {
                    formGroup.addClass('has-error').removeClass('has-success');
                    glyphicon.addClass('glyphicon-remove').removeClass('glyphicon-ok');
                    formValid = false;
                }
            }
        }
        if ($('#materialSpec').prop("checked")){
            var formGroup = $(this).parents('.form-group');
            var glyphicon = formGroup.find('.form-control-feedback');
            if (this.checkValidity()) {
                formGroup.addClass('has-success').removeClass('has-error');
                glyphicon.addClass('glyphicon-ok').removeClass('glyphicon-remove');
            } else {
                formGroup.addClass('has-error').removeClass('has-success');
                glyphicon.addClass('glyphicon-remove').removeClass('glyphicon-ok');
                formValid = false;
            }
        }
    });
    return formValid;
}

$(function() {
    $('#button').click(function() {
        $('#user-alert').hide();
        $('#email-alert').hide();
        var formValid = true;
        formValid = checkValid(formValid);
        //если форма валидна, то
        if (formValid) {
            var name = $("#name").val();
            var family = $("#family").val();
            var father = $("#father").val();
            var email = $("#email").val();
            var username = $("#username").val();
            var password = $("#password").val();
            if ($('#materialClient').prop("checked")) {
                $.ajax({
                    type: "POST",
                    url: '/reg_user',
                    dataType: "text",
                    data: {
                        family: family,
                        name: name,
                        father: father,
                        email: email,
                        username: username,
                        password: password
                    }
                }).done(function (msg) {
                    alert(msg);
                    if (msg == 0) {
                        $('#user-alert').hide();
                        alert("Вы успешно зарегестрировались, далее вы попадете на главную страницу");
                        document.location.replace("/");
                    } else if (msg == 1) {
                        $('#email-alert').show();
                        $('#email').val('');
                        checkValid();
                    } else if (msg == 2) {
                        $('#user-alert').show();
                        $('#username').val('');
                        checkValid();
                    } else if (msg == 3) {
                        $('#user-alert').show();
                        $('#username').val('');
                        $('#email-alert').show();
                        $('#email').val('');
                        checkValid();
                    }
                });
            }
            if ($('#materialSpec').prop("checked")){
                var invite = $('#key').val();
                $.ajax({
                    type: "POST",
                    url: '/reg_spec',
                    dataType: "text",
                    data: {
                        family: family,
                        name: name,
                        father: father,
                        email: email,
                        username: username,
                        ivite: invite,
                        password: password
                    }
                }).done(function (msg) {
                    alert(msg);
                    if (msg == 0) {
                        $('#user-alert').hide();
                        alert("Вы успешно зарегестрировались, далее вы попадете на главную страницу");
                        document.location.replace("/");
                    } else if (msg == 1) {
                        $('#email-alert').show();
                        $('#email').val('');
                        checkValid();
                    } else if (msg == 2) {
                        $('#user-alert').show();
                        $('#username').val('');
                        checkValid();
                    } else if (msg == 3) {
                        $('#user-alert').show();
                        $('#username').val('');
                        $('#email-alert').show();
                        $('#email').val('');
                        checkValid();
                    } else if (msg == 4) {
                        $('#key').val('');
                        checkValid();
                        alert("Код приглашения не был найден!\nПроверьте корректность ввода или обратитесь к администратору")
                    }
                });
            }
        }
    });
});