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
		console.log(data.flight);
		console.log(data.user);
		flightNumber.text(data.flight.flightNumber);
		departureDate.text(data.flight.departureDate);
		arrivalDate.text(data.flight.arrivalDate);
		departureAirport.text(data.flight.departureAirport.name);
		arrivalAirport.text(data.flight.arrivalAirport.name);
		seatNumber.text(data.flight.seatNumber);
		freeSeat.text(data.flight.freeSeat);
		ticketPrice.text(data.flight.ticketPrice);

		if(data.user !=null){
				if (data.user.role == "ADMIN")
					$('#buttonReservation').replaceWith('<a type="button" href="editflight.html?id='+data.flight.id+'" id="buttonEdit">Edit1</a>');
					$('#buttonBuy').replaceWith('<input type="button" id="buttonDelete" value="Delete">');
				}
		else {
				$('#buttonReservation').replaceWith('<input style="display: none;" type="button" id="button1" value="reservation">');
				$('#buttonBuy').replaceWith('<input style="display: none;" type="button" id="button2" value="buy">');
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
	});
});