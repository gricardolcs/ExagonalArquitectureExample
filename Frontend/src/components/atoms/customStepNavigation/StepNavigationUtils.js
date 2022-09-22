import EnumQualificationStatus from "../../organisms/stagePreviewOrganism/EnumQualificationStatus";

export function getStyle(labelsStatus, index, contractStatus) {
  let styleLine = '';
  if(labelsStatus[index] === EnumQualificationStatus.PASSED
    || labelsStatus[index] === EnumQualificationStatus.QUALIFIED_AND_READONLY) {
    if(labelsStatus[index+1] === EnumQualificationStatus.IN_PROGRESS) {
      if(index === 4 && contractStatus === "On Hold") {
        styleLine = 'onHold';
      } else {
        styleLine = 'progressLine';
      }
    }
    if(labelsStatus[index+1] === EnumQualificationStatus.PASSED
      || labelsStatus[index+1] === EnumQualificationStatus.FAILED
      || labelsStatus[index+1] === EnumQualificationStatus.QUALIFIED_AND_READONLY) { 
      styleLine = 'qualifiedLine';
    }
    if(labelsStatus[index+1] === EnumQualificationStatus.FAILED) {
      styleLine = 'failedLine';
    }
    if(labelsStatus[index+1] === EnumQualificationStatus.ON_HOLD) {
      styleLine = 'onHoldLine';
    }
  }
  if (labelsStatus[index+1] === EnumQualificationStatus.WITHDRAW) {
    styleLine = 'onHold';  
  }
  return styleLine;
}

export function getStyleCircle(state, contractStatus){
  let styleColor = "circleNotColor"
  switch (state) {
    case EnumQualificationStatus.PASSED:
      styleColor = "circleQualified"
      break;
    case EnumQualificationStatus.IN_PROGRESS:
      if (contractStatus === "On Hold") {
        styleColor = "circleOnHold"  
      } else {
        styleColor = "circleInProgress"
      }
      break;
    case EnumQualificationStatus.FAILED:
      styleColor = "circleDisqualified"
      break;
    case EnumQualificationStatus.QUALIFIED_AND_READONLY:
      styleColor = "circleQualified"
      break;
    case EnumQualificationStatus.WITHDRAW:
      styleColor = "circleOnHold"
      break;
    case EnumQualificationStatus.ON_HOLD:
      styleColor = "circlePurple"
      break;
    default:
      styleColor = "without-pointer"
      break;
  }
  return styleColor
}

export function getStepsPadding(labelsSizes) {
  let stepsPadding = 'paddingSteps';
  if( labelsSizes > 6 ){
    if( labelsSizes < 10 ){
      stepsPadding = 'paddingStepsMedium';
    }
    if( labelsSizes > 10 ){
      stepsPadding = 'paddingStepsLarge';
    }
  }
  return stepsPadding;
}

const StepNavigationUtils = {
  getStyle,
  getStyleCircle,
  getStepsPadding
}

export default StepNavigationUtils
