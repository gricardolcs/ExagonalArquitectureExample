import React from "react";
import DeleteModal from './DeleteModal';
import { action } from '@storybook/addon-actions'
import 'semantic-ui-css/semantic.min.css';

export default {
    title: 'Platform/Organisms/DeleteModal',
    component: DeleteModal,
};

const Template = ({...args}) => <DeleteModal {...args}/>;

export const modal = Template.bind({});
modal.args = {
    openModal: true,
    header:"Do you want to delete a bootcamp?",
    handleClose: action('handle close'),
    size:"small",
    onDelete: action('delete'),
    id: '-546851512'
};
