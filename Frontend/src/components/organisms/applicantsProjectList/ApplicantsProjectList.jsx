import React, { useCallback, useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import PropTypes from 'prop-types';
import { CustomList } from '../customList/CustomList';
import ApplicantCard from '../../molecules/applicantCard/ApplicantCard';
import { Grid, Button } from 'semantic-ui-react';
import CustomButton from '../../atoms/customButton/CustomButton';
import CustomDropDown from '../../atoms/customDropDown/CustomDropDown';
import CustomIcon from '../../atoms/customIcon/CustomIcon';
import ApplicantMenu from '../../molecules/applicantMenu/ApplicantMenu';
import TextInput from '../../atoms/textInput/TextInput';
import applicantFilterByCriteriaAndproject from '../../../api/applicant/fetch/ApplicantFilterByCriteriaAndProject';
import ImportApplicantsOrganism from '../importApplicantsOrganism/ImportApplicantsOrganism';
import ApplicantsEnrollmentToBootcampOrganism from '../applicantsEnrollmentToBootcampOrganism/ApplicantsEnrollmentToBootcampOrganism';
import CreateAndUpdateApplicantForm from '../../organisms/createAndUpdateApplicantForm/CreateAndUpdateApplicantForm';
import createApplicant from '../../../api/applicant/createApplicant/CreateApplicant';
import { registerExistingApplicantsInBootcamp } from '../../../api/applicant/registerExistingApplicantsInBootcamp/RegisterExistingApplicantsInBootcamp';
import countryData from '../../../data/countries/countries-cities.json';
import { uploadCVFile } from '../../../api/applicant/upload/CurriculumVitaeFileUpload';
import BootcampsEnrollmentToApplicantOrganism from '../bootcampsEnrollmentToApplicantOrganism/BootcampsEnrollmentToApplicantOrganism';
import bootcampFindById from '../../../api/bootcamp/fetch/BootcampFindById';
import applicantFields from '../../../data/applicantForm/applicantFields.json';
import StructureApplicantForm from '../../../data/applicantForm/structure';
import { doNothing } from '../../../utils/constants/constants';
import './style.css';

export function ApplicantsProjectList({
  dataApplicants = [],
  updateDataApplicants = doNothing,
  projectStages = [],
  setComponent = doNothing,
}) {
  const [openApplicantsEnrollment, setOpenApplicantsEnrollment] = useState(
    false
  );
  const [openBootcampsEnrollment, setOpenBootcampsEnrollment] = useState(false);
  const [openAddApplicantScratch, setOpenAddApplicantScratch] = useState(false);
  const [openImportApplicantsFile, setOpenImportApplicantsFile] = useState(
    false
  );
  const [applicants, setApplicants] = useState([]);
  const [file, setFile] = useState({});

  const getCurrentLastStage = (stages) => {
    return stages.reduce((previousStage, currentStage) =>
      parseInt(previousStage.stageId) > parseInt(currentStage.stageId)
        ? previousStage
        : currentStage
    ).stageName;
  };

  const getOptions = [
    { propertyName: 'country', name: 'countries', data: countryData },
    {
      propertyName: 'city',
      name: 'cities',
      dependence: 'country',
      data: countryData,
    },
  ];

  const filterApplicantsByStage = (id) => {
    if (id === 'All') {
      setApplicants([...dataApplicants]);
    } else {
      setApplicants(
        dataApplicants.filter(
          (applicant) => getCurrentLastStage(applicant.stages) === id
        )
      );
    }
  };
  const createApplicantAndAddToBootcamp = useCallback(
    (applicant) => {
      const saveApplicantCurriculumVitae = (response) => {
        const newApplicantId = response.registeredApplicants[0];
        uploadCVFile(newApplicantId, file, updateDataApplicants);
      };
      const enrollApplicantsInCurrentBootcamp = (applicant) => {
        const { id: newApplicantId } = applicant;
        const projectId = sessionStorage.getItem('currentProjectId');
        registerExistingApplicantsInBootcamp(
          projectId,
          [newApplicantId],
          saveApplicantCurriculumVitae
        );
      };
      createApplicant(applicant, enrollApplicantsInCurrentBootcamp);
    },
    [updateDataApplicants, file]
  );

  const dropDownItems = [
    {
      text: 'Add Applicant',
      handleOnClick: () => {
        setOpenApplicantsEnrollment(true);
      },
    },
    {
      text: 'New Applicant',
      handleOnClick: () => {
        setOpenAddApplicantScratch(true);
      },
    },
    {
      text: 'Import from file',
      handleOnClick: () => setOpenImportApplicantsFile(true),
    },
  ];

  function renderCard(element) {
    return (
      <Grid.Column
        key={`row-${element.id}`}
        className='applicant-project-card-column'
      >
        {
          <ApplicantCard
            applicant={element}
            setComponent={setComponent}
            setOpenBootcampsEnrollment={setOpenBootcampsEnrollment}
          />
        }
      </Grid.Column>
    );
  }

  const addApplicantsButton = (
    <>
      <Button.Group color='blue'>
        <CustomButton
          label='+ Add'
          circular={false}
          color='blue'
          onClick={() => {
            setOpenApplicantsEnrollment(true);
          }}
        />
        <div className='dropdown-divider'></div>
        <CustomDropDown
          variant='button icon'
          icon='caret down'
          items={dropDownItems}
        />
      </Button.Group>
      <ImportApplicantsOrganism
        updateData={updateDataApplicants}
        onClose={() => setOpenImportApplicantsFile(false)}
        onOpen={() => setOpenImportApplicantsFile(true)}
        open={openImportApplicantsFile}
      />
      <CreateAndUpdateApplicantForm
        updateData={updateDataApplicants}
        onSubmit={createApplicantAndAddToBootcamp}
        onClose={() => setOpenAddApplicantScratch(false)}
        onOpen={() => setOpenAddApplicantScratch(true)}
        open={openAddApplicantScratch}
        dropDownValues={getOptions}
        fields={applicantFields}
        bootcampFindById={bootcampFindById}
        structure={StructureApplicantForm}
        useForm={useForm}
        setFile={setFile}
      />
    </>
  );

  const emptyData = (
    <div className='empty-data'>
      <div className='empty-data-icon'>
        <CustomIcon name='user outline' color='grey' size='huge' />
      </div>
      <div className='empty-data-message'>
        {'There are currently no applicants for this Project'}
      </div>
      <div className='empty-data-message'>{'for this Project'}</div>
    </div>
  );

  function toolBarContent(searchDataLogic) {
    return (
      <>
        <Grid.Column floated='left' width={16} className='tool-bar-content'>
          <div className='toolbar-message'>
            {
              'Here you will find the Applicants that are actually applying to the Project'
            }
          </div>
          <ApplicantMenu
            stages={projectStages}
            filterAction={filterApplicantsByStage}
          />
          <TextInput
            icon='search'
            placeholder='Search Applicant ...'
            className='textInputSearch'
            onChange={searchDataLogic}
          />
        </Grid.Column>
      </>
    );
  }

  useEffect(() => {
    setApplicants([...dataApplicants]);
  }, [dataApplicants]);

  return (
    <>
      <ApplicantsEnrollmentToBootcampOrganism
        openApplicantsEnrollment={openApplicantsEnrollment}
        onOpenApplicantsEnrollment={setOpenApplicantsEnrollment}
        updateData={updateDataApplicants}
      />
      <BootcampsEnrollmentToApplicantOrganism
        openBootcampsEnrollment={openBootcampsEnrollment}
        onOpenBootcampsEnrollment={setOpenBootcampsEnrollment}
        updateData={updateDataApplicants}
      />
      <CustomList
        data={applicants}
        title='Applicants'
        toolBarContent={toolBarContent}
        searchLogic={applicantFilterByCriteriaAndproject}
        addButton={addApplicantsButton}
        renderCard={renderCard}
        cardVariant='cards-table-applicants'
        tableVariant='custom-applicant-project-list'
        emptyData={emptyData}
      />
    </>
  );
}

ApplicantsProjectList.prototype = {
  dataApplicants: PropTypes.array,
  updateDataApplicants: PropTypes.func,
  projectStages: PropTypes.array,
  setComponent: PropTypes.func,
};
