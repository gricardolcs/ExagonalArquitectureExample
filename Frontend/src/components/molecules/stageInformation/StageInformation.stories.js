import React from 'react';
import 'semantic-ui-css/semantic.min.css';
import StageInformation from './StageInformation';
import { action } from '@storybook/addon-actions';

export default {
  title: 'Platform/Molecules/StageInformation',
  component: StageInformation
};

const Template = ({ children, ...args }) => <StageInformation {...args}> {children} </StageInformation>

export const InitialStageInformation = Template.bind({});
InitialStageInformation.args = {
  stage: {
    "stageId": 1,
    "stageName": "Initial",
    "comment": "Passed successfully",
    "applicantQualification": [
      {
        "label": "Score:",
        "qualification": 5,
        "type": "NUMERIC_INPUT"
      }
    ],
    "appliedDate": "2021-06-07",
    "submitDate": "2021-12-07",
    "qualificationStatus": "PASSED",
    "isEnglishType": false
  },
  handleOnFile: action('Handle On File')
};

export const PsychometricStageInformation = Template.bind({});
PsychometricStageInformation.args = {
  stage: {
    "stageId": 2,
    "stageName": "Psychometric",
    "comment": "Passed successfully",
    "applicantQualification": [
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
    "appliedDate": "2021-06-07",
    "submitDate": "2021-12-07",
    "qualificationStatus": "PASSED",
    "isEnglishType": false
  },
  handleOnFile: action('Handle On File')
};

export const DevTestStageInformation = Template.bind({});
DevTestStageInformation.args = {
  stage: {
    "stageId": 3,
    "stageName": "Dev Test",
    "comment": "This is a dev test comment",
    "appliedDate": "2021-12-09",
    "submitDate": "2021-12-20",
    "applicantQualification": [
      {
        "label": "Score:",
        "qualification": 80,
        "type": "NUMERIC_INPUT"
      }
    ],
    "qualificationStatus": "IN_PROGRESS",
    "isEnglishType": false
  }
};

export const ContractStageInformation = Template.bind({});
ContractStageInformation.args = {
  stage: {
    "stageId": 6,
    "stageName": "Contract",
    "comment": "This is a comment for contract stage",
    "appliedDate": "2022-01-01",
    "submitDate": "2021-12-09",
    "applicantQualification": [
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
    "qualificationStatus": "IN_PROGRESS",
    "isEnglishType": false
  },
  handleOnFile: action('Handle On File')
};