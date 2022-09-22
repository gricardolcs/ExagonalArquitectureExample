import React from 'react';
import PropTypes from 'prop-types';
import { Tab } from 'semantic-ui-react';

function CustomTab({ defaultActiveIndex, variant, menu, panes, onTabChange, renderActiveOnly }) {
  return (
    <Tab
      onTabChange={onTabChange}
      className={variant}
      menu={menu}
      panes={panes}
      defaultActiveIndex={defaultActiveIndex}
      renderActiveOnly={renderActiveOnly}
    />
  )
}

CustomTab.prototype = {
  defaultActiveIndex: PropTypes.number,
  variant: PropTypes.string,
  menu: PropTypes.shape({
    secondary: PropTypes.bool,
    pointing: PropTypes.bool,
  }),
  panes: PropTypes.array,
  onTabChange: PropTypes.func,
  renderActiveOnly: PropTypes.bool
}

CustomTab.defaultProps = {
  defaultActiveIndex: 0,
  variant: '',
  menu: {
    secondary: true,
    pointing: true
  },
  panes: [],
  onTabChange: () => { },
  renderActiveOnly: true
}

export default CustomTab;
