import React from 'react'
import PropTypes from 'prop-types';
import DeleteBootcampModal from '../../organisms/deleteModal/DeleteModal'

function DeleteBootcampTemplate({
  handleClose,
  handleDelete,
  openModal,
  id
}) {
  return (
    <DeleteBootcampModal
      openModal={openModal}
      header="Do you want to delete the bootcamp?"
      onDelete={handleDelete}
      handleClose={handleClose}
      size="small"
      id={id}
    >
    </DeleteBootcampModal>
  )
}

DeleteBootcampTemplate.prototype = {
  handleClose: PropTypes.func,
  handleDelete: PropTypes.func,
  openModal: PropTypes.bool,
  id: PropTypes.string,
}
DeleteBootcampTemplate.defaultProps = {
  handleClose: () => {},
  handleDelete: () => {},
  openModal: false,
  id: '',
}

export default DeleteBootcampTemplate
