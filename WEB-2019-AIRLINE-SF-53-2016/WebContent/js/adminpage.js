 $(document).ready(function() {
 	var userName = window.location.search.slice(1).split('&')[0].split('=')[1];
 	var userTable = $('#userTable');

 	$.get('AdminServlet',{'userName':userName},function(data){
		console.log(data.users);
		for (it in data.users) {
			userTable.append(
						'<tr align="center">' + 
							'<td><a href="userpage.html?userName='+data.users[it].userName+'">' + data.users[it].userName + '</td>' + 
							'<td>' + data.users[it].registrationDate + '</td>' + 
							'<td>' + data.users[it].role + '</td>' +
							'<td><button id="delete" value="'+data.users[it].userName+'">Delete</button>' + '</td>' +
						'</tr>'
						);
		    }
		$('button').on('click',function(event){
			var userName=$(this).val();
			console.log(userName);
			var x=confirm("Are you shure?");
			if(x){
			$.post('AdminServlet',{'userName':userName,'status':"delete",},function(data){
					var id="#"+userName;
					if(usrName == data.logged.userName){
						$(id).remove();
						$.get('LogOutServlet',{},function(){
							
						});
					}
					$(id).remove();
			});
			}
			event.preventDefault();
			return false;
		});
	});

});

