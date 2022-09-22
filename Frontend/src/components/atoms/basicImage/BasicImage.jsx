import React from "react";
import PropTypes from "prop-types";
import { Image } from "semantic-ui-react";
import photoDefault from '../../../images/atoms/basicImage/photoDefault.png';

function BasicImage({
  circular,
  className,
  src,
  size,
  verticalAlign,
  rounded,
  imageId,
  onClick,
  bordered,
  centered
}) {
  return (
    <Image
      rounded={rounded}
      circular={circular}
      className={className}
      src={src}
      size={size}
      verticalAlign={verticalAlign}
      id={imageId}
      onClick={onClick}
      bordered={bordered}
      centered={centered}
    />
  );
}

BasicImage.prototype = {
  rounded: PropTypes.bool,
  cirular: PropTypes.bool,
  className: PropTypes.string,
  src: PropTypes.string,
  size: PropTypes.string,
  verticalAlign: PropTypes.string,
  imageId: PropTypes.string,
  onClick: PropTypes.func,
  bordered: PropTypes.bool,
  centered: PropTypes.bool
};

BasicImage.defaultProps = {
  rounded: false,
  circular: false,
  className: '',
  src: photoDefault,
  size: 'tiny',
  verticalAlign: 'middle',
  imageId: '',
  onClick: () => { },
  bordered: false,
  centered: false
};

export default BasicImage;
