import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import BasicImage from '../../atoms/basicImage/BasicImage';
import filterIcon from '../../../images/icons/icon-filter.png';
import downloadIcon from '../../../images/icons/icon-download-circular.png'
import pixelsIcon from '../../../images/icons/icon-pixels.png';
import './style.css';

function ApplicantsProjectsMenuTemplate({ imageId, imageSize, onClick }) {
  return (
    <Fragment>
      <div className={'options-template'}>
        <div className={'shrink'} />
        <BasicImage
          className={'options-template-img filterOptions'}
          size={imageSize}
          src={filterIcon}
        />
        <BasicImage
          className={'options-template-img filterOptions'}
          size={imageSize}
          src={pixelsIcon}
        />
        <BasicImage
          className={'options-template-img'}
          size={imageSize}
          src={downloadIcon}
        />
      </div>
    </Fragment>
  )
}

ApplicantsProjectsMenuTemplate.prototype = {
  imageId: PropTypes.string,
  imageSize: PropTypes.string,
  onClick: PropTypes.func
}

ApplicantsProjectsMenuTemplate.defaultProps = {
  imageId: '',
  imageSize: 'mini',
  onClick: () => { }
}

export default ApplicantsProjectsMenuTemplate;
