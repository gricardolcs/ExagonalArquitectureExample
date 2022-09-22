import React from 'react'
import PropTypes from 'prop-types';
import BasicModal from '../../atoms/basicModal/BasicModal'
import CustomButton from '../../atoms/customButton/CustomButton'

import { Grid } from 'semantic-ui-react';

function DeleteModal({
  openModal,
  header,
  handleClose,
  size,
  onDelete,
  id
}) {
  
  function handleDelete() {
    onDelete(id);
    handleClose();
  }
  
  return (
    <BasicModal
      open={openModal}
      header={header}
      onClose={handleClose}
      size={size}
    >
      <Grid>
        <Grid.Row columns={2} textAlign="center">
              <Grid.Column textAlign="right">
                <CustomButton 
                  onClick={handleDelete}
                  label="Yes" 
                  color="blue" 
                  type="Submit"
                />
              </Grid.Column>

              <Grid.Column textAlign="left">
                <CustomButton 
                  label="No" 
                  onClick={handleClose}
                />
              </Grid.Column>
            </Grid.Row>
      </Grid>
    </BasicModal>
  )
}

DeleteModal.prototype = {
  openModal: PropTypes.func,
  header: PropTypes.string,
  handleClose: PropTypes.func,
  size: PropTypes.string,
  onDelete: PropTypes.func,
  id: PropTypes.string,
}

DeleteModal.defaultProps = {
  openModal: () => {},
  header: "",
  handleClose: () => {},
  size: "",
  onDelete: () => {},
  id: "",
}
export default DeleteModal
