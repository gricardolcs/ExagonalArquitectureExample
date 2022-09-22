import { useEffect, useState } from 'react';
import { Grid } from 'semantic-ui-react';
import PropTypes from 'prop-types';
import iconSearchAscending from '../../../images/icons/icon-sort-ascending.png';
import iconSearchDescending from '../../../images/icons/icon-sort-descending.png';
import TableMolecule from '../../molecules/table/TableMolecule';
import ApplicantModalResult from '../../molecules/applicantModalResult/ApplicantModalResult';
import FormModal from '../../atoms/basicModal/BasicModal';
import BasicImage from '../../atoms/basicImage/BasicImage';
import TextInput from '../../atoms/textInput/TextInput';
import CustomButton from '../../atoms/customButton/CustomButton';
import { registerExistingApplicantsInBootcamp } from '../../../api/applicant/registerExistingApplicantsInBootcamp/RegisterExistingApplicantsInBootcamp';
import { getApplicantsExceptBootcampApplicants } from '../../../api/applicant/fetchAllApplicantExcludingABootcampApplicants/FetchAllApplicantExcludingABootcampApplicants';
import bootcampFindById from '../../../api/bootcamp/fetch/BootcampFindById';
import SkillItem from './SkillItem';
import './style.css';

function ApplicantsEnrollmentToBootcampOrganism({
  openApplicantsEnrollment,
  onOpenApplicantsEnrollment,
  updateData,
}) {
  const TITLE = 'Add Applicant(s)';
  const [searchText, setSearchText] = useState('');
  const [actualProject, setActualProject] = useState({});
  const [result, setResult] = useState();
  const [applicants, setApplicants] = useState([]);
  const [applicantIdsSelected, setApplicantIdsSelected] = useState([]);
  const [
    openApplicantsEnrollmentDetails,
    setOpenApplicantsEnrollmentDetails,
  ] = useState(false);

  const renderSkillsCell = ({ skills = [], id }) => {
    if (skills !== null && skills.length > 0) {
      const formattedSkills = (
        <div>{`${skills.splice(0, 3).join(', ')} ... ${skills.length} +`}</div>
      );
      return (
        <SkillItem
          skills={formattedSkills}
          currentApplicantId={id}
          applicantIdsSelected={applicantIdsSelected}
          setApplicantIdsSelected={setApplicantIdsSelected}
          message={skills.join(', ')}
        />
      );
    }
  };

  function sortApplicantsBy(sort) {
    const projectId = sessionStorage.getItem('currentProjectId');
    getApplicantsExceptBootcampApplicants(
      projectId,
      sort,
      searchText,
      (data) => {
        setApplicants(data);
      }
    );
  }

  const searchApplicants = (text) => {
    setSearchText(text);
    const projectId = sessionStorage.getItem('currentProjectId');
    setApplicants([]);
    getApplicantsExceptBootcampApplicants(projectId, '', text, (data) => {
      setApplicants(data);
    });
  };

  function enrollApplicantsInBootcamp() {
    const projectId = sessionStorage.getItem('currentProjectId');
    registerExistingApplicantsInBootcamp(
      projectId,
      applicantIdsSelected,
      (response) => {
        bootcampFindById(projectId, setActualProject);
        setResult(response);
        onOpenApplicantsEnrollment(false);
        setOpenApplicantsEnrollmentDetails(true);
        setApplicantIdsSelected([]);
      }
    );
  }

  function closeModal() {
    setApplicantIdsSelected([]);
    onOpenApplicantsEnrollment(false);
  }

  useEffect(() => {
    if (openApplicantsEnrollment) {
      const projectId = sessionStorage.getItem('currentProjectId');
      getApplicantsExceptBootcampApplicants(projectId, '', '', (data) => {
        setApplicants(data);
      });
    }
  }, [openApplicantsEnrollment]);

  useEffect(() => {
    return () => {
      if (openApplicantsEnrollmentDetails) {
        updateData(true);
      }
    };
  }, [openApplicantsEnrollmentDetails, updateData]);

  return (
    <>
      <FormModal
        open={openApplicantsEnrollment}
        onOpen={onOpenApplicantsEnrollment}
        showHeader={false}
        classNameHeader='modal-header'
      >
        <Grid>
          <Grid.Row>
            <Grid.Column width={16}>
              <h3>{TITLE}</h3>
              <TextInput
                icon='search'
                className='search-applicants'
                placeholder='Search Applicant ...'
                onChange={(text) => {
                  searchApplicants(text, applicants);
                }}
              />
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={16}>
              <TableMolecule
                textAlign='left'
                textAlignHeaders='left'
                cellVariant='applicant-table-header-cell'
                tableVariant='applicants-table'
                headerVariant='custom-head'
                bodyVariant='custom-body'
                rowVariant='custom-row'
                tableHeaders={[
                  'Name',
                  <div className='location-cell'>
                    Location
                    <div className='img-ascending-descending'>
                      <BasicImage
                        className='icon-sort-ascending'
                        src={iconSearchAscending}
                        onClick={() => {
                          sortApplicantsBy('location.country,ASC');
                        }}
                      />
                      <BasicImage
                        className='icon-sort-descending'
                        src={iconSearchDescending}
                        onClick={() => {
                          sortApplicantsBy('location.country,DESC');
                        }}
                      />
                    </div>
                  </div>,
                  <div style={{ display: 'flex', flexDirection: 'row' }}>
                    Skills
                    <div className='img-ascending-descending'>
                      <BasicImage
                        className='icon-sort-ascending'
                        src={iconSearchAscending}
                      />
                      <BasicImage
                        className='icon-sort-descending'
                        src={iconSearchDescending}
                      />
                    </div>
                  </div>,
                ]}
                tableData={applicants.map((item) => {
                  item.renderSkills = renderSkillsCell(item);
                  return item;
                })}
                tableRowAttributes={['fullName', 'country', 'renderSkills']}
              />
            </Grid.Column>
          </Grid.Row>
          <Grid.Row textAlign='right'>
            <Grid.Column width={16}>
              <CustomButton
                variant='custom-button'
                label='Cancel'
                type='button'
                onClick={closeModal}
                circular={false}
              />
              <CustomButton
                onClick={enrollApplicantsInBootcamp}
                variant='custom-button'
                label='Add'
                type='button'
                disabled={!applicantIdsSelected.length}
                circular={false}
                color='blue'
              />
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </FormModal>
      {openApplicantsEnrollmentDetails && result.registeredApplicants && (
        <ApplicantModalResult
          onClose={() => setOpenApplicantsEnrollmentDetails(false)}
          onOpen={() => setOpenApplicantsEnrollmentDetails(true)}
          open={openApplicantsEnrollmentDetails}
          handleCloseModal={() => setOpenApplicantsEnrollmentDetails(false)}
          projectName={actualProject.name}
          result={{
            success: {
              value: result.registeredApplicants.length,
              message: 'Applicants were added successfully',
            },
          }}
          header={TITLE}
        />
      )}
    </>
  );
}

ApplicantsEnrollmentToBootcampOrganism.prototype = {
  openApplicantsEnrollment: PropTypes.bool,
  onOpenApplicantsEnrollment: PropTypes.func,
  updateData: PropTypes.func,
};

ApplicantsEnrollmentToBootcampOrganism.defaultProps = {
  openApplicantsEnrollment: false,
  onOpenApplicantsEnrollment: () => {},
  updateData: () => {},
};

export default ApplicantsEnrollmentToBootcampOrganism;
