import React, { useCallback, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Ref } from 'semantic-ui-react';
import { doNothing, errorMessages } from '../../../utils/constants/constants';
import CustomForm from '../../atoms/customForm/CustomForm';
import GetField from '../../../utils/formUtils/getField';
import { passwordValidation } from '../../../helpers/validation/fieldValidation';
import {
  PASSWORD,
  PASSWORD_CONFIRMATION,
} from '../../../utils/constants/formFields';
import './style.css';

const UserForm = ({
  onClose = doNothing,
  onSubmit = doNothing,
  currentUser = { status: 'active' },
  dropDownValues = [],
  fields = [],
  structure = [],
  useForm = doNothing,
}) => {
  const formRef = React.useRef(null);

  const {
    register,
    setValue,
    trigger,
    getValues,
    handleSubmit,
    formState: { errors, isValid },
  } = useForm({ defaultValues: currentUser });

  const statusValue = getValues('status');

  const handleOnSubmit = (data) => {
    onSubmit({
      ...data,
    });
  };

  const handleOnCancel = () => {
    onClose();
  };

  const validatePasswordConfirmation = useCallback(() => {
    passwordValidation({
      field: PASSWORD_CONFIRMATION,
      fieldToCompare: PASSWORD,
      errorMessage: errorMessages.confirmPasswordDoesNotMatch,
      getValues,
      register,
    });
  }, [getValues, register]);

  useEffect(() => {
    validatePasswordConfirmation();
  }, [validatePasswordConfirmation]);

  const getStatus = () => {
    return GetField.getElementsToDropdown(dropDownValues[0].data);
  };

  const options = {
    status: getStatus,
  };

  return (
    <>
      <Ref innerRef={formRef}>
        <CustomForm
          register={register}
          setValue={setValue}
          trigger={trigger}
          errors={errors}
          handleOnSubmit={handleSubmit(handleOnSubmit)}
          handleOnCancel={handleOnCancel}
          options={options}
          structure={structure}
          fields={fields}
          isValid={isValid}
          fieldsVariant={'user-form-fields'}
          buttonLabel={'Save'}
          defaultValues={currentUser}
          message={
            statusValue === 'INACTIVE' &&
            '* The user no longer be able to login to the application'
          }
        />
      </Ref>
    </>
  );
};

export default UserForm;

UserForm.prototype = {
  onClose: PropTypes.func,
  onSubmit: PropTypes.func,
  currentUser: PropTypes.object,
  dropDownValues: PropTypes.array,
  fields: PropTypes.array,
  structure: PropTypes.array,
  useForm: PropTypes.func,
};
