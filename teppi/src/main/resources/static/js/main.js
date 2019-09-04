$(document).ready(function() {
	genderYmd();
	$("#search-form").submit(function(event) {

		// stop submit the form, we will post it manually.
		event.preventDefault();

		submit_registraion();
	});
});

function submit_registraion() {
	
	// Disable (Gray out) Register form 
	formDisabled();
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/registration",
		data : JSON.stringify(getRegistrationValue()),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			// If the data saved properly, display login button 
			$("#btnLogin").removeClass('hidden');
			clearError();

		},
		error : function(e) {
			// If there is any error, enable the form and display the error message on top of the form (Register label)
			formEndabled();
			showError(e.responseJSON.msg);
		}
	});
}

function getRegistrationValue() {
	var values = {}
	values["phoneNumber"] = $("#phoneNumber").val();
	values["firstName"] = $("#firstName").val();
	values["lastName"] = $("#lastName").val();
	if ($('input[name=gender]:checked')) {
		values["gender"] = $('input[name=gender]:checked').val();
	}
	if (getDateOfBirth() !== "") {
		values["dateOfBirth"] = $("#year").val() + "/"
				+ $("#month").val() + "/" + $("#day").val();
	}

	values["email"] = $("#email").val();
	return values;
}

function getDateOfBirth() {
	let year = $("#year").val();
	let month = $("#month").val();
	let date = $("#day").val()
	if (year === "" || month === "" || date === "") {
		return "";
	}
	return $("#year").val() + "/" + $("#month").val() + "/" + $("#day").val();
}

function formDisabled() {
	$("#pageContainer").addClass("disabledbutton");
}

function formEndabled() {
	$("#pageContainer").removeClass("disabledbutton");
}

function showError(errors) {

	$('#feedback').attr('data-content', errors);
	$('#feedback').popover('show');
}

function clearError() {
	$('#feedback').popover('hide');
}

function genderYmd() {
	const monthNames = [ "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December" ];
	var qntYears = 100;
	var selectYear = $("#year");
	var selectMonth = $("#month");
	var selectDay = $("#day");
	var currentYear = new Date().getFullYear();

	for (var y = 0; y <= qntYears; y++) {
		let date = new Date(currentYear);
		var yearElem = document.createElement("option");

		yearElem.value = currentYear
		yearElem.textContent = currentYear;
		selectYear.append(yearElem);
		currentYear--;
	}

	for (var m = 0; m <= 12; m++) {
		let monthNum = new Date(2019, m).getMonth()
		let month = monthNames[monthNum];
		var monthElem = document.createElement("option");
		monthElem.value = monthNum;
		monthElem.textContent = month;
		selectMonth.append(monthElem);
	}

	selectYear.on("change", AdjustDays);
	selectMonth.on("change", AdjustDays);

	AdjustDays();

	function AdjustDays() {
		var year = selectYear.val();

		var month = parseInt(selectMonth.val()) + 1;

		selectDay.empty();

		var dayElem = document.createElement("option");
		dayElem.value = "";
		dayElem.textContent = "Date";
		selectDay.append(dayElem);

		// get the last day, so the number of days in that month
		var days = new Date(year, month, 0).getDate();

		// lets create the days of that month
		for (var d = 1; d <= days; d++) {
			var dayElem = document.createElement("option");
			dayElem.value = d;
			dayElem.textContent = d;
			selectDay.append(dayElem);
		}
	}

	function adjustValueDate(date) {
		if (date < 10) {
			return "0" + date;
		}
		return date;
	}
}