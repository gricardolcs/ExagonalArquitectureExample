import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Menu } from 'semantic-ui-react';
import CustomButton from '../../atoms/customButton/CustomButton';
import './style.css';

function ApplicantMenu({ stages, filterAction }) {
  const [activeItem, setActiveItem] = useState('All');

  const handleItemClick = (e, { id }) => {
    setActiveItem(id);
    filterAction(id);
  };

  return (
    <div className='applicant-menu'>
      <div className='menu-title'>{'Stages:'}</div>
      <Menu secondary widths={stages.length + 1}>
        <Menu.Item key={'All'}>
          <CustomButton
            className='stage-button'
            id={'All'}
            label={'All'}
            onClick={handleItemClick}
            color={activeItem === 'All' ? 'yellow' : 'grey'}
            variant='buttonStyle stage-button'
            circular={false}
            fluid={true}
          />
        </Menu.Item>
        {stages.map((stage) => (
          <Menu.Item key={stage.name}>
            <CustomButton
              className='stage-button'
              id={stage.name}
              label={stage.name}
              onClick={handleItemClick}
              color={activeItem === stage.name ? 'yellow' : 'grey'}
              variant='buttonStyle stage-button'
              circular={false}
              fluid={true}
            />
          </Menu.Item>
        ))}
      </Menu>
    </div>
  );
}

export default ApplicantMenu;

ApplicantMenu.prototype = {
  stages: PropTypes.arr,
};

ApplicantMenu.defaultProps = {
  stages: [],
};
