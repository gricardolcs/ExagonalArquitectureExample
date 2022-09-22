import { action } from '@storybook/addon-actions';
import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import ApplicantsEnrollmentToBootcampOrganism from './ApplicantsEnrollmentToBootcampOrganism';

export default {
    title: 'Platform/Organisms/ApplicantsEnrollmentToBootcampOrganism',
    component: ApplicantsEnrollmentToBootcampOrganism,
}

const Template = ({ children, ...args }) => <ApplicantsEnrollmentToBootcampOrganism {...args}>
    {children}
</ApplicantsEnrollmentToBootcampOrganism>

export const applicantsEnrollmentToBootcampOrganism = Template.bind({});
applicantsEnrollmentToBootcampOrganism.args = {
    openApplicantsEnrollment: true,
    onOpenApplicantsEnrollment: action('On Open Applicants Enrollment'),
    updateData: action('Update Data')
}