$(document).ready(function() {	
$(".check_all").click(function() {
	if ($(".check_all").is(":checked")) {
		$("[name = check]:checkbox").click().attr("checked", true);
	} else {
		$("[name = check]:checkbox").click().attr("checked", false);
	}
});
});