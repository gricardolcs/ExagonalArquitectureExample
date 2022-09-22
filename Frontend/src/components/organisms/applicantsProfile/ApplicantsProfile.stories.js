import React from 'react';
import ApplicantsProfile from './ApplicantsProfile';
import photoDefault from '../../../images/atoms/basicImage/photoDefault.png';
import 'semantic-ui-css/semantic.min.css';

export default {
  title: 'Platform/Organisms/ApplicantsProfile',
  component: ApplicantsProfile,
}

const Template = ({ children, ...args}) => <ApplicantsProfile {...args}>
  {children}
</ApplicantsProfile>

export const applicantsProfile = Template.bind({});
applicantsProfile.args={
  applicant: {
    id: '-4561233585',
    name: "Maria Orellana",
    email: "maria.orellana@gmail.com",
    photo: photoDefault,
    phone: "76965489",
    birthday: "23/03/1995",
    age: "26 years",
    currentCity: "Cochabamba",
    career: "Ing. Sistemas",
    degree: "bachelor",
  }
}