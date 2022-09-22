import React from 'react';
import CardHeader from './CardHeader';
import 'semantic-ui-css/semantic.min.css';

export default {
  title: 'Platform/Atoms/CardHeader',
  component: CardHeader,
}

const Template = ({ children, ...args }) => <CardHeader {...args}>
  {children}
</CardHeader>

export const cardHeader = Template.bind({});
cardHeader.args = {
  className: '',
  textAlign: 'center',
}
