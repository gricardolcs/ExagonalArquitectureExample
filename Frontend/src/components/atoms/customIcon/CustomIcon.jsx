import React from 'react'
import PropTypes from 'prop-types';
import { Icon } from 'semantic-ui-react'

function CustomIcon({
  color,
  className,
  name,
  size,
  onClick,
  disabled
}) {
  return (
    <Icon
      size={size}
      color={color}
      className={className}
      name={name}
      disabled={disabled}
      onClick={onClick}
    />
  )
}

CustomIcon.prototype = {
  color: PropTypes.string,
  className: PropTypes.string,
  name: PropTypes.string,
  size: PropTypes.string,
  onClick: PropTypes.func,
}

CustomIcon.defaultProps = {
  color: 'grey',
  className: '',
  name: '',
  size: 'small',
  onClick: () => { },
}

export default CustomIcon;