import React, { useEffect, useState, useCallback } from 'react';
import PropTypes from 'prop-types';
import { Form } from 'semantic-ui-react';

function NumberInput({
  name,
  setValue,
  trigger,
  value,
  min,
  size,
  className,
  onChange,
  placeholder,
  required,
  disabled,
  readOnly,
  defaultValue,
}) {
  const [number, setNumber] = useState(value);

  const setDefaults = useCallback(() => {
    if (defaultValue) {
      setValue(name, defaultValue);
      setNumber(defaultValue);
      trigger(name);
    }
  }, [defaultValue, name, setValue, trigger]);

  useEffect(() => {
    setDefaults();
  }, [setDefaults]);

  const handleChange = (event, data) => {
    setNumber(data.value);
    onChange(data.value);
    setValue(name, data.value);
    trigger(name);
  };

  return (
    <Form.Input
      name={name}
      value={number}
      min={min}
      className={className}
      input={{ className }}
      type="number"
      size={size}
      width={16}
      onChange={handleChange}
      placeholder={placeholder}
      required={required}
      disabled={disabled}
      readOnly={readOnly}
      defaultValue={defaultValue}
    />
  );
}

NumberInput.prototype = {
  name: PropTypes.string,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  value: PropTypes.number,
  min: PropTypes.number,
  size: PropTypes.string,
  className: PropTypes.string,
  onChange: PropTypes.func,
  placeholder: PropTypes.string,
  required: PropTypes.bool,
  disabled: PropTypes.bool,
  readOnly: PropTypes.bool,
  defaultValue: PropTypes.number,
};

NumberInput.defaultProps = {
  name: '',
  setValue: () => {},
  trigger: () => {},
  value: '',
  min: '',
  size: '',
  className: '',
  onChange: () => {},
  placeholder: '',
  required: false,
  disabled: false,
  readOnly: false,
};

export default NumberInput;
