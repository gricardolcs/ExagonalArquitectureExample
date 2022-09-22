import React from 'react';
import BasicCard from './BasicCard';
import 'semantic-ui-css/semantic.min.css';

export default {
  title: 'Platform/Atoms/BasicCard',
  component: BasicCard,
}

const Template = ({ children, ...args}) => <BasicCard {...args}>
  {children}
</BasicCard>

export const basicCard = Template.bind({});
basicCard.args={
  fluid: true,
  className: '',
}