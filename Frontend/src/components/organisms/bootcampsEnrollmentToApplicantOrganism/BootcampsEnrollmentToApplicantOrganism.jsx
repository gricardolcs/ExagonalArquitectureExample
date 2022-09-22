import { useEffect, useState } from 'react';
import { Grid } from 'semantic-ui-react';
import PropTypes from 'prop-types';
import iconSearchAscending from '../../../images/icons/icon-sort-ascending.png';
import iconSearchDescending from '../../../images/icons/icon-sort-descending.png';
import TableMolecule from '../../molecules/table/TableMolecule';
import FormModal from '../../atoms/basicModal/BasicModal';
import BasicImage from '../../atoms/basicImage/BasicImage';
import TextInput from '../../atoms/textInput/TextInput';
import CustomButton from '../../atoms/customButton/CustomButton';
import BootcampItem from './BootcampItem';
import BootcampModalResult from '../../molecules/bootcampModalResult/BootcampModalResult';
import { registerApplicantInBootcamps } from '../../../api/applicant/registerApplicantInBootcamps/RegisterApplicantInBootcamps';
import { getBootcampsExceptBootcampsOfApplicant } from '../../../api/bootcamp/fetch/BootcampFetchExcludingApplicant';
import './style.css';

function BootcampsEnrollmentToApplicantOrganism({
  openBootcampsEnrollment = false,
  onOpenBootcampsEnrollment = () => {},
  updateData = () => {},
}) {
  const TITLE = 'Add to Project (s)';
  const [searchText, setSearchText] = useState('');
  const [result, setResult] = useState();
  const [bootcamps, setBootcamps] = useState([]);
  const [bootcampIdsSelected, setBootcampsIdsSelected] = useState([]);
  const [
    openBootcampsEnrollmentDetails,
    setOpenBootcampsEnrollmentDetails,
  ] = useState(false);

  useEffect(() => {
    if (openBootcampsEnrollment) {
      const applicantId = sessionStorage.getItem('applicantId');
      getBootcampsExceptBootcampsOfApplicant(applicantId, setBootcamps);
    }
  }, [openBootcampsEnrollment]);

  useEffect(() => {
    if (openBootcampsEnrollmentDetails) {
      updateData(true);
    }
  }, [openBootcampsEnrollmentDetails, updateData]);

  const renderProjectTypeCell = (bootcamp) => {
    return (
      <BootcampItem
        bootcamp={bootcamp}
        bootcampIdsSelected={bootcampIdsSelected}
        setBootcampIdsSelected={setBootcampsIdsSelected}
      />
    );
  };

  function sortProjectsBy(sort) {
    const applicantId = sessionStorage.getItem('applicantId');
    getBootcampsExceptBootcampsOfApplicant(
      applicantId,
      setBootcamps,
      searchText,
      sort
    );
  }

  const searchProjects = (text) => {
    setSearchText(text);
    const applicantId = sessionStorage.getItem('applicantId');
    getBootcampsExceptBootcampsOfApplicant(
      applicantId,
      setBootcamps,
      searchText
    );
  };

  function enrollApplicantToBootcamps() {
    const applicantId = sessionStorage.getItem('applicantId');
    registerApplicantInBootcamps(
      applicantId,
      bootcampIdsSelected,
      (response) => {
        setResult(
          bootcamps.filter((bootcamp) =>
            response.applicantRegisteredAtBootcamps.some(
              (id) => id === bootcamp.id
            )
          )
        );
        setOpenBootcampsEnrollmentDetails(true);
        closeModal();
      }
    );
  }

  const closeModal = () => {
    setBootcampsIdsSelected([]);
    onOpenBootcampsEnrollment(false);
  };

  return (
    <>
      <FormModal
        open={openBootcampsEnrollment}
        onOpen={onOpenBootcampsEnrollment}
        showHeader={false}
        classNameHeader='modal-header'
      >
        <Grid>
          <Grid.Row>
            <Grid.Column width={16}>
              <h3>{TITLE}</h3>
              <TextInput
                icon='search'
                className='search-projects'
                placeholder='Search Project ...'
                onChange={(text) => {
                  searchProjects(text, bootcamps);
                }}
              />
            </Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={16}>
              <TableMolecule
                textAlign='left'
                textAlignHeaders='left'
                cellVariant='project-table-header-cell'
                tableVariant='projects-table'
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
                          sortProjectsBy('bootcampLocation,ASC');
                        }}
                      />
                      <BasicImage
                        className='icon-sort-descending'
                        src={iconSearchDescending}
                        onClick={() => {
                          sortProjectsBy('bootcampLocation,DESC');
                        }}
                      />
                    </div>
                  </div>,
                  'Project type',
                ]}
                tableData={bootcamps.map((item) => {
                  item.renderSkills = renderProjectTypeCell(item);
                  return item;
                })}
                tableRowAttributes={['name', 'location', 'renderSkills']}
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
                onClick={enrollApplicantToBootcamps}
                variant='custom-button'
                label='Add'
                type='button'
                disabled={!bootcampIdsSelected.length}
                circular={false}
                color='blue'
              />
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </FormModal>
      {openBootcampsEnrollmentDetails && result && (
        <BootcampModalResult
          onClose={() => setOpenBootcampsEnrollmentDetails(false)}
          onOpen={() => setOpenBootcampsEnrollmentDetails(true)}
          open={openBootcampsEnrollmentDetails}
          handleCloseModal={() => setOpenBootcampsEnrollmentDetails(false)}
          result={{
            success: {
              value: result,
            },
          }}
          header={'Applicant was added to:'}
        />
      )}
    </>
  );
}

BootcampsEnrollmentToApplicantOrganism.prototype = {
  openBootcampsEnrollment: PropTypes.bool,
  onOpenBootcampsEnrollment: PropTypes.func,
  updateData: PropTypes.func,
};

export default BootcampsEnrollmentToApplicantOrganism;
