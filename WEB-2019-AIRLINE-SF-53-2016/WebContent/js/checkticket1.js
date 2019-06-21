$(document).ready(function(){
	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	var userNameInput = $("input[name='userName']");
	var priceInput = $("input[name='price']");
	var departureSeatInput = $("input[name='departureSeat']");
	var returnSeatInput = $("input[name='returnSeat']");
	var messageParagraph  = $('#message');
	var submit = $('#submit');
	$.get('TryServlet',{'id':id},function(data){
		console.log(JSON.stringify(data));
		var id = data.id;
		var id2 = data.id2;
		userNameInput.val(data.user.userName);
		priceInput.val(data.flight.ticketPrice);

		submit.on('click',function(event){
			var departureSeat=departureSeatInput.val();
			var returnSeat=returnSeatInput.val();
			if(departureSeat == "" ||returnSeat == ""){
				messageParagraph.text("Departure seat and Return seat are required");
				return false;
			}
			var params={
					'departureSeat':departureSeat,
					'returnSeat':returnSeat,
					'id':id,
					'id2':id2
				};
			console.log(params);
			$.post('TryServlet',params,function(data){
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
