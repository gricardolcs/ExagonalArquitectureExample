import React from 'react'
import PropTypes from 'prop-types';
import { getStyle } from './StepNavigationUtils'
import Step from './Step'
import './style.css'

function CustomStepNavigation({
  workflowStageStatus,
  updateStep,
  scoreStatus,
}) {

  const qualification = scoreStatus;

  const getContractStatus = () => {
    if (qualification.length === 6) {
      return qualification[5].applicantQualification[0].selectedElement;  
    } else {
      return "";
    }
  }

  const labelsArray = Object.keys(workflowStageStatus);
  const labelsStatus = Object.values(workflowStageStatus);

  return (
    <div className="stepWrapper">
      {labelsArray.map((item, index)=> 
        <Step 
          key={index}
          index={index}
          label={item}
          labelsSize={labelsArray.length}
          updateStep={updateStep}
          state={labelsStatus[index]}
          lineColorStyle={getStyle(labelsStatus, index, qualification? getContractStatus(): "")}
          contractStatus={index === 5? getContractStatus(): ""}
        />
      )}
    </div>
  )
}

CustomStepNavigation.prototype = {
  workflowStageStatus: PropTypes.object,
  updateStep: PropTypes.func,
  scoreStatus: PropTypes.array
}

CustomStepNavigation.defaultProps = {
  workflowStageStatus: {},
  updateStep: () => {},
  scoreStatus: []
}

export default CustomStepNavigation