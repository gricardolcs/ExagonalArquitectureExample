import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import TextAreaInput from '../../atoms/textAreaInput/TextAreaInput';
import { Grid } from 'semantic-ui-react';

function TextAreaField({
  name,
  setValue,
  trigger,
  value,
  variantTextArea,
  labelVariant,
  labelIcon,
  labelWidth,
  labelValue,
  labelColor,
  inputWidth,
  onChange,
  placeholder,
  required,
  variantLabelTextArea,
  disabled,
  rows,
  readOnly,
  maxLength
}) {
  return (
    <Fragment>
      <Grid.Column width={labelWidth} className={variantLabelTextArea}>
        <CustomLabel
          variant={labelVariant}
          value={labelValue}
          icon={labelIcon}
          required={required}
          color={labelColor}
        />
      </Grid.Column>
      <Grid.Column width={inputWidth} className={variantLabelTextArea}>
        <TextAreaInput
          name={name}
          setValue={setValue}
          trigger={trigger}
          disabled={disabled}
          value={value}
          variant={variantTextArea}
          onChange={onChange}
          placeholder={placeholder}
          rows={rows}
          readOnly={readOnly}
          maxLength={maxLength}
        />
      </Grid.Column>
    </Fragment>
  );
}

TextAreaField.prototype = {
  name: PropTypes.string,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  value: PropTypes.string,
  variantTextArea: PropTypes.string,
  labelVariant: PropTypes.string,
  labelIcon: PropTypes.string,
  labelWidth: PropTypes.string,
  labelValue: PropTypes.string,
  labelColor: PropTypes.string,
  inputWidth: PropTypes.string,
  onChange: PropTypes.func,
  placeholder: PropTypes.string,
  required: PropTypes.bool,
  disabled: PropTypes.bool,
  readOnly: PropTypes.bool
}

TextAreaField.defaultProps = {
  name: '',
  setValue: () => {},
  trigger: () => {},
  value: '',
  variantTextArea: '',
  labelVariant: '',
  labelIcon: '',
  labelWidth: '5',
  labelValue: '',
  labelColor: 'black',
  inputWidth: '11',
  onChange: () => { },
  placeholder: '',
  required: false,
  disabled: false,
  readOnly: false
}

export default TextAreaField;
