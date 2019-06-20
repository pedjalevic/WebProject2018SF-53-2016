$(document).ready(function(){
	var flightNumberInput = $("input[name='flightNumber']");
	var departureAirportInput = $("input[name='departureAirport']");
	var departureDateInput  = $("input[name='departureDate']");
	var arrivalAirportInput = $("input[name='arrivalAirport']");
	var arrivalDateInput = $("input[name='arrivalDate']");
	var ticketPriceInput = $("input[name='ticketPrice']");
	var create = $('#create');
	var seatNumberInput = $('#seatNumber');
	var messageParagraph = $('#message');
	var submit = $('#create');
	
	submit.on('click',function(event){
  		var str2 = departureDateInput.val();
  		var res10 = str2.slice(14, 16);
  		var res20 = str2.slice(11, 13);
  		var res30 = str2.slice(8, 10);
  		var res40 = str2.slice(5, 7);
  		var res50 = str2.slice(0, 4);
  		var arrivalDateValue = res50 + "-" + res40 + "-" + res30 + " " + res20 + ":" + res10 + ":00";
  		var str2 = arrivalDateInput.val();
  		var res10 = str2.slice(14, 16);
  		var res20 = str2.slice(11, 13);
  		var res30 = str2.slice(8, 10);
  		var res40 = str2.slice(5, 7);
  		var res50 = str2.slice(0, 4);
  		var arrivalDateValue = res50 + "-" + res40 + "-" + res30 + " " + res20 + ":" + res10 + ":00";
		var departureDate=arrivalDateValue;
		var arrivalDate=arrivalDateValue;
		var flightNumber=flightNumberInput.val();
		var ticketPrice=ticketPriceInput.val();
		var seatNumber=seatNumberInput.val();
		var departureAirport = document.getElementById("departureAirport").selectedIndex + 1;
		var arrivalAirport = document.getElementById("arrivalAirport").selectedIndex + 1;
		var freeSeat=seatNumberInput.val();
		if (departureAirport == arrivalAirport) {
			messageParagraph.text("Same airport");
			return false;
		}
		if(departureDateInput.val()=="" || arrivalDateInput.val() =="" || departureAirport =="" || arrivalAirport =="" || arrivalDate =="" || flightNumber =="" || seatNumber =="") {
			messageParagraph.text("Required fields are not filled");
			return false;
		}
		var params={
				'flightNumber':flightNumber,
				'departureAirport':departureAirport,
				'departureDate':departureDate,
				'arrivalAirport':arrivalAirport,
				'arrivalDate':arrivalDate,
				'ticketPrice':ticketPrice,
				'seatNumber':seatNumber,
				'freeSeat':freeSeat,
				'status':'add'
			};
		
		$.post('FlightServlet',params,function(data){
			if(data.status=="success"){
				alert("You add flight");
				window.location.replace('homepage.html');
			}else {
					messageParagraph.text("There was a mistake");
				}
		});
		event.preventDefault();
		return false;
	});
	
});

function btnCancel() {
	window.location.replace('homepage.html');
}
