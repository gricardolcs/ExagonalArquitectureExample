import React from "react";
import { action } from "@storybook/addon-actions";
import "semantic-ui-css/semantic.min.css";
import { ApplicantList } from "./ApplicantList";

export default {
  title: "Platform/organisms/ApplicantList",
  component: ApplicantList,
};

const Template = ({ children, ...args }) => (
  <ApplicantList {...args}>{children}</ApplicantList>
);

export const applicantList = Template.bind({});
applicantList.args = {
  data: [
    {
      career: "Digital Marketing",
      city: "Tarija",
      country: "Bolivia",
      email: "cristal.dev@gmail.com",
      fullName: "Cristal Dev",
      id: -9202665956764764000,
      telephone: "78233656",
    },
    {
      career: "System Engineering",
      city: "Santa Cruz",
      country: "Bolivia",
      email: "cristhian.ortiz@gmail.com",
      fullName: "Cristhian Ortiz",
      id: -5860254223043773000,
      telephone: "78434827",
    },
    {
      career: "Industrial Engineering",
      city: "La Paz",
      country: "Bolivia",
      email: "dora.solares@hotmail.com",
      fullName: "Dora Solares",
      id: -8946462828418711000,
      telephone: "76259390",
    },
  ],
  setComponent: action("Set Component"),
};
