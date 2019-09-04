$(document).ready(function() {
	genderYmd();
	$("#search-form").submit(function(event) {

		// stop submit the form, we will post it manually.
		event.preventDefault();

		submit_registraion();
	});
});

function myBeforeSubmitFunction(a, b, node) {

	console.log(a, b);

	node.find('input:not([type="submit"]), select, textarea').attr('readonly',
			'true');
	node.append('<div class="ui active loader"></div>');

}

function submit_registraion() {
	var registration = {}
	registration["phoneNumber"] = $("#phoneNumber").val();
	registration["firstName"] = $("#firstName").val();
	registration["lastName"] = $("#lastName").val();
	if ($('input[name=gender]:checked')) {
		registration["gender"] = $('input[name=gender]:checked').val();
	}
	if (getDateOfBirth() !== "") {
		registration["dateOfBirth"] = $("#year").val() + "/"
				+ $("#month").val() + "/" + $("#day").val();
	}

	registration["email"] = $("#email").val();

	// clearError();
	formDisabled();

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/registration",
		data : JSON.stringify(registration),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			console.log("SUCCESS : ", data);
			$("#btnLogin").removeClass('hidden');
			clearError();

		},
		error : function(e) {
			formEndabled();
			var json = "<h4>Ajax Response</h4><pre>" + e.responseText
					+ "</pre>";
			showError(e.responseJSON.msg);
			console.log("ERROR : ", e);
		}
	});
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