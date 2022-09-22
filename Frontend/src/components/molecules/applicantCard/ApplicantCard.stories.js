import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';
import ApplicantCard from './ApplicantCard';

export default {
    title: 'Platform/Molecules/ApplicantCard',
    component: ApplicantCard,
}

const Template = ({ children, ...args }) => <ApplicantCard {...args}>
    {children}
</ApplicantCard>

export const applicantCard = Template.bind({});
applicantCard.args = {
    applicant: {
        career: "Full Stack Web Developer",
        city: "La Paz",
        country: "",
        email: "adri.monasterios@gmail.com",
        fullName: "Adrian Monasterios Gutierrez",
        id: -4731505058866219000,
        telephone: "69956993"
    },
    setComponent: action('Set Component'),
}
