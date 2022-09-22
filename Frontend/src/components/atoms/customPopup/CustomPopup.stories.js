import React from 'react';
import { action } from '@storybook/addon-actions';

import 'semantic-ui-css/semantic.min.css';
import CustomPopup from './CustomPopup';

export default {
    title: 'Platform/Atoms/CustomPopup',
    component: CustomPopup,
};

const Template = ({ children, ...args }) => <CustomPopup {...args}>
    {children}
</CustomPopup>

export const customPopup = Template.bind({});

customPopup.args = {
    open: null,
    message: 'My Popup',
    messageVariant: '',
    pointerVariant: '',
    position: 'bottom',
    size: 'mini',
    color: 'red',
    trigger: <label>See Popup</label>
}
