import React from 'react';
import PropTypes from 'prop-types';
import { Table } from 'semantic-ui-react';
import { TableHeader } from '../../atoms/table/TableHeader';
import { TableBody } from '../../atoms/table/TableBody';

function TableMolecule({
  tableHeaders = [],
  tableData = [],
  tableRowAttributes = [],
  textAlign = '',
  textAlignHeaders = '',
  basic = '',
  headerVariant = '',
  bodyVariant = '',
  cellVariant = '',
  tableVariant = '',
  rowVariant = '',
  tableRowActions = [],
}) {
  return (
    <Table basic={basic} className={tableVariant}>
      <TableHeader
        tableHeaders={tableHeaders}
        textAlign={textAlignHeaders}
        variant={headerVariant}
        cellVariant={cellVariant}
      />
      <TableBody
        bodyVariant={bodyVariant}
        cellVariant={cellVariant}
        rowVariant={rowVariant}
        tableData={tableData}
        tableRowAttributes={tableRowAttributes}
        textAlign={textAlign}
        actions={tableRowActions}
      />
    </Table>
  );
}

TableMolecule.prototype = {
  tableHeaders: PropTypes.array,
  tableData: PropTypes.array,
  tableRowAttributes: PropTypes.array,
  textAlign: PropTypes.string,
  textAlignHeaders: PropTypes.string,
  headerVariant: PropTypes.string,
  bodyVariant: PropTypes.string,
  cellVariant: PropTypes.string,
  tableVariant: PropTypes.string,
  rowVariant: PropTypes.string,
  tableRowActions: PropTypes.array,
};

export default TableMolecule;
