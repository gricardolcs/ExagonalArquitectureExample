import React from 'react';
import CustomDragAndDrop from './CustomDragAndDrop'
import { action } from '@storybook/addon-actions'
import 'semantic-ui-css/semantic.min.css';


export default {
    title: 'Platform/Atoms/CustomDragAndDrop',
    component: CustomDragAndDrop
};

const Template = ({ children, ...args }) => <CustomDragAndDrop {...args}>{children}</CustomDragAndDrop>;

export const customDragAndDrop = Template.bind({});
customDragAndDrop.args = {
    onDropAccepted: () => { },
    onDropRejected: () => { },
    file: {},
    acceptedFiles: '.csv',
}
