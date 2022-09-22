import React, { Fragment } from 'react';
import PropTypes from 'prop-types';
import CustomTab from '../../atoms/customTab/CustomTab';
import './style.css';

function NavBarTemplate({ panes, updateActivePane, defaultActiveIndex }) {
  const navbarStyleConfiguration = {
    justifyContent: 'center',
    fontSize: '1.25rem',
    margin: '0 0 0 -8%',
  };

  return (
    <Fragment>
      <CustomTab
        variant={'tabs'}
        menu={{
          inverted: true,
          secondary: true,
          text: true,
          className: 'navbar-tab custom-tabs',
          style: navbarStyleConfiguration,
        }}
        panes={panes}
        onTabChange={updateActivePane}
        defaultActiveIndex={defaultActiveIndex}
        renderActiveOnly
      />
    </Fragment>
  );
}

NavBarTemplate.prototype = {
  panes: PropTypes.array,
  defaultActiveIndex: PropTypes.number.isRequired,
};

NavBarTemplate.defaultProps = {
  panes: [],
};

export default NavBarTemplate;
