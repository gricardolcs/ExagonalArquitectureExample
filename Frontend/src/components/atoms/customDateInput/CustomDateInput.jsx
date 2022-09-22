import React, { useState } from 'react';
import PropTypes from 'prop-types';
import DatePicker from "react-datepicker";
import moment from 'moment';
import "react-datepicker/dist/react-datepicker.css";
import "./style.css";

function CustomDateInput({
  name,
  setValue,
  trigger,
  value,
  disabled,
  minDate,
  onChange,
  readOnly,
  placeholder,
  size,
  popupPosition,
  handleOnClear,
}) {

  const [date, setDate] = useState(value);

  function handleOnChange(date) {
    if (date !== null) {
      const arrayDate = moment(date).format('YYYY-MM-DD');
      setValue(name, arrayDate)
      trigger(name)
      setDate(arrayDate.split("-"));
      onChange(arrayDate);  
    }
  }

  function setMinDate() {
    const newMinDate = new Date(minDate);
    newMinDate.setDate(newMinDate.getDate() + 1);
    return newMinDate;
  }

  return (
    <DatePicker
      name={name}
      disabled={disabled}
      dateFormat="yyyy-MM-dd"
      minDate={setMinDate()}
      disabledKeyboardNavigation
      peekNextMonth
      showMonthDropdown
      showYearDropdown
      dropdownMode="select"      
      onChange={(selectedDate) => {handleOnChange(selectedDate)}}
      placeholderText={placeholder}
      readOnly={readOnly}
      selected={date? new Date(date): null}
      value={date}
    />
  );
}

CustomDateInput.prototype = {
  name: PropTypes.string,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  value: PropTypes.string,
  disabled: PropTypes.bool,
  minDate: PropTypes.string,
  onChange: PropTypes.func,
  readOnly: PropTypes.bool,
  placeholder: PropTypes.string,
  size: PropTypes.string,
  popupPosition: PropTypes.string,
  handleOnClear: PropTypes.func
}

CustomDateInput.defaultProps = {
  name: '',
  setValue: () => {},
  trigger: () => {},
  value: '',
  disabled: false,
  minDate: '',
  onChange: () => { },
  readOnly: false,
  placeholder: '',
  size: 'mini',
  popupPosition: 'right center',
  handleOnClear: () => { }
}

export default CustomDateInput;
