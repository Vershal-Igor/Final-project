function CustomValidation(input) {
    this.invalidities = [];
    this.validityChecks = [];

    //add reference to the input node
    this.inputNode = input;

    //trigger method to attach the listener
    this.registerListener();
}

CustomValidation.prototype = {
    addInvalidity: function (message) {
        this.invalidities.push(message);
    },
    getInvalidities: function () {
        return this.invalidities.join('. \n');
    },
    checkValidity: function (input) {
        for (var i = 0; i < this.validityChecks.length; i++) {

            var isInvalid = this.validityChecks[i].isInvalid(input);
            if (isInvalid) {
                this.addInvalidity(this.validityChecks[i].invalidityMessage);
            }

            var requirementElement = this.validityChecks[i].element;

            if (requirementElement) {
                if (isInvalid) {
                    requirementElement.classList.add('invalid');
                    requirementElement.classList.remove('valid');
                } else {
                    requirementElement.classList.remove('invalid');
                    requirementElement.classList.add('valid');
                }

            } // end if requirementElement
        } // end for
    },
    checkInput: function () { // checkInput now encapsulated

        this.inputNode.CustomValidation.invalidities = [];
        this.checkValidity(this.inputNode);

        if (this.inputNode.CustomValidation.invalidities.length === 0 && this.inputNode.value !== '') {
            this.inputNode.setCustomValidity('');
        } else {
            var message = this.inputNode.CustomValidation.getInvalidities();
            this.inputNode.setCustomValidity(message);
        }
    },
    registerListener: function () { //register the listener here

        var CustomValidation = this;

        this.inputNode.addEventListener('keyup', function () {
            CustomValidation.checkInput();
        });


    }

};

var nameValidityChecks = [
    {

        isInvalid: function (input) {
            return input.value.length < 3 | input.value.length > 24;
        },
        invalidityMessage: 'This input needs to be between 3 and 24 characters',
    },

];

var surnameValidityChecks = [
    {

        isInvalid: function (input) {
            return input.value.length < 3 | input.value.length > 24;
        },
        invalidityMessage: 'This input needs to be between 3 and 24 characters',
    },

];

var loginValidityChecks = [
    {

        isInvalid: function (input) {
            return input.value.length < 5 | input.value.length > 24;
        },
        invalidityMessage: 'This input needs to be between 5 and 24 characters',
    },

    {
        isInvalid: function (input) {
            return !input.value.match(/[а-яА-Яa-zA-Z]/g);
        },
        invalidityMessage: 'At least 1 letter is required',
    }


];

var passwordValidityChecks = [
    {
        isInvalid: function (input) {
            return input.value.length < 5 | input.value.length > 24;
        },
        invalidityMessage: 'This input needs to be between 5 and 24 characters',
    },
    {
        isInvalid: function (input) {
            return !input.value.match(/[0-9]/g);
        },
        invalidityMessage: 'At least 1 number is required',
    },
    {
        isInvalid: function (input) {
            return !input.value.match(/[a-zA-Z]/g);
        },
        invalidityMessage: 'At least 1 letter is required(only latin character)',
    },
    {
        isInvalid: function (input) {
            return input.value.match(/[а-яА-Я]/);
        },
        invalidityMessage: 'Only latin character',
    }
];

var nameInput = document.getElementById('inputsignup1');
var surnameInput = document.getElementById('inputsignup2');
var loginInput = document.getElementById('inputsignup3');
var passwordInput = document.getElementById('inputsignup4');

nameInput.CustomValidation = new CustomValidation(nameInput);
nameInput.CustomValidation.validityChecks = nameValidityChecks;

surnameInput.CustomValidation = new CustomValidation(surnameInput);
surnameInput.CustomValidation.validityChecks = surnameValidityChecks;

loginInput.CustomValidation = new CustomValidation(loginInput);
loginInput.CustomValidation.validityChecks = loginValidityChecks;

passwordInput.CustomValidation = new CustomValidation(passwordInput);
passwordInput.CustomValidation.validityChecks = passwordValidityChecks;


var inputs = document.querySelectorAll('input:not([type="submit"])');


var submit = document.querySelector('input[type="submit"');
var form = document.getElementById('signUp');

function validate() {
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].CustomValidation.checkInput();
    }
}

submit.addEventListener('click', validate);
form.addEventListener('submit', validate);


