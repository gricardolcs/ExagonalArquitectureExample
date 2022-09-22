import React from "react";
import CardMeta from "./CardMeta";
import "semantic-ui-css/semantic.min.css";

export default {
  title: "Platform/Atoms/CardMeta",
  component: CardMeta,
};

const Template = ({ children, ...args }) => (
  <CardMeta {...args}>{children}</CardMeta>
);

export const cardMeta = Template.bind({});
cardMeta.args = {
  className: "",
};
