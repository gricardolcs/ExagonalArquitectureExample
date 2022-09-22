import React from 'react';
import PropTypes from 'prop-types';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import CustomDateInput from '../../atoms/customDateInput/CustomDateInput';
import GetDateUtils from '../../../utils/getValue/getValueOrCurrentDate';
import { Grid } from 'semantic-ui-react';
import { Fragment } from 'react';
import { ErrorMessage } from '@hookform/error-message';
import CustomPopup from '../../atoms/customPopup/CustomPopup';
import { doNothing } from '../../../utils/constants/constants';
import './style.css';

const DateField = ({
  name = '',
  setValue = doNothing,
  trigger = doNothing,
  value = '',
  className = '',
  disabled = false,
  labelIcon = '',
  iconColor = 'grey',
  labelWidth = '5',
  labelValue = '',
  labelVariant = '',
  labelColor = 'black',
  image = '',
  imagePosition = 'right',
  inputWidth = '11',
  inputSize = 'mini',
  errorDate = '',
  minDate = '',
  onChange = doNothing,
  readOnly = false,
  required = false,
  handleOnClear = doNothing,
  placeholder = '',
  errors = {},
}) => {
  return (
    <Fragment>
      <Grid.Column width={labelWidth} verticalAlign='middle'>
        <CustomLabel
          image={image}
          imagePosition={imagePosition}
          imageVariant='labelImage'
          value={labelValue}
          icon={labelIcon}
          iconColor={iconColor}
          required={required}
          variant={labelVariant}
          color={labelColor}
        />
      </Grid.Column>
      <CustomPopup
        className='message pointer'
        message={errorDate}
        open={errorDate !== ''}
        position='right'
        trigger={
          <Grid.Column width={inputWidth} className='inputColumn'>
            <CustomDateInput
              name={name}
              setValue={setValue}
              trigger={trigger}
              value={value}
              size={inputSize}
              minDate={
                minDate ? minDate : GetDateUtils.getValueOrCurrentDate(minDate)
              }
              className={className}
              disabled={disabled}
              onChange={onChange}
              readOnly={readOnly}
              error={errorDate}
              handleOnClear={handleOnClear}
              placeholder={placeholder}
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
    </Fragment>
  );
};

DateField.prototype = {
  name: PropTypes.string,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  value: PropTypes.string,
  className: PropTypes.string,
  disabled: PropTypes.bool,
  labelIcon: PropTypes.string,
  iconColor: PropTypes.string,
  labelWidth: PropTypes.string,
  labelValue: PropTypes.string,
  labelVariant: PropTypes.string,
  labelColor: PropTypes.string,
  image: PropTypes.string,
  imagePosition: PropTypes.string,
  inputWidth: PropTypes.string,
  inputSize: PropTypes.string,
  errorDate: PropTypes.string,
  minDate: PropTypes.string,
  onChange: PropTypes.func,
  readOnly: PropTypes.bool,
  required: PropTypes.bool,
  handleOnClear: PropTypes.func,
  placeholder: PropTypes.string,
  errors: PropTypes.object,
};

export default DateField;
