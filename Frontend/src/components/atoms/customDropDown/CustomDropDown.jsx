import React from 'react';
import PropTypes from 'prop-types';
import { Dropdown } from 'semantic-ui-react';

export function CustomDropDown({
  trigger,
  icon,
  direction,
  header,
  items,
  menuVariant,
  headerVariant,
  variant,
}) {
  return (
    <Dropdown
      trigger={trigger}
      icon={icon}
      direction={direction}
      className={variant}
    >
      <Dropdown.Menu className={menuVariant}>
        {header && (
          <Dropdown.Header className={headerVariant}>{header}</Dropdown.Header>
        )}
        {items.map((item) => (
          <Dropdown.Item
            key={`option-${item.text}`}
            onClick={item.handleOnClick}
          >
            {item.text}
          </Dropdown.Item>
        ))}
      </Dropdown.Menu>
    </Dropdown>
  );
}

CustomDropDown.prototype = {
  trigger: PropTypes.node,
  icon: PropTypes.string,
  direction: PropTypes.string,
  items: PropTypes.arrayOf(
    PropTypes.shape({
      text: PropTypes.string,
      handleOnClick: PropTypes.func,
    })
  ),
  variant: PropTypes.string,
  header: PropTypes.node,
  menuVariant: PropTypes.string,
  headerVariant: PropTypes.string,
};

CustomDropDown.defaultProps = {
  trigger: <></>,
  icon: null,
  direction: 'right',
  items: [],
  variant: '',
  header: <></>,
  menuVariant: '',
  headerVariant: '',
};

export default CustomDropDown;
