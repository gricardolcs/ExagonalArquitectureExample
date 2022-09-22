import React from 'react';
import DeleteBootcampTemplate from './DeleteBootcampTemplate';
import { action } from '@storybook/addon-actions';
import 'semantic-ui-css/semantic.min.css';

export default {
    title: 'Platform/Templates/DeleteBootcampTemplate',
    component: DeleteBootcampTemplate,
}

const Template = ({ ...args}) => <DeleteBootcampTemplate {...args} />

export const deleteBootcampTemplate = Template.bind({});
deleteBootcampTemplate.args = {
    handleClose: action('handle close'),
    handleDelete: action('handle delete'),
    openModal: true,
    id: "-5123792"
}
