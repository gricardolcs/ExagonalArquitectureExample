import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { CustomList } from '../customList/CustomList';
import ApplicantCard from '../../molecules/applicantCard/ApplicantCard';
import { Grid } from 'semantic-ui-react';
import applicantFilterByCriteria from '../../../api/applicant/fetch/ApplicantFilterByCriteria';
import BasicImage from '../../atoms/basicImage/BasicImage';
import TextInput from '../../atoms/textInput/TextInput';
import iconRow from '../../../images/icons/icon-rows.png';
import iconGrid from '../../../images/icons/icon-grid.png';
import iconDownload from '../../../images/icons/icon-download-menu.png';
import DropDownInput from '../../atoms/dropDownInput/DropDownInput';
import BootcampsEnrollmentToApplicantOrganism from '../bootcampsEnrollmentToApplicantOrganism/BootcampsEnrollmentToApplicantOrganism';
import './style.css';

export function ApplicantList({ data = [], setComponent = () => {} }) {
  const [openBootcampsEnrollment, setOpenBootcampsEnrollment] = useState(false);
  function toolBarContent(searchDataLogic) {
    return (
      <>
        <Grid.Column width={5} className='optionSection'>
          <TextInput
            label={
              <DropDownInput
                placeholder='All Projects'
                options={[
                  {
                    key: 'All Projects',
                    text: 'All Projects',
                    value: 'All Projects',
                  },
                ]}
                className='textInputDropdown'
              />
            }
            icon='search'
            placeholder='Search ...'
            className='textInputSearch'
            onChange={searchDataLogic}
          />
        </Grid.Column>
        <Grid.Column floated='right' width={5} className='optionSection'>
          <BasicImage src={iconRow} size='mini' className='sizeIconRow' />
          <BasicImage src={iconGrid} size='mini' className='sizeIconGrid' />
          <BasicImage
            src={iconDownload}
            size='mini'
            className='sizeIconDownload'
          />
        </Grid.Column>
      </>
    );
  }

  function renderCard(element) {
    return (
      <Grid.Column key={`row-${element.id}`} className='applicant-card-column'>
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

  return (
    <>
      <BootcampsEnrollmentToApplicantOrganism
        openBootcampsEnrollment={openBootcampsEnrollment}
        onOpenBootcampsEnrollment={setOpenBootcampsEnrollment}
      />
      <CustomList
        title='Applicants'
        data={data}
        toolBarContent={toolBarContent}
        searchLogic={applicantFilterByCriteria}
        renderCard={renderCard}
        tableVariant='custom-applicant-list'
      />
    </>
  );
}

ApplicantList.prototype = {
  data: PropTypes.array,
  setComponent: PropTypes.func,
};
