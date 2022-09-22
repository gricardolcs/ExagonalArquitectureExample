import React from "react";
import PropTypes from 'prop-types';
import { Table } from 'semantic-ui-react'

import { TableHeader } from '../../atoms/table/TableHeader'

export const TableCardOrganism = ({
  tableHeaders,
  textAlign,
  children
}) => {
  return (
    <Table celled striped>
      <TableHeader
        tableHeaders={tableHeaders}
        textAlign={textAlign}
      >
      </TableHeader>
      {children}
    </Table>
  )
}

TableCardOrganism.prototype = {
  tableHeaders: PropTypes.array,
  textAlign: PropTypes.string,
  children: PropTypes.node
}

TableCardOrganism.defaultProps = {
  tableHeaders: [],
  textAlign: '',
}
