import React from 'react';
import PropTypes from 'prop-types';
import { Icon } from 'semantic-ui-react';
import { doNothing } from '../../../utils/constants/constants';

const PasswordIcon = ({ value = true, setValue = doNothing }) => {
  return (
    <Icon
      name={value ? 'eye' : 'eye slash'}
      link
      onClick={() => setValue(!value)}
    />
  );
};

export default PasswordIcon;

PasswordIcon.prototype = {
  value: PropTypes.bool,
  setValue: PropTypes.func,
};
