import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import { action } from '@storybook/addon-actions';
import BootcampCard from './BootcampCard';

export default {
    title: 'Platform/Molecules/BootcampCard',
    component: BootcampCard,
}

const Template = ({ children, ...args }) => <BootcampCard {...args}>
    {children}
</BootcampCard>

export const bootcampCard = Template.bind({});
bootcampCard.args = {
    bootcamp: {
        "startDate": "2021-06-07",
        "endDate": "2021-12-07",
        "id": 1,
        "name": "Dev Bootcamp Bolivia",
        "description": "First Bolivian Dev Bootcamp Bolivia",
        "location": "Latam",
        "workflowType": 1,
        "workflow": null,
        "projectType": 1,
        "project": null,
        "departmentType": 1,
        "department": null
    },
    setComponent: action('Set Component')
}
