function checkValid(formValid){
    $('input').each(function() {
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

            $.ajax({
                type: "POST",
                url: '/reg_user',
                //processData: false, // указываем URL и
                dataType : "text",                // тип загружаемых данных
                data: {
                    family: family,
                    name: name,
                    father: father,
                    email: email,
                    username: username,
                    password: password
                }
            }).done(function( msg ) {
                alert(msg);
                if(msg == 0){
                    $('#user-alert').hide();
                    alert("Вы успешно зарегестрировались, далее вы попадете на главную страницу");
                    document.location.replace("/");
                }else if(msg == 1){
                    $('#user-alert').show();
                    $('#username').val('');
                    checkValid();
                }else if(msg == 2){
                    $('#email-alert').show();
                    $('#email').val('');
                    checkValid();
                }else if(msg == 3){
                    $('#user-alert').show();
                    $('#username').val('');
                    $('#email-alert').show();
                    $('#email').val('');
                    checkValid();
                }
            });
        }
    });
});