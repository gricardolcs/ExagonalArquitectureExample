import React, { Fragment, useState } from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import CustomPopup from '../../atoms/customPopup/CustomPopup';
import TextInput from '../../atoms/textInput/TextInput';
import PasswordIcon from '../../atoms/passwordIcon/PasswordIcon';

const PasswordField = ({
  value,
  className,
  labelVariant,
  labelValue,
  labelIcon,
  labelWidth,
  labelColor,
  labelSize,
  image,
  imagePosition,
  inputWidth,
  inputSize,
  inputMaxLength,
  onChange,
  placeholder,
  required,
  errorText,
  readOnly,
}) => {
  const [showPassword, setShowPassword] = useState(false);

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
          value={labelValue}
          variant={labelVariant}
          color={labelColor}
          size={labelSize}
        />
      </Grid.Column>
      <CustomPopup
        className='message pointer'
        message={errorText}
        open={errorText !== ''}
        position='right'
        trigger={
          <Grid.Column width={inputWidth} className='textInputColumn'>
            <TextInput
              size={inputSize}
              maxLength={inputMaxLength}
              value={value}
              className={className}
              onChange={onChange}
              placeholder={placeholder}
              required={required}
              readOnly={readOnly}
              type={showPassword ? 'text' : 'password'}
              icon={
                !readOnly && (
                  <PasswordIcon value={showPassword} setValue={setShowPassword}/>
                )
              }
            />
          </Grid.Column>
        }
      />
    </Fragment>
  );
};

PasswordField.prototype = {
  value: PropTypes.string,
  className: PropTypes.string,
  labelVariant: PropTypes.string,
  labelValue: PropTypes.string,
  labelIcon: PropTypes.string,
  labelWidth: PropTypes.string,
  labelColor: PropTypes.string,
  labelSize: PropTypes.string,
  image: PropTypes.string,
  imagePosition: PropTypes.string,
  inputIcon: PropTypes.string,
  inputType: PropTypes.string,
  inputWidth: PropTypes.string,
  inputSize: PropTypes.string,
  inputMaxLength: PropTypes.number,
  onChange: PropTypes.func,
  placeholder: PropTypes.string,
  required: PropTypes.bool,
  disabled: PropTypes.bool,
  readOnly: PropTypes.bool,
  error: PropTypes.bool,
};

PasswordField.defaultProps = {
  value: '',
  className: '',
  labelVariant: '',
  labelValue: '',
  labelIcon: '',
  labelWidth: '5',
  labelColor: 'black',
  labelSize: 'medium',
  image: '',
  imagePosition: 'right',
  inputIcon: null,
  inputType: null,
  inputWidth: '11',
  inputSize: 'mini',
  inputMaxLength: -1,
  errorText: '',
  onChange: () => {},
  placeholder: '',
  required: false,
  disabled: false,
  readOnly: false,
  error: false,
};

export default PasswordField;
