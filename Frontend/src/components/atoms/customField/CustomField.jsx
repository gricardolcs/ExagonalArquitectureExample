import PropTypes from 'prop-types';
import { Form } from 'semantic-ui-react';

function CustomField({
  variant = '',
  width = '',
  disabled = false,
  children = <></>,
}) {
  return (
    <Form.Field className={variant} width={width} disabled={disabled}>
      {children}
    </Form.Field>
  );
}

CustomField.prototype = {
  variant: PropTypes.string,
  width: PropTypes.oneOfType([PropTypes.number, PropTypes.string]),
  disabled: PropTypes.bool,
  children: PropTypes.node,
};

export default CustomField;
