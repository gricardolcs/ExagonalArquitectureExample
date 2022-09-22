function isNotEmpty(input) {
  return input !== '' && input !== undefined && input !== null;
}

/**
 * validates if the date satisfies the regular expression: 'YYYY-MM-DD',
 * extracted from internet:
 * https://stackoverflow.com/questions/22061723/regex-date-validation-for-yyyy-mm-dd/22061800
 * @param {String} date to been evaluate
 * @returns {boolean} true when date satisfies false otherwise
 */
function isValidDateFormat(date) {
  return /^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/.test(date);
}

function isValidEmailFormat(email) {
  return /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  .test(email); 
}
function isValidUserPasswordFormat(password) {
  return /(?=.*[a-z\u00f1])(?=.*[A-Z\u00d1])(?=.*\d)(?=.*[!@#$%^&*_=+-]).{8,16}/
  .test(password); 
}

function isValidPasswordConfirmation(password, passwordConfirmation) {
  return password === passwordConfirmation; 
}
const InputValidatorUtils = {
  isNotEmpty,
  isValidDateFormat,
  isValidEmailFormat,
  isValidUserPasswordFormat,
  isValidPasswordConfirmation,
}

export default InputValidatorUtils;