import React, { useEffect, useState, useCallback } from 'react';
import PropTypes from 'prop-types';
import { Ref } from 'semantic-ui-react';
import CustomForm from '../../atoms/customForm/CustomForm';
import GetField from '../../../utils/formUtils/getField';
import './style.css';

function CreateAndUpdateBootcampForm({
  onClose = () => {},
  onSubmit = () => {},
  currentBootcamp = {
    id: '',
    name: '',
    description: '',
    location: '',
    startDate: '',
    endDate: '',
    workflowType: '',
    department: '',
    projectType: '',
  },
  dropDownValues = [],
  fields = [],
  structure = [],
  useForm = () => {},
}) {
  const formRef = React.useRef(null);
  const [optionValues, setOptionValues] = useState([]);

  const {
    register,
    setValue,
    trigger,
    getValues,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const handleOnSubmit = (data) => {
    onSubmit(data);
    onClose();
  };

  const handleOnCancel = () => {
    onClose();
  };

  const options = {
    departments: optionValues[0],
    projects: optionValues[1],
    workflows: optionValues[2],
  };

  const startDate = getValues('startDate');

  const validateEndDate = useCallback(() => {
    register('endDate', {
      validate: {
        comparation: (value) =>
          value > startDate ||
          'The final date must be higher than the start date.',
      },
    });
  }, [register, startDate]);

  const buildDropdownOption = useCallback(() => {
    const values = dropDownValues.map((element, index) => {
      return GetField.getElementsToDropdown(element.data);
    });
    setOptionValues(values);
  }, [dropDownValues]);

  useEffect(() => {
    buildDropdownOption();
    validateEndDate();
  }, [buildDropdownOption, validateEndDate]);

  return (
    <Ref innerRef={formRef}>
      <CustomForm
        register={register}
        setValue={setValue}
        trigger={trigger}
        errors={errors}
        handleOnSubmit={handleSubmit(handleOnSubmit)}
        handleOnCancel={handleOnCancel}
        buttonLabel="Save"
        options={options}
        structure={structure}
        fields={fields}
      />
    </Ref>
  );
}

CreateAndUpdateBootcampForm.prototype = {
  onClose: PropTypes.func,
  onSubmit: PropTypes.func,
  currentBootcamp: PropTypes.shape({
    id: PropTypes.string,
    name: PropTypes.string,
    description: PropTypes.string,
    location: PropTypes.string,
    startDate: PropTypes.string,
    endDate: PropTypes.string,
    workflowType: PropTypes.number,
    department: PropTypes.number,
    projectType: PropTypes.number,
  }),
  dropDownValues: PropTypes.array,
  fields: PropTypes.array,
  structure: PropTypes.array,
  useForm: PropTypes.func,
};

export default CreateAndUpdateBootcampForm;
