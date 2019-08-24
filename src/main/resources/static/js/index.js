$(document).ready(function(){
    $(".hello").html(" - Пример работы JavaScript");
});

function checklogin(formValid){
    $('input').each(function() {
        if (!this.checkValidity()) {
            formValid = false;
        }
    });
    return formValid;
}

$(function() {
    $('#button').click(function() {
        var formValid = true;
        formValid = checklogin(formValid);
        if(!formValid){
            alert("Пожалуйста, заполните формы");
        }
        if (formValid) {
            var email = $("#email").val();
            var password = $("#password").val();

            $.ajax({
                type: "POST",
                url: '/login_user',
                dataType : "text",
                data: {
                    email: email,
                    password: password
                }
            }).done(function( msg ) {
                if(msg == 0){
                    alert("Вы успешно авторизовались!");
                    document.location.replace("/");
                }else if (msg == 1){
                    alert("Такого пользователя не сущетсвует, проверьте правильность информации");
                }

            });
        }
    });
});