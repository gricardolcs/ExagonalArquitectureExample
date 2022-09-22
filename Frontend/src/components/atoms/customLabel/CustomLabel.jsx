import React from 'react';
import PropTypes from 'prop-types';
import { Icon, Image, Label } from 'semantic-ui-react'
import { Fragment } from 'react';
import './style.css';

function CustomLabel({
  icon,
  iconColor,
  iconPosition,
  iconInverted,
  image,
  imagePosition,
  imageVariant,
  imageSize,
  value,
  required,
  elementType,
  basic,
  variant,
  handleOnClick,
  children,
  size,
  color
}) {
  return (
    <Fragment>
      {children ? (
        <Label
          as={elementType}
          basic={basic}
          className={`custom ${variant}`}
          onClick={handleOnClick}
          size={size}
          color={color}
        >
          { image && imagePosition === 'left' &&
            <Image src={image} size={imageSize} className={imageVariant} verticalAlign='middle' />
          }
          {icon && iconPosition === 'left' &&
            <Icon name={icon} color={iconColor} inverted={iconInverted} />
          }
          {children}
          {icon && iconPosition === 'right' &&
            <Icon name={icon} color={iconColor} inverted={iconInverted} />
          }
          { image && imagePosition === 'right' &&
            <Image src={image} size={imageSize} className={imageVariant} verticalAlign='middle' />
          }
        </Label>
      ) : (
        <Label
          as={elementType}
          basic={basic}
          className={`custom ${variant}`}
          onClick={handleOnClick}
          size={size}
          color={color}
        >
          { image && imagePosition === 'left' &&
            <Image src={image} size={imageSize} className={imageVariant} verticalAlign='middle' />
          }
          {icon && iconPosition === 'left' &&
            <Icon name={icon} color={iconColor} inverted={iconInverted} />
          }
          {value}
          {icon && iconPosition === 'right' &&
            <Icon name={icon} color={iconColor} inverted={iconInverted} />
          }
          { image && imagePosition === 'right' &&
            <Image src={image} size={imageSize} className={imageVariant} verticalAlign='middle' />
          }
          {required && <span> * </span>}
        </Label>
      )}
    </Fragment>
  );
}

CustomLabel.prototype = {
  icon: PropTypes.string,
  iconColor: PropTypes.string,
  iconPosition: PropTypes.string,
  iconInverted: PropTypes.bool,
  image: PropTypes.string,
  imagePosition: PropTypes.string,
  imageVariant: PropTypes.string,
  imageSize: PropTypes.string,
  value: PropTypes.string,
  required: PropTypes.bool,
  elementType: PropTypes.string,
  basic: PropTypes.bool,
  variant: PropTypes.string,
  handleOnClick: PropTypes.func,
  children: PropTypes.node,
  size: PropTypes.string,
  color: PropTypes.string
}

CustomLabel.defaultProps = {
  icon: '',
  iconColor: 'grey',
  iconPosition: 'right',
  iconInverted: false,
  image: '',
  imagePosition: '',
  imageVariant: '',
  imageSize: 'mini',
  value: '',
  required: false,
  elementType: 'label',
  basic: true,
  variant: '',
  handleOnClick: () => { },
  size: 'medium',
  color: null
}

export default CustomLabel;
