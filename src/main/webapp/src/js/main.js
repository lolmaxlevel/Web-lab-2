//checks if string is an int number
function isInt(value) {
    return !isNaN(value) &&
        parseInt(Number(value)) == value && !isNaN(parseInt(value, 10));
}
//checks if string is a float number
function isFloat(str) {
    if (typeof str != "string") return false
    return !isNaN(str) && !isNaN(parseFloat(str))
}

//validate functions
function validateY() {
    let Y_MAX = 5;
    let Y_MIN = -5;
    let y_val = document.getElementById('y-text-input').value;
    let float_y_val = parseFloat(Number(y_val));
    if (isFloat(y_val) && float_y_val <= Y_MAX && float_y_val >= Y_MIN){
        $( ".y-error-box" ).hide();
        return true
    }else {
        $( ".y-error-box" ).show();
    }
}

function validateR() {
    let R_MAX = 3;
    let R_MIN = 1;
    let r_val = document.getElementById('r-select').value;
    let int_r_val = parseInt(Number(r_val));

    if (int_r_val <= R_MAX && int_r_val >= R_MIN) {
        $( ".r-error-box" ).hide();
        return true
    } else {
        $( ".r-error-box" ).show();
    }
}
function validateX(){
    let X_MAX = 2;
    let X_MIN = -2;
    let x_val;
    $('input[name="xval"]:checked').each(function () {
        x_val = $(this).val();
    })
    let int_x_val = parseInt(Number(x_val));

    if (isFloat(x_val) && int_x_val <= X_MAX && int_x_val >= X_MIN) {
        $( ".x-error-box" ).hide();
        return true
    } else {
        $( ".x-error-box" ).show();
    }
}

function validateForm() {
    return validateY() & validateR() & validateX();
}

//sends request when button clicked
$('#input-form').on('submit', function(event) {
    event.preventDefault();
    if (!validateForm()) return;
    let data = $(this).serialize() + '&timezone=' + new Date().getTimezoneOffset();
    window.location.assign("control?" + data);
}).on('reset', function (event){
    window.location.assign("control?reset=true")
});

function formatParams(params) {
    return "?" + Object
        .keys(params)
        .map(function (key) {
            return key + "=" + encodeURIComponent(params[key])
        })
        .join("&")
}
//send request if clicked on graph
$('#graph').on('click', function(e) {
    let pos = findPos(this);
    let graph_x = e.pageX - pos.x;
    let graph_y = e.pageY - pos.y;
    if (!validateR()) return;
    let r_val = document.getElementById('r-select').value
    let normalized_x = (graph_x-(x/2)) / (x/2) * r_val;
    let normalized_y = -(graph_y-(y/2)) / (y/2) * r_val;
    let data= {
        'xval':normalized_x,
        'yval':normalized_y,
        'rval':r_val,
        'timezone': new Date().getTimezoneOffset(),
    }
    window.location.assign("control" + formatParams(data));
});

//prevent checking more than one x value
$('input[type="checkbox"]').on('change', function() {
    $('input[type="checkbox"]').not(this).prop('checked', false);
});