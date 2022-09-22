const months = [
  "Jan",
  "Feb",
  "Mar",
  "Apr",
  "May",
  "June",
  "July",
  "Aug",
  "Sept",
  "Oct",
  "Nov",
  "Dec"
];

/** 
 * This method convert date format.
 * Example:
 * input: 2021-03-11 (YYYY/MM/DD)
 * output: Mar 11 2021
*/
const convert = (date) => {
  const parts = date.split('-');
  const monthIndex = parseInt(parts[1]) - 1;
  const result = `${months[monthIndex]} ${parts[2]} ${parts[0]}`
  return result;
}

const convertDateFormat = {
  convert
};

export default convertDateFormat;
