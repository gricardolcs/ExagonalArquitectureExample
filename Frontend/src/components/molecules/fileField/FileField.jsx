import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import FileInput from '../../atoms/fileInput/FileInput';
import CustomPopup from '../../atoms/customPopup/CustomPopup';
import { ErrorMessage } from '@hookform/error-message';
import { doNothing } from '../../../utils/constants/constants';

const FileField = ({
  name = '',
  setValue = doNothing,
  trigger = doNothing,
  labelValue = '',
  iconColor = '',
  imagePosition = '',
  variant = '',
  imageVariant = '',
  accept = '',
  handleFile = doNothing,
  labelWidth = 5,
  labelColor = '',
  inputWidth = 11,
  placeholder = '',
  required = false,
  onChange = doNothing,
  errorFile = '',
  buttonContent = 'Choose file',
  buttonVariant = '',
  errors = {},
}) => {
  return (
    <>
      <Grid.Column width={labelWidth}>
        <CustomLabel
          value={labelValue}
          color={labelColor}
          iconColor={iconColor}
          imagePosition={imagePosition}
          variant={variant}
          imageVariant={imageVariant}
          required={required}
        />
      </Grid.Column>
      <CustomPopup
        className='message pointer'
        message={errorFile}
        open={errorFile !== ''}
        position='right'
        trigger={
          <Grid.Column
            width={inputWidth}
            className='textInputColumn'
            style={{ display: 'flex', width: '59%' }}
          >
            <FileInput
              name={name}
              setValue={setValue}
              trigger={trigger}
              accept={accept}
              buttonContent={buttonContent}
              buttonVariant={buttonVariant}
              handleFile={handleFile}
              placeholder={placeholder}
              onChange={onChange}
            />
          </Grid.Column>
        }
      />
      <ErrorMessage className='error' errors={errors} name={name} as='p' />
    </>
  );
};

FileField.prototype = {
  name: PropTypes.string,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  errorFile: PropTypes.string,
  labelValue: PropTypes.string,
  handleFile: PropTypes.func,
  labelWidth: PropTypes.number,
  labelColor: PropTypes.string,
  inputWidth: PropTypes.number,
  placeholder: PropTypes.string,
  onChange: PropTypes.func,
  iconColor: PropTypes.string,
  imagePosition: PropTypes.string,
  variant: PropTypes.string,
  imageVariant: PropTypes.string,
  required: PropTypes.bool,
  buttonContent: PropTypes.node,
  buttonVariant: PropTypes.string,
  errors: PropTypes.object,
};

export default FileField;
