import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Form } from 'semantic-ui-react';
import './style.css';

function TextInput({
  value: defaultValue,
  size,
  className,
  onChange,
  placeholder,
  required,
  disabled,
  maxLength,
  readOnly,
  icon,
  label,
  error,
  type,
}) {
  const [text, setText] = useState(defaultValue);

  const handleChange = (event, data) => {
    setText(data.value);
    onChange(data.value);
  };

  return (
    <Form.Input
      value={text}
      input={{ maxLength, className }}
      className={className}
      type={type}
      size={size}
      width={16}
      onChange={handleChange}
      placeholder={placeholder}
      required={required}
      disabled={disabled}
      readOnly={readOnly}
      label={label}
      icon={icon}
      error={error}
    />
  );
}

TextInput.prototype = {
  defaultValue: PropTypes.string,
  size: PropTypes.string,
  className: PropTypes.string,
  onChange: PropTypes.func,
  placeholder: PropTypes.string,
  required: PropTypes.bool,
  disabled: PropTypes.bool,
  maxLength: PropTypes.number,
  icon: PropTypes.string,
  readOnly: PropTypes.bool,
  label: PropTypes.string,
  error: PropTypes.bool,
  type: PropTypes.string,
};

TextInput.defaultProps = {
  defaultValue: '',
  size: 'mini',
  className: '',
  onChange: () => {},
  placeholder: '',
  required: false,
  disabled: false,
  maxLength: -1,
  icon: null,
  readOnly: false,
  label: '',
  error: false,
  type: 'text',
};

export default TextInput;
