import React from 'react'
import PropTypes from 'prop-types';
import { Form } from 'semantic-ui-react';

function BasicForm({
  checkForm,
  size,
  handleOnSubmit,
  children
}) {
  return (
    <Form
      size={size}
      onChange={checkForm}
      onSubmit={handleOnSubmit}
    >
      {children}
    </Form>
  )
}

BasicForm.prototype = {
  size: PropTypes.string,
  checkForm: PropTypes.func,
  handleOnSubmit: PropTypes.func,
  children: PropTypes.node,
}

BasicForm.defaultProps = {
  size: 'mini',
  checkForm: () => { },
  handleOnSubmit: () => { },
}

export default BasicForm;
