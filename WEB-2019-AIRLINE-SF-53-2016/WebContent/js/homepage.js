
$(document).ready(function() {
	var airlineTable = $('#airlineTable');
	var departureAirportList = document.getElementById("departureAirportList");
	var arrivalAirportList = document.getElementById("arrivalAirportList");
	var userNameInput = $("input[name='userName']");
	var passwordInput = $("input[name='password']");
	var messageParagraph = $('#message');
	var addFlight = $('#addFlight');
	var topnav = $('.topnav');
	var flightNumber = $('#flightNumber');
	var ascdes = $('#ascdes');
	var orderby = $('#orderby');
	var data1 = $('#data1');
	var data2 = $('#data2');
	var data3 = $('#data3');
	var data4 = $('#data4');
	var price1 = $('#price1');
	var price2 = $('#price2');
	var submit = $('#searchAirport');
	var messageSearch = $('#message');
	$.get('GetFlightsServlet',{'status':status},function(data){
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

	submit.on('click',function(event){
		if (price1.val()=="") {
			var ticketPrice1 = 0.1;
		}else {
			ticketPrice1 = price1.val();
		}
		if (price2.val()=="") {
			var ticketPrice2 = 100000.01;
		}else {
			ticketPrice2 = price1.val();
		}
		if (flightNumber.val()=="") {
			var flightNumberValue = "";
		}else {
			flightNumberValue = flightNumber.val();
		}
		if (data1.val()=="") {
			var str2 = "2010-06-01T08:30";
		}else {
			str2 = data1.val();
		}
		if (data2.val()=="") {
			var str3 = "2099-06-01T08:30";
		}else {
			str3 = data2.val();
		}
		if (data3.val()=="") {
			var str4 = "2010-06-01T08:30";
		}else {
			str4 = data3.val();
		}
		if (data4.val()=="") {
			var str5 = "2099-06-01T08:30";
		}else {
			str5 = data4.val();
		}
  		var res10 = str2.slice(14, 16);
  		var res20 = str2.slice(11, 13);
  		var res30 = str2.slice(8, 10);
  		var res40 = str2.slice(5, 7);
  		var res50 = str2.slice(0, 4);
  		var departureDateValue1 = res50 + "-" + res40 + "-" + res30 + " " + res20 + ":" + res10 + ":00";
  		var res13 = str3.slice(14, 16);
  		var res23 = str3.slice(11, 13);
  		var res33 = str3.slice(8, 10);
  		var res43 = str3.slice(5, 7);
  		var res53 = str3.slice(0, 4);
  		var departureDateValue2 = res53 + "-" + res43 + "-" + res33 + " " + res23 + ":" + res13 + ":00";
  		var res14 = str4.slice(14, 16);
  		var res24 = str4.slice(11, 13);
  		var res34 = str4.slice(8, 10);
  		var res44 = str4.slice(5, 7);
  		var res54 = str4.slice(0, 4);
  		var arrivalDateValue1 = res54 + "-" + res44 + "-" + res34 + " " + res24 + ":" + res14 + ":00";
  		var res15 = str5.slice(14, 16);
  		var res25 = str5.slice(11, 13);
  		var res35 = str5.slice(8, 10);
  		var res45 = str5.slice(5, 7);
  		var res55 = str5.slice(0, 4);
  		var arrivalDateValue2 = res55 + "-" + res45 + "-" + res35 + " " + res25 + ":" + res15 + ":00";
		var departureDate1=departureDateValue1;
		var departureDate2=departureDateValue2;
		var arrivalDate1=arrivalDateValue1;
		var arrivalDate2=arrivalDateValue2;
		var departureAirport = document.getElementById("departureAirportList").selectedIndex + 1;
		var arrivalAirport = document.getElementById("arrivalAirportList").selectedIndex + 1;
		var orderbyValue = document.getElementById("orderby").value;
		var ascdesValue = document.getElementById("ascdes").value;
		if (departureAirport == arrivalAirport) {
			messageSearch.text("Same airport");
			return false;
		}
		var params={
				'flightNumber':flightNumberValue,
				'departureAirport':departureAirport,
				'departureDate1':departureDate1,
				'departureDate2':departureDate2,
				'arrivalAirport':arrivalAirport,
				'arrivalDate1':arrivalDate1,
				'arrivalDate2':arrivalDate2,
				'ticketPrice1':ticketPrice1,
				'ticketPrice2':ticketPrice2,
				'orderbyValue':orderbyValue,
				'ascdesValue':ascdesValue,
				'status':'search'
			};
		$.get('GetFlightsServlet',params,function(data){
			console.log(params);
			if("success"=="success"){
				var x = document.getElementById("airlineTable").rows.length;
				for (var i = x - 1; i >= 1; i--) {
					document.getElementById("airlineTable").deleteRow(i);
				}
				for (it in data.flightSearch) {
				airlineTable.append(
							'<tr align="center">' + 
								'<td><a href="flightpage.html?id='+data.flightSearch[it].id+'">' + data.flightSearch[it].flightNumber + '</td>' + 
								'<td>' + data.flightSearch[it].departureDate + '</td>' + 
								'<td>' + data.flightSearch[it].arrivalDate + '</td>' + 
								'<td>' + data.flightSearch[it].departureAirport.name + '</td>' + 
								'<td>' + data.flightSearch[it].arrivalAirport.name + '</td>' + 
								'<td>' + data.flightSearch[it].seatNumber + '</td>' + 
								'<td>' + data.flightSearch[it].ticketPrice + '</td>' +
							'</tr>'
						);
		    	}
			}else {
					messageSearch.text("There was a mistake");
				}

		});
		event.preventDefault();
		return false;
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


