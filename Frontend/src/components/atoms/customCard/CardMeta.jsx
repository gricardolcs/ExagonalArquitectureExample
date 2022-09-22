import React from "react";
import PropTypes from "prop-types";
import { Card } from "semantic-ui-react";

function CardMeta({ children, className }) {
  return (
    <Card.Meta className={className}>
      {children}
    </Card.Meta>
  );
}

CardMeta.prototype = {
  children: PropTypes.node,
  className: PropTypes.string,
};

CardMeta.defaultProps = {
  className: "",
};

export default CardMeta;
