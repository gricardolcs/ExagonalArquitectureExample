import React from 'react';
import PropTypes from 'prop-types';
import { Button } from 'semantic-ui-react';
import { doNothing } from '../../../utils/constants/constants';

function CustomButton({
  id = '',
  color = null,
  disabled = false,
  label = 'Button',
  onClick = doNothing,
  type = 'button',
  variant = '',
  circular = true,
  size = 'small',
  fluid = false,
}) {
  return (
    <Button
      id={id}
      color={color}
      disabled={disabled}
      onClick={onClick}
      role={type}
      className={variant}
      circular={circular}
      size={size}
      fluid={fluid}
    >
      {label}
    </Button>
  );
}

CustomButton.prototype = {
  id: PropTypes.string,
  color: PropTypes.string,
  disabled: PropTypes.bool,
  label: PropTypes.string,
  onClick: PropTypes.func,
  type: PropTypes.string,
  variant: PropTypes.string,
  circular: PropTypes.bool,
  size: PropTypes.string,
  fluid: PropTypes.bool,
};

export default CustomButton;
