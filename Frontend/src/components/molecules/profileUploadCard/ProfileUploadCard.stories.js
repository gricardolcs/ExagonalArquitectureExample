import React from 'react';
import ProfileUploadCard from './ProfileUploadCard';
import { action } from '@storybook/addon-actions';
import 'semantic-ui-css/semantic.min.css';

export default {
    title: 'Platform/Molecules/ProfileUploadCard',
    component: ProfileUploadCard,
}

const Template = ({ children, ...args }) => <ProfileUploadCard {...args}>
    {children}
</ProfileUploadCard>

export const profileUploadCard = Template.bind({});
profileUploadCard.args = {
    handleOnImagenProfile: action('Handle image profile'),
}
