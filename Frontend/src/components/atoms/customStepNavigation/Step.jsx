import React from 'react'
import PropTypes from 'prop-types';
import { getStyleCircle, getStepsPadding } from './StepNavigationUtils'
import './style.css'

function Step({
  index,
  label,
  labelsSize,
  updateStep,
  state,
  lineColorStyle,
  contractStatus
}) {

  const handleOnSelectedColor = (index) => {
    if(state) {
      return updateStep(index);
    }
  }

  return (
      <div className={`stepBlock ${contractStatus? contractStatus.replace(/\s+/g, ''): lineColorStyle}`}>
        <div className={`${(getStepsPadding(labelsSize))} circleWrapper`}>
          <div className={`circleStep ${(getStyleCircle(state, contractStatus))}`} onClick={() => handleOnSelectedColor(index + 1)}>
          </div>
        </div>
      <span>{label}</span>
      </div>
  )
}

Step.proptype = {
  index: PropTypes.number,
  label: PropTypes.string,
  labelsSize: PropTypes.number,
  updateStep: PropTypes.func,
  state: PropTypes.array,
  lineColorStyle: PropTypes.string,
  contractStatus: PropTypes.string
}

Step.defaultProps = {
  index: 0,
  label: '',
  labelsSize: 0,
  updateStep: () => {},
  state: [],
  lineColorStyle: '',
  contractStatus: ''
}

export default Step