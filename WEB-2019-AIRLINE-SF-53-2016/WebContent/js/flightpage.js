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
		flightNumber.text(data.flight.flightNumber);
		departureDate.text(data.flight.departureDate);
		arrivalDate.text(data.flight.arrivalDate);
		departureAirport.text(data.flight.departureAirport.name);
		arrivalAirport.text(data.flight.arrivalAirport.name);
		seatNumber.text(data.flight.seatNumber);
		freeSeat.text(data.flight.freeSeat);
		ticketPrice.text(data.flight.ticketPrice);
	});
});