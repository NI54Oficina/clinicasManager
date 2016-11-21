jQuery.validator.setDefaults({
    highlight: function(element) {
	    $(element).closest('.form-group').addClass('has-error');
    },
    unhighlight: function(element) {
	    $(element).closest('.form-group').removeClass('has-error');
    },
    errorElement: 'span',
    errorClass: 'help-block',
    errorPlacement: function(error, element) {
	    if (element.parent('.input-group').length) {
		    error.insertAfter(element.parent());
	    }
	    else {
		    error.insertAfter(element);
	    }
    }
});

jQuery.extend(jQuery.validator.messages, {
    required: "Campo requerido.",
    remote: "Please fix this field.",
    email: "Ingresar un mail válido.",
    url: "Please enter a valid URL.",
    date: "Please enter a valid date.",
    dateISO: "Please enter a valid date (ISO).",
    number: "Se requiere un número.",
    digits: "Se requiere sólo dígitos.",
    creditcard: "Please enter a valid credit card number.",
    equalTo: "Please enter the same value again.",
    accept: "Please enter a value with a valid extension.",
    maxlength: jQuery.validator.format("Please enter no more than {0} characters."),
    minlength: jQuery.validator.format("Please enter at least {0} characters."),
    rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
    range: jQuery.validator.format("Please enter a value between {0} and {1}."),
    max: jQuery.validator.format("Please enter a value less than or equal to {0}."),
    min: jQuery.validator.format("Please enter a value greater than or equal to {0}.")
});

jQuery.validator.addMethod("greaterThan", function(value, element, params) {
	
    if (!/Invalid|NaN/.test(parseDate(value))) {
        return parseDate(value) >= parseDate($(params).html());
    }

    return isNaN(value) && isNaN($(params).html()) || (Number(value) >= Number($(params).html()));
}, 'Debe ser igual o posterior a {0}.');

jQuery.validator.addMethod("emptyIf", function(value, element, params) {
	if ($("#traumatico").is(":checked") && !value) {
		return false;
	}
	
	return true;
}, 'Campo requerido.'); 

function parseDate(str) {
	var mdy = str.split('/');
	return new Date(mdy[2], mdy[1]-1, mdy[0]);
}