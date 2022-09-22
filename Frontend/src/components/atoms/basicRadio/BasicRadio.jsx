import React from 'react';
import PropTypes from 'prop-types';
import { Radio } from 'semantic-ui-react'

function BasicRadio({
  label,
  name,
  value,
  variant,
  checked,
  style,
  onChange,
  disabled,
  readOnly
}) {
  return (
    <Radio
      disabled={disabled}
      readOnly={readOnly}
      style={style}
      label={label}
      name={name}
      value={value}
      checked={checked}
      className={variant}
      onChange={() => onChange(value)}
    >
    </Radio>
  )
}

BasicRadio.prototype = {
  style: PropTypes.object,
  label: PropTypes.string,
  name: PropTypes.string,
  value: PropTypes.string,
  variant: PropTypes.string,
  checked: PropTypes.bool,
  onChange: PropTypes.func,
  disabled: PropTypes.bool,
  readOnly: PropTypes.bool
}

BasicRadio.defaultProps = {
  style: {},
  label: '',
  name: '',
  value: '',
  variant: '',
  checked: false,
  onChange: () => { },
  disabled: false,
  readOnly: false
}

export default BasicRadio;
