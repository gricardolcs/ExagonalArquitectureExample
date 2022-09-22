import React from "react";
import PropTypes from 'prop-types';
import CustomButton from '../../atoms/customButton/CustomButton';
import GetValueUtils from "../../../utils/getValue/getValueOrEmpty";

function ApplicantsTableActionButton({ arrayButtons, id, bootcampStage, idNextStage }) {
  const parameters = {id, bootcampStage, idNextStage}
  return (
    <div>
      {arrayButtons.map(item => {
        return (<CustomButton
          key={`button-${item.name}-${id}`}
          label={item.label}
          color={item.color}
          variant={GetValueUtils.getValueOrEmpty(item.variant)}
          onClick={item.onClick ? () => { item.onClick(parameters) } : () => { }}
        >
        </CustomButton>)
      })}
    </div >
  );
};

ApplicantsTableActionButton.prototype = {
  id: PropTypes.string,
  arrayButtons: PropTypes.arrayOf(
    PropTypes.shape({
      label: '',
      color: PropTypes.string,
      variant: PropTypes.string,
      onClick: PropTypes.func
    })
  ),
  bootcampStage: PropTypes.object,
  idNextStage: PropTypes.number
}

ApplicantsTableActionButton.defaultProps = {
  id: '',
  arrayButtons: [{
    label: '',
    color: '',
    variant: '',
    onClick: () => { }
  }],
  bootcampStage: {},
  idNextStage: 0
}

export default ApplicantsTableActionButton;
