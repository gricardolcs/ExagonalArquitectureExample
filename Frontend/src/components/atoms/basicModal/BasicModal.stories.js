import React from 'react';
import BasicModal from './BasicModal';
import { action } from '@storybook/addon-actions';

import 'semantic-ui-css/semantic.min.css';

export default {
  title: 'Platform/Atoms/BasicModal',
  component: BasicModal,
  argTypes: {
    size: {
      control: {
        type: 'select',
        options: ['mini', 'tiny', 'small', 'large', 'fullscreen'],
      },
    }
  },
};

// TODO(daniel.lopezj): Open the modal when clicking a button and close the same way or
// clicking outside the modal, by now only is working with the storybook controls
const Template = ({ children, ...args }) => <BasicModal {...args}>
  {children}
</BasicModal>

export const basicModal = Template.bind({});
basicModal.args = {
  children: 'Modal Content',
  header: 'Modal Header',
  onClose: action('On Close'),
  open: true,
  onOpen: action('On Open'),
  size: 'small',
  displayCloseIcon: false,
  classNameHeader: '',
  variant: '',
  modalVariant: '',
  showHeader: true,
  trigger: (<button class="ui button">Show Modal</button>)
}
