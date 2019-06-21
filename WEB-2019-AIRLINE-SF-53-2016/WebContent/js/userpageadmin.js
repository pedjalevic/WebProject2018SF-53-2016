
$(document).ready(function(){
	var userName = window.location.search.slice(1).split('&')[0].split('=')[1];
	var name=$('#name');
	var password=$('#password');
	var role=$('#role');
	var blocked=$('#blocked');submit
	var submit=$('#submit');
	var messageParagraph=$('#message');

	$.get('UserServlet',{'userName':userName},function(data){
		console.log(data.user);
		name.val(data.owner.userName);
		password.val(data.owner.userPassword);
		if(data.owner.role == "USER"){
			role.val('user');
		}
		else{
			role.val('admin');
		}
		if(data.owner.blocked == true){
			blocked.prop('checked',true);
		}
		else{
			blocked.prop('checked',false);
		}
	});
	
	submit.on('click',function(event){
		var nameValue=name.val();
		var passwordValue=password.val();
		var roleValue = role.val();
		var blockedValue=false;
		if(blocked.is(':checked')){
			blockedValue=true;
		}

		var params={
				'userName':userName,
				'name':nameValue,
				'password':passwordValue,
				'role':roleValue,
				'blocked':blockedValue,
				'status':'edit'
			};
		
		$.post('UserServlet',params,function(data){
			if(data.status=="success"){
				var location="homepage.html";
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