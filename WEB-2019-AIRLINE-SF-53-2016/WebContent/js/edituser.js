
$(document).ready(function(){
	var userName = window.location.search.slice(1).split('&')[0].split('=')[1];
	var name=$('#name');
	var password=$('#password');
	var submit=$('#submit');
	var messageParagraph=$('#message');


	$.get('UserServlet',{'userName':userName},function(data){
		name.val(data.owner.userName);
		password.val(data.owner.userPassword);
		console.log(data.owner);
	});
	
	submit.on('click',function(event){
		var nameValue=name.val();
		var passwordValue=password.val();

		var params={
				'userName':userName,
				'name':nameValue,
				'password':passwordValue,
				'status':'edit',
				'role':'user'

			};
		
		$.post('UserServlet',params,function(data){
			if(data.status=="success"){
				var location="LogOutServlet";
				window.location.replace(location);
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