 $(document).ready(function() {
 	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
 	var returnTable = $('#returnTable');
 	var noreturnbtn = $('#noreturnbtn');
 	console.log(id);
 	$.get('ReservationServlet',{'id':id },function(data){
		console.log(data.flightsReturn);
		for (it in data.flightsReturn) {
			returnTable.append(
						'<tr align="center">' + 
							'<td>' + data.flightsReturn[it].flightNumber + '</td>' + 
							'<td>' + data.flightsReturn[it].arrivalAirport.name + '</td>' + 
							'<td>' + data.flightsReturn[it].departureDate + '</td>' +
							'<td><button type="submit" id="submit" value="' + data.flightsReturn[it].id + '">Add return</button>' + '</td>' +
						'</tr>'
						);
		    }
		$('#submit').on('click',function(event){
			var id2=document.getElementById("submit").value
			var params={
				'id':id,
				'id2':id2,
				'status':"yes"
			};
			console.log(params);
			$.get('TryServlet',params,function(data){
				console.log("uspelo");
				window.location.replace('checkticket1.html')
			});
			event.preventDefault();
			return false;
		});
		$('#noreturnbtn').on('click',function(event){
			var params={
				'id':id,
				'status':"no"
			};
			console.log(params);
			$.get('ReservationServlet',params,function(data){
				window.location.replace('checkticket.html?id=' + id)
				console.log("uspelo");
			});
			event.preventDefault();
			return false;
		});
	});

});

function btnCancel() {
	window.location.replace('homepage.html');
}