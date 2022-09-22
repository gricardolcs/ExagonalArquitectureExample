import React from "react";
import ActionIcons from '../../molecules/actionIcons/ActionIcons'

function ApplicantsActionsTemplate() {

  const arrayIcons = [
    {
      name: 'plus circle',
      size: 'large'
    }
  ]

  return (
    <ActionIcons
      arrayIcons={arrayIcons}
    >
    </ActionIcons>
  );
};

export default ApplicantsActionsTemplate;
