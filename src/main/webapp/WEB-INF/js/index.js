$( document ).ready(function() {

    var todayDate = new Date();

    var checkInPicker = $('#datetimepicker1').datetimepicker({
        pickTime: false,
        language: 'ru',
        minDate: todayDate
    });
    var checkOutPicker = $('#datetimepicker2').datetimepicker({
        pickTime: false,
        language: 'ru',
        minDate: todayDate
    });

    checkInPicker.on('dp.change', function(e) {
        checkOutPicker.data('DateTimePicker').setMinDate(e.date);
    });


    checkOutPicker.on('dp.change', function(e) {
        checkInPicker.data('DateTimePicker').setMaxDate(e.date);
    });

});