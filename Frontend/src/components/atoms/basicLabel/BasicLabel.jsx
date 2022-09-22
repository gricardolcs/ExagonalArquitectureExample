import React from 'react';
import PropTypes from 'prop-types';
import { Label } from 'semantic-ui-react';
import { doNothing } from '../../../utils/constants/constants';

const BasicLabel = ({
  children = null,
  size = null,
  color = null,
  variant = '',
  handleOnClick = doNothing,
  circular = true,
}) => {
  return (
    <Label
      color={color}
      as='label'
      className={variant}
      circular={circular}
      size={size}
      onClick={handleOnClick}
    >
      {children}
    </Label>
  );
};

BasicLabel.prototype = {
  size: PropTypes.string,
  color: PropTypes.string,
  variant: PropTypes.string,
  handleOnClick: PropTypes.func,
  children: PropTypes.node,
};

export default BasicLabel;
