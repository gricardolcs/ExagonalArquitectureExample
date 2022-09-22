import React from 'react';
import PropTypes from 'prop-types';
import NumberInput from '../../atoms/numberInput/NumberInput';
import { Grid } from 'semantic-ui-react';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import CustomPopup from '../../atoms/customPopup/CustomPopup';
import { ErrorMessage } from '@hookform/error-message';
import { doNothing } from '../../../utils/constants/constants';

const NumberField = ({
  name = '',
  setValue = doNothing,
  trigger = doNothing,
  value = '',
  min = 0,
  className = '',
  labelVariant = '',
  labelValue = '',
  labelIcon = '',
  labelWidth = '5',
  labelColor = 'black',
  image = '',
  imagePosition = 'right',
  inputWidth = '11',
  inputSize = 'mini',
  errorNumber = '',
  onChange = doNothing,
  placeholder = '',
  required = false,
  disabled = false,
  readOnly = false,
  errors = {},
  defaultValue = {},
}) => {
  return (
    <>
      <Grid.Column width={labelWidth} verticalAlign='bottom' textAlign='left'>
        <CustomLabel
          image={image}
          imagePosition={imagePosition}
          imageVariant='labelImage'
          imageSize='mini'
          color={labelColor}
          labelIcon={labelIcon}
          required={required}
          value={labelValue}
          variant={labelVariant}
        />
      </Grid.Column>
      <CustomPopup
        className='message pointer'
        message={errorNumber}
        open={errorNumber !== ''}
        position='right'
        trigger={
          <Grid.Column width={inputWidth}>
            <NumberInput
              name={name}
              setValue={setValue}
              trigger={trigger}
              size={inputSize}
              value={value}
              min={min}
              className={className}
              onChange={onChange}
              placeholder={placeholder}
              required={required}
              disabled={disabled}
              readOnly={readOnly}
              defaultValue={defaultValue}
            />
            <ErrorMessage
              className='error'
              errors={errors}
              name={name}
              as='p'
            />
          </Grid.Column>
        }
      />
    </>
  );
};

NumberField.prototype = {
  name: PropTypes.string,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  value: PropTypes.number,
  min: PropTypes.number,
  className: PropTypes.string,
  labelVariant: PropTypes.string,
  labelValue: PropTypes.string,
  labelIcon: PropTypes.string,
  labelWidth: PropTypes.string,
  labelColor: PropTypes.string,
  image: PropTypes.string,
  imagePosition: PropTypes.string,
  inputWidth: PropTypes.string,
  inputSize: PropTypes.string,
  errorNumber: PropTypes.string,
  onChange: PropTypes.func,
  placeholder: PropTypes.string,
  required: PropTypes.bool,
  disabled: PropTypes.bool,
  readOnly: PropTypes.bool,
  errors: PropTypes.object,
  defaultValue: PropTypes.object,
};

export default NumberField;
