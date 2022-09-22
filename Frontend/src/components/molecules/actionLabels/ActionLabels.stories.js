import React from 'react'
import 'semantic-ui-css/semantic.min.css';
import ActionLabels from './ActionLabels';

export default {
  title: 'Platform/Molecules/ActionLabels',
  component: ActionLabels,
}

const Template = ({ children, ...args }) => <ActionLabels {...args}>
  {children}
</ActionLabels>

const arrayLabels = [
  {
    content: 'Users',
    size: 'large'
  },
  {
    content: 'Clients',
    size: 'large'
  },
  {
    content: 'Sales',
    size: 'big'
  },
  {
    content: 'Reports',
    size: 'big'
  },
]

export const actionLabels = Template.bind({});
actionLabels.args = {
  arrayLabels,
  width: 16
}
