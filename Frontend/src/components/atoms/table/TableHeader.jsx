import React from "react";
import PropTypes from 'prop-types';
import { Table } from 'semantic-ui-react'

//This class will be worked on ticket #48105, for its generic use
export const TableHeader = ({
  tableHeaders,
  textAlign,
  variant,
  cellVariant
}) => {
  return (
    <Table.Header className={variant}>
      <Table.Row>
        {tableHeaders.map((item, index) => {
          return (
            <Table.HeaderCell
              className={cellVariant}
              textAlign={(item === tableHeaders[0]) ? "left" : textAlign}
              key={`header-${index}`}>
              {item}
            </Table.HeaderCell>
          )
        })
        }
      </Table.Row>
    </Table.Header>
  )
}

TableHeader.prototype = {
  tableHeaders: PropTypes.array,
  textAlign: PropTypes.string,
  variant: PropTypes.string,
  cellVariant: PropTypes.string
}

TableHeader.defaultProps = {
  tableHeaders: [],
  textAlign: '',
  variant: '',
  cellVariant: ''
}
