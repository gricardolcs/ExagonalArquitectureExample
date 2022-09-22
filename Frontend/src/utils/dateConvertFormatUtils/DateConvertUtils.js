import moment from "moment";

const DATE_FORMATTER = "ll";

/**
 * @param {*} dateString 2020-04-03
 * @returns Apr 3, 2021
 */
function convert(dateString) {
  const newDate = moment(dateString).format(DATE_FORMATTER);
  return newDate;
}

const convertDateFormat = {
  convert
};

export default convertDateFormat;
