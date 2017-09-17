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


/* ----------------------------
 Validity Checks

 The arrays of validity checks for each input
 Comprised of three things
 1. isInvalid() - the function to determine if the input fulfills a particular requirement
 2. invalidityMessage - the error message to display if the field is invalid
 3. element - The element that states the requirement
 ---------------------------- */

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

/* ----------------------------

 Setup CustomValidation

 Setup the CustomValidation prototype for each input
 Also sets which array of validity checks to use for that input

 ---------------------------- */

var loginInput = document.getElementById('login');
var passwordInput = document.getElementById('password');

loginInput.CustomValidation = new CustomValidation(loginInput);
loginInput.CustomValidation.validityChecks = loginValidityChecks;

passwordInput.CustomValidation = new CustomValidation(passwordInput);
passwordInput.CustomValidation.validityChecks = passwordValidityChecks;


var inputs = document.querySelectorAll('input:not([type="submit"])');


var submit = document.querySelector('input[type="submit"');
var form = document.getElementById('signIn');

function validate() {
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].CustomValidation.checkInput();
    }
}

submit.addEventListener('click', validate);
form.addEventListener('submit', validate);

