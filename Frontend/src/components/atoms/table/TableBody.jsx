import React from 'react';
import PropTypes from 'prop-types';
import { Label, Table } from 'semantic-ui-react';
import { TableRow } from './TableRow';
import { doNothing } from '../../../utils/constants/constants';
import { RowIconsCell } from '../../atoms/rowIconsCell/RowIconsCell';
import { userStatusColors } from '../../../utils/constants/constants';
import './tableBodyStyle.css';

export const TableBody = ({
  tableData = [],
  tableRowAttributes = [],
  textAlign = 'left',
  bodyVariant = '',
  rowVariant = '',
  cellVariant = '',
  onClickRow = doNothing,
  actions = [],
}) => {
  const getTableCell = (item) => (field) => {
    return field === 'status' ? (
      <Table.Cell
        verticalAlign='middle'
        className={cellVariant}
        singleLine
        key={`cell-${field}`}
      >
        <div className='table-status-field'>
          <Label
            className='table-label-status'
            color={userStatusColors[item[field]]}
            size='tiny'
            circular
          ></Label>
          <div className='table-label-status-text'>
            {item[field].toLowerCase()}
          </div>
        </div>
      </Table.Cell>
    ) : (
      <Table.Cell
        className={cellVariant}
        singleLine
        key={`cell-${field}`}
        textAlign={field === tableRowAttributes[0] ? 'left' : textAlign}
        content={item[field]}
      />
    );
  };

  return (
    <Table.Body className={bodyVariant}>
      {tableData.map((item) => (
        <TableRow
          key={`row-${item.id}`}
          item={item}
          rowVariant={rowVariant}
          onClickRow={onClickRow}
        >
          {tableRowAttributes.map(getTableCell(item))}
          <RowIconsCell actions={actions} />
        </TableRow>
      ))}
    </Table.Body>
  );
};

TableBody.prototype = {
  tableData: PropTypes.array,
  tableRowAttributes: PropTypes.array,
  textAlign: PropTypes.string,
  handleEdit: PropTypes.func,
  handleDelete: PropTypes.func,
  bodyVariant: PropTypes.string,
  cellVariant: PropTypes.string,
  rowVariant: PropTypes.string,
  onClickRow: PropTypes.func,
  showEditAndDeleteOnOneCell: PropTypes.bool,
};
