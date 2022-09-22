import React from 'react';
import photoDefault from '../../../images/icons/applicant-default-photo.png';
import 'semantic-ui-css/semantic.min.css';
import ApplicantInformationOrganism from './ApplicantInformationOrganism';

export default {
    title: 'Platform/Organisms/ApplicantInformationOrganism',
    component: ApplicantInformationOrganism,
}

const Template = ({ children, ...args }) => <ApplicantInformationOrganism {...args}>
    {children}
</ApplicantInformationOrganism>

export const applicantInformationOrganism = Template.bind({});
applicantInformationOrganism.args = {
    applicant: {
        id: '-4561233585',
        name: "Maria Orellana",
        email: "maria.orellana@gmail.com",
        photo: photoDefault,
        telephone: "76965489",
        birthday: "23/03/1995",
        age: "26 years",
        city: "Cochabamba",
        country: "Bolviia",
        career: "Ing. Sistemas",
        degree: "bachelor",
    },
    englishStageQualification: {
        stageId: 4,
        stageName: "English",
        comment: "Passed successfully",
        applicantQualification: [
            {
                label: "Reading",
                qualification: 56,
                type: "NUMERIC_INPUT"
            },
            {
                label: "Listening",
                qualification: 56,
                type: "NUMERIC_INPUT"
            },
            {
                label: "Speaking",
                qualification: 56,
                type: "NUMERIC_INPUT"
            },
            {
                label: "Writing",
                qualification: 56,
                type: "NUMERIC_INPUT"
            },
            {
                label: "Score(AVG):",
                qualification: 56,
                type: "NUMERIC_INPUT"
            }
        ],
        qualificationStatus: "PASSED",
        isEnglishType: true
    },
    statuses: [{id: 1, name: "NOT_STARTED"}, 
               {id: 2, name: "IN_PROGRESS"}, 
               {id: 3, name: "PASSED"}, 
               {id: 4, name: "FAILED"}, 
               {id: 5, name: "WITHDRAW"}],
    // eslint-disable-next-line no-undef
    handleOnClickPrevious: action('Handle On Click Previous'),
    previousLocationName: 'Go back'
}