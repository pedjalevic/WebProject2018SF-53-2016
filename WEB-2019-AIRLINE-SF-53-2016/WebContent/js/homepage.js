/**
 * 
 */

$(document).ready(function() {
	var airlineTable = $('#airlineTable');
	var departureAirportList = document.getElementById("departureAirportList");
	var arrivalAirportList = document.getElementById("arrivalAirportList");
	var userNameInput = $("input[name='userName']");
	var passwordInput = $("input[name='password']");
	var messageParagraph = $('#message');
	var addFlight = $('#addFlight');
	var topnav = $('.topnav');
	$.get('GetFlightsServlet',{},function(data){
		console.log(JSON.stringify(data));
		for (ai in data.airports) {
			var option = document.createElement("option");
			option.text = data.airports[ai].name;
			departureAirportList.add(option);
		    }
		for (ai in data.airports) {
			var option = document.createElement("option");
			option.text = data.airports[ai].name;
			arrivalAirportList.add(option);
		    }
		for (it in data.flights) {
			airlineTable.append(
						'<tr align="center">' + 
							'<td><a href="flightpage.html?id='+data.flights[it].id+'">' + data.flights[it].flightNumber + '</td>' + 
							'<td>' + data.flights[it].departureDate + '</td>' + 
							'<td>' + data.flights[it].arrivalDate + '</td>' + 
							'<td>' + data.flights[it].departureAirport.name + '</td>' + 
							'<td>' + data.flights[it].arrivalAirport.name + '</td>' + 
							'<td>' + data.flights[it].seatNumber + '</td>' + 
							'<td>' + data.flights[it].ticketPrice + '</td>' +
						'</tr>'
						);
		    }
		    if(data.user !=null){
				$('#signInLink').replaceWith('<a href="LogOutServlet">Sign out</a>');
				if (data.user.role == "ADMIN") {
					$('#home').replaceWith('<a href="adminpage.html?userName='+data.user.userName+'">Admin page</a>');
					$('#addFlight').replaceWith('<a href="addflight.html" id="addFlight" >Add Flight</a>');
				}else if (data.user.role == "USER") {
					$('#home').replaceWith('<a href="userpage.html?userName='+data.user.userName+'">My profile</a>');
				}
		}

		});
	$('#signIn').on('click', function(event) { 
		var userName = userNameInput.val();
		var password = passwordInput.val();
		if(userName=="" || password =="")
			messageParagraph.text("Empty fields");
			
		userNameInput.on('focus',function(event){
			messageParagraph.text("");
			event.preventDefault();
			return false;
		});
		passwordInput.on('focus',function(event){
			messageParagraph.text("");
			event.preventDefault();
			return false;
		});
		console.log('userName: ' + userName);
		console.log('password: ' + password);

		
		$.post('LoginServlet', {'userName': userName, 'password': password}, function(data) { 
			console.log('stigao odgovor');
			console.log(data);

			if (data.status == 'success') {
				console.log('Uspelo');
				window.location.replace('userpage.html?userName='+userName);
			}
			if (data.status == 'failure') {
			messageParagraph.text("Wrong username or password");
			}
		});
		
		console.log('poslat zahtev');

		event.preventDefault();
		return false;
	});

});




function openNav() {
    document.getElementById("mySidelogin").style.width = "280px";
}
function closeNav() {
    document.getElementById("mySidelogin").style.width = "0";
}


