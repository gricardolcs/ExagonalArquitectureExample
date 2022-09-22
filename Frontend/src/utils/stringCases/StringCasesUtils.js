function firstLetterUpperCaseWithSpaceAsSeparation(value) {
    const valueToLowerCase = value.split("_").join(" ").toLowerCase()
    return valueToLowerCase.charAt(0).toUpperCase() + valueToLowerCase.slice(1);
}
  
function allUpperCaseWithUnderscoreAsSeparation (value) {
    return value.split(" ").join("_").toUpperCase();
}

const StringCasesUtils = {
    firstLetterUpperCaseWithSpaceAsSeparation,
    allUpperCaseWithUnderscoreAsSeparation,
}

export default StringCasesUtils;