$(document).ready(function(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	var userNameInput = $("input[name='userName']");
	var priceInput = $("input[name='price']");
	var departureSeatInput = $("input[name='departureSeat']");
	var messageParagraph  = $('#message');
	var submit = $('#submit');
	$.get('ReservationServlet',{'id':id},function(data){
		console.log(JSON.stringify(data));
		var id = data.id;
		userNameInput.val(data.user.userName);
		priceInput.val(data.flight.ticketPrice);

		submit.on('click',function(event){
			var departureSeat=departureSeatInput.val();
			if(departureSeat == ""){
				messageParagraph.text("Departure Seat are required");
				return false;
			}
			var params={
					'departureSeat':departureSeat,
					'id':id,
					'status':"no"
				};
			
			$.post('ReservationServlet',params,function(data){
				if(data.status=="success"){
					window.location.replace('homepage.html');
				}else {
						messageParagraph.text("Seat is occupied");
					}
			});
			event.preventDefault();
			return false;
	});
	});
});

function btnCancel() {
	window.location.replace('homepage.html');
}
