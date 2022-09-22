import React from 'react';
import PropTypes from 'prop-types';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import DropDownInput from '../../atoms/dropDownInput/DropDownInput';
import { Grid } from 'semantic-ui-react';
import { ErrorMessage } from '@hookform/error-message';
import { doNothing } from '../../../utils/constants/constants';
import './style.css';

const DropDownField = ({
  name = '',
  setValue = doNothing,
  trigger = doNothing,
  value = '',
  className = '',
  labelIcon = '',
  labelWidth = '5',
  labelValue = '',
  labelVariant = '',
  labelColor = 'black',
  inputWidth = '11',
  inputSize = 'mini',
  openIconVariant = '',
  placeholder = 'Select one',
  onChange = doNothing,
  options = [],
  required = false,
  labelSize = 'medium',
  compact = false,
  classNameFields = '',
  allowAdditions = true,
  disabled = false,
  search = true,
  clearable = true,
  readOnly = false,
  errors = {},
  image = '',
  imagePosition = 'right',
  defaultValue = '',
  message = '',
}) => {
  return (
    <>
      <Grid.Column
        width={labelWidth}
        className={`dropdownLabel ${classNameFields}`}
      >
        <CustomLabel
          image={image}
          imagePosition={imagePosition}
          imageVariant='labelImage'
          value={labelValue}
          icon={labelIcon}
          required={required}
          size={labelSize}
          variant={labelVariant}
          color={labelColor}
        />
      </Grid.Column>
      <Grid.Column
        width={inputWidth}
        className={`dropdownColumn ${classNameFields}`}
      >
        <DropDownInput
          name={name}
          setValue={setValue}
          trigger={trigger}
          openIconVariant={openIconVariant}
          selectedValue={value}
          className={className}
          onChange={onChange}
          options={options}
          placeholder={placeholder}
          compact={compact}
          allowAdditions={allowAdditions}
          disabled={disabled}
          search={search}
          size={inputSize}
          clearable={clearable}
          readOnly={readOnly}
          defaultValue={defaultValue}
          message={message}
        />
        <ErrorMessage className='error' errors={errors} name={name} as='p' />
      </Grid.Column>
    </>
  );
};

DropDownField.prototype = {
  name: PropTypes.string,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
  className: PropTypes.string,
  labelIcon: PropTypes.string,
  labelWidth: PropTypes.string,
  labelValue: PropTypes.string,
  labelVariant: PropTypes.string,
  labelColor: PropTypes.string,
  inputWidth: PropTypes.string,
  inputSize: PropTypes.string,
  openIconVariant: PropTypes.string,
  placeholder: PropTypes.string,
  onChange: PropTypes.func,
  options: PropTypes.array,
  required: PropTypes.bool,
  labelSize: PropTypes.string,
  compact: PropTypes.bool,
  classNameFields: PropTypes.string,
  disabled: PropTypes.bool,
  allowAdditions: PropTypes.bool,
  search: PropTypes.bool,
  clearable: PropTypes.bool,
  readOnly: PropTypes.bool,
  errors: PropTypes.object,
  message: PropTypes.string,
};

export default DropDownField;
