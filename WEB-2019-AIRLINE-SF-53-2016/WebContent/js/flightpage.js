$(document).ready(function(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	var flightNumber=$('#flightNumber');
	var departureDate=$('#departureDate');
	var arrivalDate=$('#arrivalDate');
	var departureAirport=$('#departureAirport');
	var arrivalAirport=$('#arrivalAirport');
	var seatNumber=$('#seatNumber');
	var freeSeat=$('#freeSeat');
	var ticketPrice=$('#ticketPrice');

	$.get('FlightServlet',{'id':id},function(data){
		console.log(data.dataFlight);
		flightNumber.text(data.flight.flightNumber);
		departureDate.text(data.flight.departureDate);
		arrivalDate.text(data.flight.arrivalDate);
		departureAirport.text(data.flight.departureAirport.name);
		arrivalAirport.text(data.flight.arrivalAirport.name);
		seatNumber.text(data.flight.seatNumber);
		freeSeat.text(data.flight.freeSeat);
		ticketPrice.text(data.flight.ticketPrice);

		if(data.user !=null){
				if (data.user.role == "ADMIN"){
					$('#buttonReservation').replaceWith('<a type="button" class="btn btn-info" href="editflight.html?id='+data.flight.id+'" id="buttonEdit">Edit</a>');
					$('#buttonBuy').replaceWith('<input class="btn btn-info" type="button" id="buttonDelete" value="Delete">');
					}
				if (data.user.role == "USER"){
					$('#buttonReservation').replaceWith('<a type="button" class="btn btn-info" href="reservationpage.html?id='+data.flight.id+'" id="button12">Reservation</a>');
					$('#buttonBuy').replaceWith('<a type="button" class="btn btn-info" href="buypage.html?id='+data.flight.id+'" id="button2">Buy</a>');
					}
				if (data.dataFlight == true) {
					$('#button1').replaceWith('<input style="display: none;" type="button" id="button1" value="reservation">');
					$('#button2').replaceWith('<input style="display: none;" type="button" id="button2" value="buy">');
				}
			}


		$('#buttonDelete').on('click',function(event){

			console.log(id);
			var x=confirm("Are you shure?");
			if(x){
				$.post('FlightServlet',{'flightId':id,'status':"delete"},function(data){
					window.location.replace('homepage.html');
							
						});
					}
			event.preventDefault();
			return false;
		});
		$('#button1').on('click',function(event){

			console.log(id);
			$.get('ReservationServlet',{'id':id},function(data){
				console.log("reservation");
				window.location.replace('homepage.html');	
				});
			event.preventDefault();
			return false;
		});
	});
});