 $(document).ready(function() {
 	var userName = window.location.search.slice(1).split('&')[0].split('=')[1];
 	var reservationTicket = $('#reservationTicket');
 	var sellTicket = $('#sellTicket');
 	var username = $('#userName');
 	var registrationDate = $('#registrationDate');
 	var role = $('#role');
 	var reservationticket = $('#reservationTicket');
 	var sellticket = $('#sellTicket');
 	var editbutton = $('#editbutton');

 	$.get('UserServlet',{'userName':userName},function(data){
 		console.log(data.owner);
		username.text(data.owner.userName);
		registrationDate.text(data.owner.registrationDate);
		role.text(data.owner.role);
		if (data.user.blocked == true) {
			$('#editbutton').replaceWith('<a href="#" style="display: none;" class="btn btn-info" id="editbutton" role="button">Edit user</a>');
		}
		for (it in data.reservationTicket) {
			reservationticket.append(
						'<tr align="center">' + 
							'<td><a href="flightpage.html?id='+data.reservationTicket[it].id+'">' + data.reservationTicket[it].reservationDate + '</td>' + 
						'</tr>'
						);
		    }
		for (it in data.sellTicket) {
			sellticket.append(
						'<tr align="center">' + 
							'<td ><a href="flightpage.html?id='+data.sellTicket[it].id+'" width = 100%;>' + data.sellTicket[it].sellDate + '</td>' + 
						'</tr>'
						);
		    }
		editbutton.on('click',function(event){
			if (data.user.role == "ADMIN") {
		window.location.replace('edituseradmin.html?userName='+data.owner.userName);
				}else if (data.user.role == "USER") {
					window.location.replace('edituser.html?userName='+data.owner.userName);
				}
			});
	});

});

