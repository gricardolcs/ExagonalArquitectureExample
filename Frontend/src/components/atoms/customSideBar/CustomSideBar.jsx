import React from 'react';
import PropTypes from 'prop-types';
import { Grid, Sidebar } from 'semantic-ui-react';
import './style.css';

function CustomSideBar({ sideBarContent, headerContent, bodyContent }) {
  return (
    <Grid className="sidebar-grid">
      <Grid.Column width={3}>
        <Sidebar className="custom-sidebar" visible={true}>
          {sideBarContent}
        </Sidebar>
      </Grid.Column>
      <Grid.Column width={13}>
        <Grid>
          <Grid.Row className="sidebar-header-row">
            <Grid.Column className="sidebar-header-column">
              <div className="custom-sidebar-header">{headerContent}</div>
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <div className="custom-sidebar-body">{bodyContent}</div>
          </Grid.Row>
        </Grid>
      </Grid.Column>
    </Grid>
  );
}

CustomSideBar.prototype = {
  sideBarContent: PropTypes.node,
  headerContent: PropTypes.node,
  bodyContent: PropTypes.node,
};

CustomSideBar.defaultProps = {
  sideBarContent: '',
  headerContent: '',
  bodyContent: '',
};

export default CustomSideBar;
