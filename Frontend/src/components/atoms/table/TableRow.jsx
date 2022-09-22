import React from 'react';
import PropTypes from 'prop-types';
import { Table } from 'semantic-ui-react';
import { doNothing } from '../../../utils/constants/constants';

export const TableRow = ({ item, rowVariant, children, onClickRow }) => {
  return (
    <Table.Row
      key={item.id}
      className={rowVariant}
      onClick={() => onClickRow(item)}
    >
      {children}
    </Table.Row>
  );
};

TableRow.prototype = {
  item: PropTypes.object,
  rowVariant: PropTypes.string,
  children: PropTypes.node,
};

TableRow.defaultProps = {
  item: {},
  rowVariant: '',
  onClickRow: doNothing,
};
