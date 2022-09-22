import React from 'react';
import PropTypes from 'prop-types';
import { useDropzone } from 'react-dropzone';
import CustomButton from '../customButton/CustomButton';
import './style.css';

function CustomDragAndDrop({ onDropAccepted, onDropRejected, file, acceptedFiles }) {
    const { getRootProps, getInputProps, open } = useDropzone({
        onDropAccepted,
        onDropRejected,
        accept: acceptedFiles,
        noClick: true,
        noKeyboard: true,
    });

    return (
        <div className='dropzone-whole' {...getRootProps()} data-testid='dropArea'>
            <input className='dropzone-input' {...getInputProps()} />
            <div className='content-center'>
                {file.name ?
                    <div className='file-imported'>
                        <div>{'File selected:'}</div>
                        <div className='file-imported-name'>{file.name}</div>
                    </div> :
                    <div className='drop-message'>{'Drop your file here or'}</div>}
                <CustomButton
                    label={'Choose a file'}
                    onClick={open}
                    circular={false} />
            </div>
        </div>
    );
}

CustomDragAndDrop.prototype = {
    onDropAccepted: PropTypes.func,
    onDropRejected: PropTypes.func,
    file: PropTypes.object,
    acceptedFiles: PropTypes.string,
}

CustomDragAndDrop.defaultProps = {
    onDropAccepted: () => { },
    onDropRejected: () => { },
    file: {},
    acceptedFiles: '*',
}

export default CustomDragAndDrop;