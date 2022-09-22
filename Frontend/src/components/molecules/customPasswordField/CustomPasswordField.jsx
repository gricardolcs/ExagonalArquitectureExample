import React, { Fragment, useState } from 'react';
import PropTypes from 'prop-types';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import { Grid, Input } from 'semantic-ui-react';
import { ErrorMessage } from '@hookform/error-message';
import { doNothing } from '../../../utils/constants/constants';
import PasswordIcon from '../../atoms/passwordIcon/PasswordIcon';

const CustomPasswordField = ({
  setValue = doNothing,
  trigger = doNothing,
  data = {},
  labelIcon = '',
  labelSize = 'medium',
  image = '',
  imagePosition = 'right',
  errors = {},
}) => {
  const [showPassword, setShowPassword] = useState(false);
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
    <Fragment>
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
          type={showPassword ? 'text' : 'password'}
          icon={
            <PasswordIcon value={showPassword} setValue={setShowPassword} />
          }
        />
        <ErrorMessage
          className='error'
          errors={errors}
          name={propertyName}
          as='p'
        />
      </Grid.Column>
    </Fragment>
  );
};

CustomPasswordField.prototype = {
  name: PropTypes.string,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  data: PropTypes.object,
  labelIcon: PropTypes.string,
  labelSize: PropTypes.string,
  image: PropTypes.string,
  imagePosition: PropTypes.string,
  errors: PropTypes.object,
};

export default CustomPasswordField;
