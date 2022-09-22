
/*
* This method verify if current date is in a range.
* startDateString and endDateString should be in the following format: (YYYY-MM-DD)
* example: 
* startDateString: 2021-03-11 
* endDateString: 2021-04-11 
*/
const verify = (startDateString, endDateString, currentDate = new Date()) => {
  const startDate = new Date(startDateString);
  const endDate = new Date(endDateString);
  startDate.setDate(startDate.getDate() + 1);
  endDate.setDate(endDate.getDate() + 1);
  return currentDate >= startDate && currentDate <= endDate
}

const verifyDateInRange = {
  verify
};

export default verifyDateInRange;
