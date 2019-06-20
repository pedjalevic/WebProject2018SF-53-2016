
$(document).ready(function(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	var departureAirportInput = $("#departureAirport");
	var departureDateInput  = $("input[name='departureDate']");
	var arrivalAirportInput = $('#arrivalAirport');
	var arrivalDateInput = $("input[name='arrivalDate']");
	var ticketPriceInput = $("input[name='ticketPrice']");
	var create = $('#create');
	var seatNumberInput = $("input[name='seatNumber']");
	var submit=$('#submit');
	var messageParagraph=$('#message');

	$.get('FlightServlet',{'id':id},function(data){
		var str = data.flight.departureDate;
  		var res1 = str.slice(15, 19);
  		var res2 = str.slice(12, 14);
  		var res3 = str.slice(9, 11);
  		var res4 = str.slice(6, 8);
  		var res5 = str.slice(3, 5);
  		var res = res1 + "-"  + res2 + "-" + res3 + "T" + res4 + ":" + res5;
  		var str1 = data.flight.arrivalDate;
  		var res12 = str1.slice(15, 19);
  		var res23 = str1.slice(12, 14);
  		var res34 = str1.slice(9, 11);
  		var res45 = str1.slice(6, 8);
  		var res56 = str1.slice(3, 5);
  		var res1 = res12 + "-"  + res23 + "-" + res34 + "T" + res45 + ":" + res56;
		document.getElementById("departureDate").value = res;
		document.getElementById("arrivalDate").value = res1;
		seatNumberInput.val(data.flight.seatNumber);
		ticketPriceInput.val(data.flight.ticketPrice);
		document.getElementById("departureAirport").selectedIndex = data.flight.departureAirport.id - 1;
		document.getElementById("arrivalAirport").selectedIndex = data.flight.arrivalAirport.id - 1;
	});
	
	submit.on('click',function(event){
		var departureAirportValue = document.getElementById("departureAirport").selectedIndex + 1;
		var arrivalAirportValue = document.getElementById("arrivalAirport").selectedIndex + 1;
		var ticketPriceValue = ticketPriceInput.val();
		var seatNumberValue = seatNumberInput.val();
		var str2 = arrivalDateInput.val();
  		var res10 = str2.slice(14, 16);
  		var res20 = str2.slice(11, 13);
  		var res30 = str2.slice(8, 10);
  		var res40 = str2.slice(5, 7);
  		var res50 = str2.slice(0, 4);
  		var arrivalDateValue = res50 + "-" + res40 + "-" + res30 + " " + res20 + ":" + res10 + ":00";

		var params={
				'id':id,
				'departureAirport':departureAirportValue,
				'arrivalAirport':arrivalAirportValue,
				'arrivalDate':arrivalDateValue,
				'ticketPrice':ticketPriceValue,
				'seatNumber':seatNumberValue,
				'status':'edit',
			};
		
		$.post('FlightServlet',params,function(data){
			if(data.status=="success"){
				var location="homepage.html";
				window.location.replace(location);
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