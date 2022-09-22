import React, { useEffect, useState } from 'react';
import  { useForm } from 'react-hook-form';
import PropTypes from 'prop-types';
import BasicModal from '../../atoms/basicModal/BasicModal';
import CreateAndUpdateBootcampForm from '../../organisms/createAndUpdateBootcampForm/CreateAndUpdateBootcampForm';
import BootcampUtils from '../../../utils/bootcampUtils/BootcampUtils';
import bootcampFields from '../../../data/bootcampForm/bootcampFields.json';
import StructureBootcampForm from '../../../data/bootcampForm/structure';

function CreateAndUpdateBootcampTemplate({
  handleClose,
  handleSave,
  openForm,
  workflowTypes,
  departments,
  projectTypes,
  currentBootcamp
}) {

  const [name, setName] = useState('');

  const getOption = [
    {propertyName: "deparment", name: "deparment", data: departments},
    {propertyName: "projectType", name: "projectType", data: projectTypes},
    {propertyName: "workflowType", name: "workflowType", data: workflowTypes}
  ]

  useEffect(() => {
    setName(currentBootcamp.name);
  }, [currentBootcamp.name])

  return (
    <BasicModal
      open={openForm}
      modalVariant={'modal-project'}
      classNameHeader={'modal-header-project'}
      variant={'modal-body-project'}
      header={currentBootcamp.name ? name : "New Bootcamp"}
      size="small"
      onClose={handleClose}
    >
      { currentBootcamp.name ?
        <CreateAndUpdateBootcampForm
          onClose={handleClose}
          onSubmit={handleSave}
          currentBootcamp={currentBootcamp}
          dropDownValues={getOption}
          fields={bootcampFields}
          structure={StructureBootcampForm}
          useForm={useForm}
        /> : <CreateAndUpdateBootcampForm
          onClose={handleClose}
          onSubmit={handleSave}
          currentBootcamp={BootcampUtils.emptyInstance()}
          dropDownValues={getOption}
          fields={bootcampFields}
          structure={StructureBootcampForm}
          useForm={useForm}
        />}
    </BasicModal >
  );
}

CreateAndUpdateBootcampTemplate.prototype = {
  handleClose: PropTypes.func,
  handleSave: PropTypes.func,
  openForm: PropTypes.bool,
  workflowTypes: PropTypes.array,
  departments: PropTypes.array,
  projectTypes: PropTypes.array,
  currentBootcamp: PropTypes.shape({
    id: PropTypes.string,
    name: PropTypes.string,
    description: PropTypes.string,
    startDate: PropTypes.string,
    endDate: PropTypes.string,
    workflowType: PropTypes.number,
    department: PropTypes.number,
    projectType: PropTypes.number
  })
}

CreateAndUpdateBootcampTemplate.defaultProps = {
  handleClose: () => { },
  handleSave: () => { },
  openForm: false,
  workflowTypes: [],
  departments: [],
  projectTypes: [],
  currentBootcamp: {
    id: '',
    name: '',
    description: '',
    startDate: '',
    endDate: '',
    workflowType: '',
    department: '',
    projectType: ''
  }
}

export default CreateAndUpdateBootcampTemplate;
