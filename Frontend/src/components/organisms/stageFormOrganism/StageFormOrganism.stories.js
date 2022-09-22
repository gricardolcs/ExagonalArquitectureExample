import React from 'react';
import { action } from '@storybook/addon-actions';
import 'semantic-ui-css/semantic.min.css';
import StageFormOrganism from './StageFormOrganism';

export default {
  title: 'Platform/Organisms/StageFormOrganism',
  component: StageFormOrganism
};

const Template = ({ children, ...args }) => <StageFormOrganism { ...args }> {children} </StageFormOrganism>

export const EmptyStagePreview = Template.bind({});
EmptyStagePreview.args = {
  schema: [],
  appliedDate: '2021-10-15',
  submitDate: '2021-11-24',
  qualificationStatus: 'IN_PROGRESS',
  comment: 'this is a test',
  statuses: [{id: 1, name: "NOT_STARTED"}, 
             {id: 2, name: "IN_PROGRESS"}, 
             {id: 3, name: "PASSED"}, 
             {id: 4, name: "FAILED"}, 
             {id: 5, name: "WITHDRAW"}],
  onCancel: action('On Cancel'),
  onSubmit: action('On Submit'),
  onSubmitFileReport: action('on Submit Report')
}

export const InitialStagePreviewWithFilled = Template.bind({});
InitialStagePreviewWithFilled.args = {
  stageName: 'Initial',
  schema: [{
    type: 'NUMERIC_INPUT',
    label: 'Score',
    qualification: 97,
  }],
  appliedDate: '2020-10-15',
  submitDate: '2020-11-24',
  qualificationStatus: 'PASSED',
  comment: 'This is a comment for initial stage',
  statuses: [{id: 1, name: "NOT_STARTED"}, 
             {id: 2, name: "IN_PROGRESS"}, 
             {id: 3, name: "PASSED"}, 
             {id: 4, name: "FAILED"}, 
             {id: 5, name: "WITHDRAW"}],
  onCancel: action('On Cancel'),
  onSubmit: action('On Submit')
}

export const PyschometricStagePreviewFilled = Template.bind({});
PyschometricStagePreviewFilled.args = {
  stageName: 'Psychometric',
  schema: [ 
    {
      "label": "CA:",
      "qualification": 6,
      "type": "NUMERIC_INPUT"
    },
    {
      "label": "Mixed Control:",
      "qualification": 5,
      "type": "NUMERIC_INPUT"
    }
  ],
  appliedDate: '2020-10-15',
  submitDate: '2020-11-24',
  qualificationStatus: 'PASSED',
  comment: 'This is a comment for pyschometric stage',
  statuses: [{id: 1, name: "NOT_STARTED"}, 
             {id: 2, name: "IN_PROGRESS"}, 
             {id: 3, name: "PASSED"}, 
             {id: 4, name: "FAILED"}, 
             {id: 5, name: "WITHDRAW"}],
  onCancel: action('On Cancel'),
  onSubmit: action('On Submit')
}


export const DevTestStagePreviewFilled = Template.bind({});
DevTestStagePreviewFilled.args = {
  stageName:'Dev Test',
  schema: [ 
    {
      "label": "Score:",
      "qualification": 80,
      "type": "NUMERIC_INPUT"
    }
  ],
  appliedDate: '2020-10-15',
  submitDate: '2020-11-24',
  qualificationStatus: 'PASSED',
  comment: 'This is a comment for devetest stage',
  statuses: [{id: 1, name: "NOT_STARTED"}, 
             {id: 2, name: "IN_PROGRESS"}, 
             {id: 3, name: "PASSED"}, 
             {id: 4, name: "FAILED"}, 
             {id: 5, name: "WITHDRAW"}],
  onCancel: action('On Cancel'),
  onSubmit: action('On Submit')
}

export const EnglishStagePreviewFilled = Template.bind({});
EnglishStagePreviewFilled.args = {
  stageName:'English',
  schema: [
    {
      "label": "Reading",
      "qualification": 75,
      "type": "NUMERIC_INPUT"
    },
    {
      "label": "Listening",
      "qualification": 75,
      "type": "NUMERIC_INPUT"
    },
    {
      "label": "Speaking",
      "qualification": 75,
      "type": "NUMERIC_INPUT"
    },
    {
      "label": "Writing",
      "qualification": 75,
      "type": "NUMERIC_INPUT"
    },
    {
      "label": "Score(AVG):",
      "qualification": 75,
      "type": "NUMERIC_INPUT"
    }
  ],
  appliedDate: '2020-10-15',
  submitDate: '2020-11-24',
  comment: 'This is a comment for english stage',
  statuses: [{id: 1, name: "NOT_STARTED"}, 
             {id: 2, name: "IN_PROGRESS"}, 
             {id: 3, name: "PASSED"}, 
             {id: 4, name: "FAILED"}, 
             {id: 5, name: "WITHDRAW"}],
  onCancel: action('On Cancel'),
  onSubmit: action('On Submit')
}

export const ContractStagePreviewFilled = Template.bind({});
ContractStagePreviewFilled.args = {
  stageName:'Contract',
  schema: [ 
    {
      "label": "Status:",
      "elements": [
        "Signed",
        "Declined",
        "On Hold"
      ],
      "selectedElement": "Signed",
      "type": "DROPDOWN_INPUT"
    }
  ],
  appliedDate: '2020-10-15',
  submitDate: '2020-11-24',
  comment: 'This is a comment for contract stage',
  statuses: [{id: 1, name: "NOT_STARTED"}, 
             {id: 2, name: "IN_PROGRESS"}, 
             {id: 3, name: "PASSED"}, 
             {id: 4, name: "FAILED"}, 
             {id: 5, name: "WITHDRAW"}],
  onCancel: action('On Cancel'),
  onSubmit: action('On Submit')
}
