import React from "react";
import PropTypes from 'prop-types';
import { Table } from 'semantic-ui-react'

import { TableRow } from '../../atoms/table/TableRow'
import CustomIcon from '../../atoms/customIcon/CustomIcon';
import { CustomCard } from '../customCard/CustomCard';

export function TableBodyCard({
  tableData,
  tableRowAttributes,
  textAlign,
  cardImage,
  cardImageSize,
  handleEdit,
  handleDelete,
  handleNavigate
}) {

  const editCell = (item) => {
    if (handleEdit) {
      return (
        <Table.Cell textAlign={textAlign}>
          <CustomIcon
            name="pencil alternate"
            onClick={() => handleEdit(item)} />
        </Table.Cell>
      )
    }
  }

  const deleteCell = (item) => {
    if (handleDelete) {
      return (
        <Table.Cell textAlign={textAlign}>
          <CustomIcon
            name='trash alternate outline'
            color='red'
            onClick={() => handleDelete(item)} />
        </Table.Cell>
      )
    }
  }

  return (
    <Table.Body>
      {
        tableData.map(item =>
          <TableRow key={`row-${item.id}`} item={item}>
            {
              tableRowAttributes.map((field, index) => {
                if (index === 0) {
                  return (
                    <Table.Cell key={`cell-${item.id}-${field}`} textAlign={textAlign}>
                      <CustomCard
                        cardImage={cardImage}
                        cardImageSize={cardImageSize}
                        cardHeader={item[field]}
                        item={item}
                        handleNavigate={handleNavigate}
                      >
                      </CustomCard>
                    </Table.Cell>)
                }
                return (
                  <Table.Cell
                    key={`cell-${item.id}-${field}`}
                    textAlign={textAlign}
                    content={item[field]}>
                  </Table.Cell>)
              })
            }
            {editCell(item)}
            {deleteCell(item)}
          </TableRow>
        )
      }

    </Table.Body>
  )
}

TableBodyCard.prototype = {
  tableData: PropTypes.array,
  tableRowAttributes: PropTypes.array,
  textAlign: PropTypes.string,
  cardImage: PropTypes.string,
  cardImageSize: PropTypes.string,
  handleEdit: PropTypes.func,
  handleDelete: PropTypes.func,
  handleNavigate: PropTypes.func,
}

TableBodyCard.defaultProps = {
  tableData: [],
  tableRowAttributes: [],
  textAlign: '',
  cardImageSize: 'small',
  cardImage: '',
  handleEdit: () => { },
  handleDelete: () => { },
  handleNavigate: () => { }
}
