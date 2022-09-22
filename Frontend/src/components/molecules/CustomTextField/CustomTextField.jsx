import React from 'react';
import PropTypes from 'prop-types';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import { Grid, Input } from 'semantic-ui-react';
import { ErrorMessage } from '@hookform/error-message';
import { doNothing } from '../../../utils/constants/constants';

const CustomTextField = ({
  setValue = doNothing,
  trigger = doNothing,
  data = {},
  labelIcon = '',
  labelSize = 'medium',
  image = '',
  imagePosition = 'right',
  errors = {},
  defaultValue = '',
}) => {
  const {
    label,
    labelColor,
    labelVariant,
    labelWidth,
    inputWidth,
    placeholder,
    propertyName,
    required,
    sizeInput,
  } = data;

  const handleChange = (event, { name, value }) => {
    setValue(name, value);
    trigger(name);
  };

  return (
    <>
      <Grid.Column
        width={labelWidth}
        verticalAlign='middle'
        textAlign='left'
        className='textColumn'
      >
        <CustomLabel
          image={image}
          imagePosition={imagePosition}
          imageVariant='labelImage'
          imageSize='mini'
          labelIcon={labelIcon}
          required={required}
          value={label}
          variant={labelVariant}
          color={labelColor}
          size={labelSize}
        />
      </Grid.Column>
      <Grid.Column width={inputWidth} className='textInputColumn'>
        <Input
          placeholder={placeholder}
          size={sizeInput}
          name={propertyName}
          onChange={handleChange}
          defaultValue={defaultValue}
        />
        <ErrorMessage
          className='error'
          errors={errors}
          name={propertyName}
          as='p'
        />
      </Grid.Column>
    </>
  );
};

CustomTextField.prototype = {
  name: PropTypes.string,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  data: PropTypes.object,
  labelIcon: PropTypes.string,
  labelSize: PropTypes.string,
  image: PropTypes.string,
  imagePosition: PropTypes.string,
  errors: PropTypes.object,
  defaultValue: PropTypes.string,
};

export default CustomTextField;
