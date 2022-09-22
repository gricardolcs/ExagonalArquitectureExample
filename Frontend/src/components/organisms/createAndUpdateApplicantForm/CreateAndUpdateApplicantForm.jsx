import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Ref } from 'semantic-ui-react';
import FormModal from '../../atoms/basicModal/BasicModal';
import ApplicantModalResult from '../../molecules/applicantModalResult/ApplicantModalResult';
import CustomForm from '../../atoms/customForm/CustomForm';
import GetField from '../../../utils/formUtils/getField';

function CreateAndUpdateForm({
  onClose = () => {},
  onOpen = () => {},
  open = false,
  onSubmit = () => {},
  currentApplicant = {
    id: '',
    name: '',
    lastName: '',
    email: '',
    celNumber: '',
    country: '',
    city: '',
    university: '',
    degree: '',
    birthday: '',
    career: '',
    curriculumVitae: '',
    fileNameCV: '',
  },
  bootcampFindById,
  dropDownValues = [],
  fields = [],
  structure = [],
  useForm = () => {},
  setFile = () => {},
}) {
  const formRef = React.useRef(null);
  //TODO:(jhonatan.sanchez) Fix to show modal when applicant is created
  // eslint-disable-next-line
  const [addResult, setAddResult] = useState({});
  const [openResultModal, setOpenResultModal] = useState(false);
  const [actualProject, setActualProject] = useState({});

  const {
    register,
    setValue,
    trigger,
    getValues,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const handleOnSubmit = (data) => {
    const newApplicant = {
      fullName: `${data.name} ${data.lastName}`,
      birthday: data.birthday,
      email: data.email,
      telephone: data.telephone,
      city: data.city,
      country: data.country,
      career: data.career,
      degree: data.degree,
      photo: data.profileImage,
    };
    setFile(data.curriculumVitae);
    onSubmit(newApplicant);
    const projectId = sessionStorage.getItem('currentProjectId');
    bootcampFindById(projectId, setActualProject);
    onClose();
  };

  const handleOnCancel = () => {
    onClose();
  };

  const getCountries = () => {
    return GetField.getOptions(dropDownValues[0].data);
  };

  const getCities = () => {
    const country = getValues('country');
    return country
      ? GetField.getElementsToDropdown(dropDownValues[1].data[country])
      : [];
  };

  const options = {
    countries: getCountries,
    cities: getCities,
  };

  return (
    <>
      <FormModal
        onClose={onClose}
        onOpen={onOpen}
        open={open}
        header={'Add new Applicant'}
        classNameHeader={'headerText'}
      >
        <Ref innerRef={formRef}>
          <CustomForm
            register={register}
            setValue={setValue}
            trigger={trigger}
            errors={errors}
            handleOnSubmit={handleSubmit(handleOnSubmit)}
            handleOnCancel={handleOnCancel}
            buttonLabel='Save'
            options={options}
            structure={structure}
            fields={fields}
          />
        </Ref>
      </FormModal>
      {addResult && (
        <ApplicantModalResult
          onClose={() => setOpenResultModal(false)}
          onOpen={() => setOpenResultModal(true)}
          open={openResultModal}
          handleCloseModal={() => setOpenResultModal(false)}
          projectName={actualProject.name}
          result={{
            success: {
              value: addResult.length,
              message: 'Applicant was created and added succesfuly',
              render: '',
            },
          }}
          header={'New Applicant'}
          className={'headerText'}
        />
      )}
    </>
  );
}

CreateAndUpdateForm.prototype = {
  onClose: PropTypes.func,
  onOpen: PropTypes.func,
  open: PropTypes.bool,
  onSubmit: PropTypes.func,
  currentApplicant: PropTypes.shape({
    id: PropTypes.string,
    name: PropTypes.string,
    lastName: PropTypes.string,
    email: PropTypes.string,
    celNumber: PropTypes.number,
    country: PropTypes.string,
    city: PropTypes.string,
    university: PropTypes.string,
    degree: PropTypes.string,
    birthday: PropTypes.string,
    career: PropTypes.number,
    curriculumVitae: PropTypes.string,
    fileNameCV: PropTypes.string,
  }),
  bootcampFindById: PropTypes.func,
  dropDownValues: PropTypes.object,
  fields: PropTypes.array,
  structure: PropTypes.object,
  useForm: PropTypes.func,
  setFile: PropTypes.func,
};

export default CreateAndUpdateForm;
