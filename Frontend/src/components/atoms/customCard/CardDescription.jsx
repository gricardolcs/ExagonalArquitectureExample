import React from "react";
import PropTypes from "prop-types";
import { Card } from "semantic-ui-react";

function CardDescription({ children, className, textAlign }) {
    return (
        <Card.Description className={className} textAlign={textAlign}>
            {children}
        </Card.Description>
    );
}

CardDescription.prototype = {
    children: PropTypes.node,
    className: PropTypes.string,
    textAlign: PropTypes.string,
};

CardDescription.defaultProps = {
    className: "",
    textAlign: "center",
};

export default CardDescription;
