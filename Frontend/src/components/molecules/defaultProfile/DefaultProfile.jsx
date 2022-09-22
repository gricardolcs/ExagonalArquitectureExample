import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import BasicImage from '../../atoms/basicImage/BasicImage';
import defaultProfile from '../../../images/logos/logo-profile.png';
import './style.css';

function DefaultProfile({ srcProfile, sizeProfile, userName }) {
  return (
    <Fragment>
      <div className={'flex-profile'}>
        <BasicImage
          src={srcProfile}
          size={sizeProfile}
        />
        <div className={'text-profile'}>
          <p>{userName}</p>
        </div>
      </div>
    </Fragment>
  );
}

DefaultProfile.prototype = {
  srcProfile: PropTypes.string,
  sizeProfile: PropTypes.string,
  userName: PropTypes.string
}

DefaultProfile.defaultProps = {
  srcProfile: defaultProfile,
  sizeProfile: 'tiny',
  userName: ''
}

export default DefaultProfile;
