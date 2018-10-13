/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
var form1 = document.getElementById('form1');
	form2 = document.getElementById('form2');
    assets = document.getElementById('assets');
    liabs = document.getElementById('liabs');

form1.onsubmit = function() {
   var variable = assets.value;
   alert( variable );
}; 

form2.onsubmit = function() {
   var variable = liabs.value;
   alert( variable );
}; 

function myFunction() {
	document.getElementById("myDropdown").classList.toggle("show");
}

//Closes the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
	if (!event.target.matches('.dropbtn')) {
		var dropdowns = document.getElementsByClassName("dropdown-content");
		var i;
		for (i = 0;i < dropdowns.length; i++) {
			var openDropdown = dropdowns[i];
			if (openDropdown.classList.contains('show')) {
        		openDropdown.classList.remove('show');
        	}
		}
	}
}

