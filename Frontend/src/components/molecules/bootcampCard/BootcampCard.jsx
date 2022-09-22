import React, { useCallback } from 'react';
import BasicImage from '../../atoms/basicImage/BasicImage';
import convertDateFormat from '../../../utils/dateConvertFormatUtils/DateConvertUtils';
import { CustomCard } from '../customCard/CustomCard';
import PropTypes from 'prop-types';
import bootcampCandidatesNumber from '../../../images/icons/icon-user-simple.png';
import bootcampImageDev from '../../../images/logos/logo-dev.png';
import bootcampImageAuto from '../../../images/logos/logo-auto.png';
import bootcampImageDevops from '../../../images/logos/logo-devops.png';
import bootcampImageTest from '../../../images/logos/logo-test.png';
import {
  SESSION_COMPONENT,
  START,
  FINISH,
  EDIT,
  DELETE,
} from '../../../utils/constants/constants';
import { BootcampsPaths } from '../../../enum/availableRoutes';
import { doNothing } from '../../../utils/constants/constants';
import './style.css';

export function BootcampCard({
  bootcamp,
  setComponent,
  handleDelete,
  handleEdit,
}) {
  const handleOnClickCard = useCallback(() => {
    sessionStorage.setItem('currentProjectId', bootcamp.id);
    sessionStorage.setItem(SESSION_COMPONENT, BootcampsPaths.PROFILE);
    setComponent(BootcampsPaths.PROFILE);
  }, [bootcamp, setComponent]);

  const getBootcampImage = (item) => {
    const department = item.department?.name;

    if (!department) return bootcampImageDev;

    let img = null;

    switch (department.toLowerCase()) {
      case 'development':
        img = bootcampImageDev;
        break;

      case 'qa':
        img = bootcampImageTest;
        break;

      case 'devops':
        img = bootcampImageDevops;
        break;
      case 'automation':
        img = bootcampImageAuto;
        break;
      default:
        img = bootcampImageDev;
        break;
    }

    return img;
  };

  function renderBootcampImage(item) {
    return (
      <div className="circle">
        <BasicImage src={getBootcampImage(item)} className="logo" />
      </div>
    );
  }

  return (
    <CustomCard
      cardImage={renderBootcampImage(bootcamp)}
      onClickCard={handleOnClickCard}
      cardHeader={`${bootcamp.name}`}
      cardMeta={bootcamp.description}
      cardDescription={[
        {
          key: 'bootcamp-dates',
          render: `${convertDateFormat.convert(
            bootcamp['startDate']
          )} thru ${convertDateFormat.convert(bootcamp['endDate'])}`,
        },
        {
          key: 'bootcamp-candidates-number',
          render: (
            <>
              <BasicImage
                className="bootcamp-icon"
                src={bootcampCandidatesNumber}
                size="mini"
              />
              {bootcamp.acceptedParticipants}/
              {bootcamp.participantsExpectedQuantity}
            </>
          ),
        },
      ]}
      dropDownOptions={[
        { text: START },
        { text: FINISH },
        { text: EDIT, handleOnClick: () => handleEdit(bootcamp) },
        { text: DELETE, handleOnClick: () => handleDelete(bootcamp) },
      ]}
      tabColorVariant="bootcamp-card"
      showStatus
    />
  );
}

BootcampCard.prototype = {
  bootcamp: PropTypes.object,
  setComponent: PropTypes.func,
  handleDelete: PropTypes.func,
  handleEdit: PropTypes.func,
};

BootcampCard.defaultProps = {
  bootcamp: {},
  setComponent: doNothing,
  handleDelete: doNothing,
  handleEdit: doNothing,
};

export default BootcampCard;
