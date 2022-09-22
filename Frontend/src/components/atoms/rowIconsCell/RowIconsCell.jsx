import React from 'react';
import PropTypes from 'prop-types';
import { Table } from 'semantic-ui-react';
import CustomIcon from '../customIcon/CustomIcon';

export const RowIconsCell = ({ actions = [] }) => {
  return (
    <Table.Cell>
      {actions.map((action) => (
        <CustomIcon
          key={action.key}
          name={action.name}
          color={action.color}
          onClick={action.onClick}
        />
      ))}
    </Table.Cell>
  );
};

RowIconsCell.prototype = {
  actions: PropTypes.array,
};
