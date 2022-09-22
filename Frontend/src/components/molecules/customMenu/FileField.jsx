import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import FileInput from '../../atoms/fileInput/FileInput';
import CustomPopup from '../../atoms/customPopup/CustomPopup';

function FileField({
  labelValue = '',
  iconColor = '',
  imagePosition = '',
  variant = '',
  imageVariant = '',
  accept = '',
  handleFile = () => {},
  labelWidth = 5,
  labelColor = '',
  inputWidth = 11,
  placeholder = '',
  required = false,
  onChange = () => {},
  errorFile = '',
  buttonContent = 'Choose file',
  buttonVariant = '',
}) {
  return (
    <Fragment>
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
        className="message pointer"
        message={errorFile}
        open={errorFile !== ''}
        position="right"
        trigger={
          <Grid.Column
            width={inputWidth}
            className="textInputColumn"
            style={{ display: 'flex', width: '59%' }}
          >
            <FileInput
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
    </Fragment>
  );
}

FileField.prototype = {
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
};

export default FileField;
