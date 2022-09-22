import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Dropdown } from 'semantic-ui-react';
import { useEffect } from 'react';
import CustomField from '../customField/CustomField';
import { doNothing } from '../../../utils/constants/constants';
import './style.css';

const DropDownInput = ({
  name = '',
  setValue = doNothing,
  trigger = doNothing,
  selectedValue = '',
  size = 'mini',
  inputWidth = null,
  className = '',
  onChange = doNothing,
  options = [],
  placeholder = 'Select one',
  allowAdditions = true,
  compact = false,
  search = true,
  clearable = true,
  readOnly = false,
  openIconVariant = '',
  defaultValue = '',
  message = '',
}) => {
  const [selected, setSelected] = useState(selectedValue);
  const [containOptions, setContainOptions] = useState(options);
  const [open, setOpen] = useState(false);

  const handleChange = (event, { value, name }) => {
    setSelected(value);
    onChange(value);
    setValue(name, value);
    trigger(name);
  };

  const handleOnOpenAndClose = () => {
    if (readOnly) {
      setOpen(false);
    } else {
      setOpen(!open);
    }
  };

  const handleAddition = (event, { value }) => {
    setContainOptions([...containOptions, { text: value, value }]);
  };

  useEffect(() => {
    setContainOptions(options);
  }, [options]);

  return (
    <CustomField width={inputWidth}>
      <Dropdown
        name={name}
        value={selected || defaultValue}
        icon={{ className: `dropdown ${openIconVariant}` }}
        className={`${size} ${className}`}
        clearable={clearable}
        search={search}
        selection
        options={containOptions}
        placeholder={placeholder}
        onChange={handleChange}
        allowAdditions={allowAdditions}
        onAddItem={handleAddition}
        compact={compact}
        disabled={readOnly}
        searchInput={{ readOnly, className }}
        onOpen={handleOnOpenAndClose}
        onClose={handleOnOpenAndClose}
        open={open}
      />
      {message && <div className='dropdown-message'>{message}</div>}
    </CustomField>
  );
};

DropDownInput.prototype = {
  name: PropTypes.string,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  selectedValue: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
  size: PropTypes.string,
  inputWidth: PropTypes.number,
  className: PropTypes.string,
  onChange: PropTypes.func,
  options: PropTypes.array,
  placeholder: PropTypes.string,
  allowAdditions: PropTypes.bool,
  compact: PropTypes.bool,
  search: PropTypes.bool,
  clearable: PropTypes.bool,
  disabled: PropTypes.bool,
  readOnly: PropTypes.bool,
  openIconVariant: PropTypes.string,
  message: PropTypes.string,
};

export default DropDownInput;
