$(document).ready(function () {
	var filterType = $( '#filterType' ),
		filterCategory = $( '#filterCategory' ),
		$options = filterCategory.find( 'option' );

	filterType.on('change', function() {
		var value = filterCategory.val(),
			selectedType = this.value;
		filterCategory.html( $options.filter(function () {
			var type = $(this).data('type');
			return type == '' || type == selectedType;
		}));

		filterCategory.val(value);

		if (this.value != '') {
			filterCategory.removeAttr('disabled');
		} else {
			filterCategory.attr('disabled', 'disabled');
		}
	});
	filterType.trigger( 'change' );
});

document.getElementById("resetBtn").onclick = function () {

    var myForm = document.getElementById("filtersForm");

    for (var i = 0; i < myForm.elements.length; i++) {
        myForm.elements[i].value = "";
    }

};