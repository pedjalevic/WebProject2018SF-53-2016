$(document).ready(function(){
	var userNameInput = $("input[name='userName']");
	var passwordInput = $("input[name='password']");
	var messageParagraph  = $('#message');
	var submit = $('#submit');
	
	submit.on('click',function(event){
		var userName=userNameInput.val();
		var password=passwordInput.val();
		if(userName == "" || password == ""){
			messageParagraph.text("Username and password are required");
			return false;
		}
		var params={
				'userName':userName,
				'password':password,
			};
		
		$.post('RegisterServlet',params,function(data){
			if(data.status=="success"){
				alert("You add user");
				window.location.replace('homepage.html');
			}else {
					messageParagraph.text("Username already exists or other error");
				}
		});
		event.preventDefault();
		return false;
	});
	
});

function btnCancel() {
	window.location.replace('homepage.html');
}
