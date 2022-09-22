import { Grid } from 'semantic-ui-react';
import PropTypes from 'prop-types';
import iconPlus from '../../../images/icons/icon-plus.png';
import iconMinus from '../../../images/icons/icon-minus.png';
import CustomPopup from '../../atoms/customPopup/CustomPopup';
import BasicImage from '../../atoms/basicImage/BasicImage';
import { doNothing } from '../../../utils/constants/constants';

const SkillItem = ({
  skills = <></>,
  currentApplicantId = 0,
  applicantIdsSelected = [],
  setApplicantIdsSelected = doNothing,
  message = '',
}) => {
  const addApplicant = (currentApplicantId, applicantIdsSelected) => {
    if (!applicantIdsSelected.includes(currentApplicantId)) {
      setApplicantIdsSelected([...applicantIdsSelected, currentApplicantId]);
    }
  };

  const removeApplicant = (currentApplicantId, applicantIdsSelected) => {
    const applicantIdsSelectedUpdated = applicantIdsSelected.filter(
      (applicantId) => applicantId !== currentApplicantId
    );
    setApplicantIdsSelected(applicantIdsSelectedUpdated);
  };

  return (
    <Grid className='skills-grid'>
      <Grid.Column width={12}>
        {skills.length <= 3 ? (
          skills.join(', ')
        ) : (
          <CustomPopup
            position='top'
            size='mini'
            color='black'
            messageVariant='skills-message'
            pointerVariant='skills-message-pointer'
            message={message}
            trigger={skills}
          />
        )}
      </Grid.Column>
      <Grid.Column width={4} className='skills-column'>
        {applicantIdsSelected.includes(currentApplicantId) ? (
          <BasicImage
            className='icon-minus'
            src={iconMinus}
            onClick={() =>
              removeApplicant(currentApplicantId, applicantIdsSelected)
            }
          />
        ) : (
          <BasicImage
            className='icon-plus'
            src={iconPlus}
            onClick={() =>
              addApplicant(currentApplicantId, applicantIdsSelected)
            }
          />
        )}
      </Grid.Column>
    </Grid>
  );
};

SkillItem.prototype = {
  skills: PropTypes.node,
  currentApplicantId: PropTypes.number,
  applicantIdsSelected: PropTypes.arrayOf(PropTypes.number),
  setApplicantIdsSelected: PropTypes.func,
  message: PropTypes.string,
};

export default SkillItem;
