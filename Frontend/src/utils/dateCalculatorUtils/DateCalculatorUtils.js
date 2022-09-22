import moment from 'moment';

function increaseDays(date, amount, increaseUnit) {
  if (date) {
    return moment(date, 'YYYY-MM-DD')
      .add(amount,  increaseUnit)
      .format('YYYY-MM-DD');
  }
}

function isStartDateBeforeEndDate(startDate, endDate) {
  return moment(startDate).isSameOrBefore(endDate);
}

const DateCalculatorUtils = {
  increaseDays,
  isStartDateBeforeEndDate,
}

export default DateCalculatorUtils;