import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { TextArea } from 'semantic-ui-react';
import './styles.css';

function TextAreaInput({
  name,
  setValue,
  trigger,
  value,
  onChange,
  placeholder,
  rows,
  variant,
  disabled,
  readOnly,
  maxLength
}) {

  const [text, setText] = useState(value);

  const handleChange = (event, data) => {
    setText(data.value);
    onChange(data.value);
    setValue(name, data.value)
    trigger(name)
  }

  return (
    <>
      <TextArea
        name={name}
        disabled={disabled}
        onChange={handleChange}
        placeholder={placeholder}
        rows={rows}
        value={text}
        className={variant}
        readOnly={readOnly}
        maxLength={maxLength}
      />
      <p className="text-length-counter">{text.length} / {maxLength}</p>
    </>
  )
}

TextAreaInput.prototype = {
  name: PropTypes.string,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  value: PropTypes.string,
  onChange: PropTypes.func,
  placeholder: PropTypes.string,
  variant: PropTypes.string,
  rows: PropTypes.number,
  disabled: PropTypes.bool,
  readOnly: PropTypes.bool
}

TextAreaInput.defaultProps = {
  name: '',
  setValue: () => {},
  trigger: () => {},
  value: '',
  onChange: () => { },
  placeholder: '',
  variant: '',
  rows: 3,
  disabled: false,
  readOnly: false
}

export default TextAreaInput;
