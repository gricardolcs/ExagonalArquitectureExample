import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';
import ApplicantSearchAndFilterOrganism from './ApplicantSearchAndFilteringOrganism';

export default {
  title: 'Platform/Organisms/ApplicantSearchAndFilterOrganism',
  component: ApplicantSearchAndFilterOrganism,
}

const Template = ({ children, ...args }) => <ApplicantSearchAndFilterOrganism {...args}>
  {children}
</ApplicantSearchAndFilterOrganism>

export const applicantSearchAndFilterOrganism = Template.bind({});
applicantSearchAndFilterOrganism.args = {
    stages: [
        {
            id: -2,
            name: 'Initial'
        },
        {
            id: -3,
            name: 'Psychometric'
        },
        {
            id: -4,
            name: 'English'
        }
    ],
    handleOnClickOnStage: action('Handle On Click On Stage'),
    handleOnSearch: action('Handle On Search')
}
