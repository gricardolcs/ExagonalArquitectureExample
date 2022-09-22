import React from 'react';
import PropTypes from 'prop-types';
import { Modal } from 'semantic-ui-react'

import './style.css';

function FormModal({
  displayCloseIcon,
  classNameHeader,
  variant,
  modalVariant,
  open,
  onClose,
  onOpen,
  size,
  header,
  showHeader,
  trigger,
  children
}) {
  return (
    <Modal
      className={modalVariant}
      closeIcon={displayCloseIcon && { name: 'close', className: 'custom' }}
      open={open}
      size={size}
      onClose={onClose}
      onOpen={onOpen}
      trigger={trigger}
    >
      {showHeader && <Modal.Header className={classNameHeader}>{header}</Modal.Header>}
      <Modal.Content
        className={variant}
      >
        {children}
      </Modal.Content>
    </Modal>
  )
}

FormModal.prototype = {
  open: PropTypes.bool,
  onClose: PropTypes.func,
  onOpen: PropTypes.func,
  size: PropTypes.string,
  header: PropTypes.string,
  showHeader: PropTypes.bool,
  children: PropTypes.node,
  displayCloseIcon: PropTypes.bool,
  classNameHeader: PropTypes.string,
  variant: PropTypes.string,
  modalVariant: PropTypes.string,
  trigger: PropTypes.node
}

FormModal.defaultProps = {
  open: false,
  onClose: () => { },
  onOpen: () => { },
  size: 'small',
  header: 'Welcome',
  showHeader: true,
  displayCloseIcon: false,
  classNameHeader: '',
  variant: '',
  modalVariant: ''
}

export default FormModal;
