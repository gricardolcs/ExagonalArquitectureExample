import React, { useEffect } from 'react';
import PropTypes from 'prop-types';
import { Form, Grid } from 'semantic-ui-react';
import { buildErrors, getField } from '../../../utils/formUtils/getField';
import CustomButton from '../../atoms/customButton/CustomButton';
import { doNothing } from '../../../utils/constants/constants';
import './style.css';

/**
 * Component to build a form from a .JSON file and a structure.
 * @author Cristhian Canizares
 * @property {function} register - register a field and validations in the form hook
 * @property {function} setValue - allows to set a specific field with an entered or selected value
 * @property {function} trigger - manually trigger form or input validation
 * @property {object} errors - field errors
 * @property {function} checkForm - form validation
 * @property {string} size - text to define the size of the form
 * @property {function} handleOnSubmit - action to get form values
 * @property {function} handleOnCancel - action to cancel or exit the form
 * @property {string} buttonSubmit - allow define the button name
 * @property {object} options - used when you have a field of type Dropdown
 * @property {object} structure - structure of the form where the fields were entered
 * @property {array} fields - array of fields for the form
 * @property {node} children - additional components
 */
function CustomForm({
  register = doNothing,
  setValue = doNothing,
  trigger = doNothing,
  errors = {},
  checkForm = doNothing,
  size = 'mini',
  handleOnSubmit = doNothing,
  handleOnCancel = doNothing,
  buttonLabel = '',
  options = {},
  structure = {},
  fields = [],
  defaultValues = {},
  textAlignButtons = 'right',
  isValid = true,
  fieldsVariant = '',
  message = '',
}) {
  const formFields = fields.map((field) => {
    buildErrors(field);
    field.fieldErrors
      ? register(field.propertyName, ...field.fieldErrors)
      : register(field.propertyName, []);
    return getField(
      field.type,
      setValue,
      trigger,
      field,
      errors,
      options[field.options],
      defaultValues[field.propertyName],
      message
    );
  });

  useEffect(() => {
    for (let field in fields) {
      register(field.propertyName);
    }
  });

  return (
    <Form size={size} onChange={checkForm}>
      <div className={fieldsVariant}>{structure.setStructure(formFields)}</div>
      <Grid>
        <Grid.Row textAlign={textAlignButtons}>
          <Grid.Column>
            <CustomButton
              id={'cancel-btn'}
              label='Cancel'
              type='button'
              onClick={handleOnCancel}
              circular={false}
            />
            <CustomButton
              id={'submit-btn'}
              onClick={handleOnSubmit}
              label={buttonLabel}
              type='Submit'
              disabled={!isValid}
              circular={false}
              color='blue'
            />
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </Form>
  );
}

CustomForm.prototype = {
  register: PropTypes.func,
  setValue: PropTypes.func,
  trigger: PropTypes.func,
  errors: PropTypes.object,
  checkForm: PropTypes.func,
  size: PropTypes.string,
  handleOnSubmit: PropTypes.func,
  handleOnCancel: PropTypes.func,
  buttonLabel: PropTypes.string,
  options: PropTypes.object,
  structure: PropTypes.object,
  fields: PropTypes.array,
  defaultValues: PropTypes.object,
  textAlignButtons: PropTypes.string,
  isValid: PropTypes.bool,
  fieldsVariant: PropTypes.string,
};

export default CustomForm;
