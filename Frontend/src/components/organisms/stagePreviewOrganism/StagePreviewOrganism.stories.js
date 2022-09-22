import React from 'react';
import { action } from '@storybook/addon-actions';
import 'semantic-ui-css/semantic.min.css';
import StagePreviewOrganism from './StagePreviewOrganism';

export default {
    title: 'Platform/Organisms/StagePreviewOrganism',
    component: StagePreviewOrganism
};

const Template = ({ children, ...args }) => <StagePreviewOrganism {...args}> {children} </StagePreviewOrganism>

export const stagePreviewOrganism = Template.bind({});
stagePreviewOrganism.args = {
    stage: {
        stageId: 1,
        stageName: "Initial",
        comment: "",
        appliedDate: "2021-11-24",
        submitDate: null,
        applicantQualification: [
            {
                label: "Score:",
                qualification: null,
                type: "NUMERIC_INPUT"
            }
        ],
        qualificationStatus: "IN_PROGRESS",
        isEnglishType: false
    },
    isTheLastStage: action('Is The Last Stage'),
    bootcampId: 1,
    nextStageId: 1,
    statuses: [{id: 1, name: "NOT_STARTED"}, 
               {id: 2, name: "IN_PROGRESS"}, 
               {id: 3, name: "PASSED"}, 
               {id: 4, name: "FAILED"}, 
               {id: 5, name: "WITHDRAW"}],
    edit: false,
    setEdit: action('Set Edit'),
    onSubmit: action('On Submit')
}

export const stagePreviewOrganismToEdit = Template.bind({});
stagePreviewOrganismToEdit.args = {
    stage: {
        stageId: 1,
        stageName: "Initial",
        comment: "Good knowledge on React Redux",
        appliedDate: "2021-11-24",
        submitDate: "2021-12-24",
        applicantQualification: [
            {
                label: "Score:",
                qualification: 97,
                type: "NUMERIC_INPUT"
            }
        ],
        qualificationStatus: "PASSED",
        isEnglishType: false
    },
    isTheLastStage: action('Is The Last Stage'),
    bootcampId: 1,
    nextStageId: 1,
    statuses: [{id: 1, name: "NOT_STARTED"}, 
               {id: 2, name: "IN_PROGRESS"}, 
               {id: 3, name: "PASSED"}, 
               {id: 4, name: "FAILED"}, 
               {id: 5, name: "WITHDRAW"}],
    edit: true,
    setEdit: action('Set Edit'),
    onSubmit: action('On Submit')
}
