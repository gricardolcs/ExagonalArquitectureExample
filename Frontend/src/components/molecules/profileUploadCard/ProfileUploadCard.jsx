/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import BasicImage from '../../atoms/basicImage/BasicImage';
import { CustomCard } from '../customCard/CustomCard';
import iconUserAlternate from '../../../images/icons/icon-user-alternate.png';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import { fileToBase64 } from '../../../utils/fileToStringUtils/FileToStringUtils';
import './style.css';
import { doNothing } from '../../../utils/constants/constants';

const ProfileUploadCard = ({
  name = '',
  setValue = doNothing,
  trigger = doNothing,
  defaultValue = '',
}) => {
  const [image, setImage] = useState({
    value: iconUserAlternate,
    imageData: '',
  });

  const inputRef = React.createRef();
  const MAX_SIZE_BYTES = 2000000;
  const INCORRECT_MESSAGE = `The size is higher than allowed`;

  const handleOnUploadPhoto = ({ target }) => {
    const [file] = target.files;
    if (file && validateFileSize(file)) {
      fileToBase64(file)
        .then((data) => {
          const objectURL = URL.createObjectURL(file);
          setImage({ ...image, value: objectURL, imageData: data });
        })
        .catch(() =>
          setImage({ ...image, value: iconUserAlternate, imageData: '' })
        );
    }
  };

  const validateFileSize = (file) => {
    return file.size < MAX_SIZE_BYTES ? true : alert(INCORRECT_MESSAGE);
  };

  const renderLinkToUpload = () => {
    return (
      <>
        <CustomLabel
          basic
          handleOnClick={() => inputRef.current.click()}
          size='large'
          value='Upload image'
        />
        <input
          ref={inputRef}
          type='file'
          accept='image/png, image/jpg, image/jpeg'
          onChange={handleOnUploadPhoto}
        />
      </>
    );
  };

  const setDefaultImage = () => {
    if (defaultValue) {
      setImage({ ...image, value: `data:image/jpeg;base64,${defaultValue}` });
    }
  };

  useEffect(() => {
    setDefaultImage();
  }, []);

  useEffect(() => {
    setValue(name, image.imageData);
    trigger(name);
  }, [setValue, trigger, name, image.imageData]);

  return (
    <CustomCard
      typeCard='upload-image'
      cardImage={
        <BasicImage className='basicImageCard' src={image.value} circular />
      }
      cardHeader={renderLinkToUpload()}
      cardMeta='(optional)'
      cardDescription={[
        { key: 'format', render: 'Jpg or Png file format' },
        { key: 'size', render: 'Max. 2 Mb size' },
      ]}
      tabColorVariant='grey'
      showStatus={false}
      showRemove={image.length > 0}
      handleOnRemoveImage={() => setImage({ ...image, value: '' })}
    />
  );
};

ProfileUploadCard.prototype = {
  handleOnImagenProfile: PropTypes.func,
  name: PropTypes.string,
  trigger: PropTypes.func,
  defaultValue: PropTypes.func,
};

export default ProfileUploadCard;
