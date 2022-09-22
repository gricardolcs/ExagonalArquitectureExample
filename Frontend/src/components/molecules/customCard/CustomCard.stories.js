import React from 'react';
import { action } from '@storybook/addon-actions';
import 'semantic-ui-css/semantic.min.css';
import { CustomCard } from './CustomCard';
import cardImage from '../../../images/atoms/customCard/default-bootcamp-image.jpg';
import BasicImage from '../../atoms/basicImage/BasicImage';

export default {
  title: 'Platform/Molecules/CustomCard',
  component: CustomCard
};

const Template = ({ children, ...args }) => <CustomCard {...args}>{children}</CustomCard>;

export const customCard = Template.bind({});
customCard.args = {
  cardImage: <BasicImage src={cardImage} />,
  cardHeader: 'Card Example Header',
  cardMeta: 'Card Meta',
  cardDescription: [{ key: 'card-description', render: 'Card Description'}],
  dropDownOptions: [{text: 'Option 1'}, {text: 'Option 2'}, {text: 'Option 3'}],
  tabColorVariant: 'gray',
  showStatus: true,
  showRemove: false,
  onClickCard: action('On CLick Card'),
  handleOnRemoveImage: action('On Click Remove image profile'),
  children: <React.Fragment />,
  typeCard: ""
}
