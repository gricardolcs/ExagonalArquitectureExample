import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import BasicImage from '../../atoms/basicImage/BasicImage';
import defaultLogo from '../../../images/logos/logo-navbar.png';
import './style.css';

function DefaultLogo({ srcLogo, sizeLogo, textSide }) {
  return (
    <Fragment>
      <div className={'flex-logo'}>
        <BasicImage
          src={srcLogo}
          size={sizeLogo}
        />
        <div className={'text-side-logo'}>
          <h4>{textSide}</h4>
        </div>
      </div>
    </Fragment>
  )
}

DefaultLogo.prototype = {
  srcLogo: PropTypes.string,
  sizeLogo: PropTypes.string,
  textSide: PropTypes.string
}

DefaultLogo.defaultProps = {
  srcLogo: defaultLogo,
  sizeLogo: 'tiny',
  textSide: ''
}

export default DefaultLogo;
