import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Button, Grid } from 'semantic-ui-react';
import CustomLabel from '../customLabel/CustomLabel';
import { doNothing } from '../../../utils/constants/constants';
import './style.css';

const FileInput = ({
  name = '',
  setValue = doNothing,
  trigger = doNothing,
  accept = '',
  handleFile = doNothing,
  onChange = doNothing,
  buttonContent = 'Choose file',
  buttonVariant = '',
}) => {
  const inputRef = React.createRef();
  const [fileName, setFileName] = useState();

  const handleOnChange = ({ target }) => {
    const [file] = target.files;
    if (file) {
      setFileName(file.name);
      handleFile(file);
      onChange(file.name);
      setValue(name, file);
      trigger(name);
    }
  };

  return (
    <>
      <Grid.Column width={10} className='fileNameTextColumn'>
        <CustomLabel basic={true} variant='fileNameText'>
          {fileName}
        </CustomLabel>
      </Grid.Column>
      <Grid.Column width={4} className='fileUploadButtonColumn'>
        <Button
          id='file'
          name={name}
          className={buttonVariant}
          content={buttonContent}
          onClick={() => inputRef.current.click()}
          size='mini'
        />
        <input
          ref={inputRef}
          accept={accept}
          type='file'
          onChange={handleOnChange}
        />
      </Grid.Column>
    </>
  );
};

FileInput.prototype = {
  name: PropTypes.string,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  accept: PropTypes.string,
  handleFile: PropTypes.func,
  onChange: PropTypes.func,
  buttonContent: PropTypes.node,
  buttonVariant: PropTypes.string,
  placeholder: PropTypes.string,
};

export default FileInput;
