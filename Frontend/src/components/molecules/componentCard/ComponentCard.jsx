import React from 'react';
import PropTypes from 'prop-types';
import { CardHeader } from 'semantic-ui-react';
import BasicCard from '../../atoms/customCard/BasicCard';
import './style.css';

const ComponentCard = ({
  headerText = '',
  BodyComponent,
  variantCard = '',
  variantComponent = '',
}) => {
  return (
    <BasicCard className={`component-card ${variantCard}`}>
      <CardHeader className='component-header-card' textAlign={'left'}>
        <h3>{headerText}</h3>
      </CardHeader>
      <div className={`component-body ${variantComponent}`}>
        <BodyComponent />
      </div>
    </BasicCard>
  );
};

export default ComponentCard;

ComponentCard.prototype = {
  headerText: PropTypes.string,
  BodyComponent: PropTypes.string,
  variantCard: PropTypes.string,
  variantComponent: PropTypes.string,
};
