import React from 'react';
import { Table } from 'semantic-ui-react';
import { doNothing } from '../../../utils/constants/constants';
import { TableBody } from '../../atoms/table/TableBody';
import { TableHeader } from '../../atoms/table/TableHeader';

const TableOrganism = ({
  headerData = [],
  bodyData = [],
  bodyFieldsData = [],
  onClickRow = doNothing,
  rowVariant = '',
}) => {
  return (
    <Table attached='top' basic='very'>
      <TableHeader tableHeaders={headerData} />
      <TableBody
        tableData={bodyData}
        tableRowAttributes={bodyFieldsData}
        onClickRow={onClickRow}
        rowVariant={rowVariant}
      />
    </Table>
  );
};

export default TableOrganism;
