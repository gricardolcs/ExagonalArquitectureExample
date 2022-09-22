import React from "react";
import PropTypes from "prop-types";
import { Card } from "semantic-ui-react";

function CardHeader({ children, className, textAlign }) {
    return (
        <Card.Header className={className} textAlign={textAlign}>
            {children}
        </Card.Header>
    );
}

CardHeader.prototype = {
    children: PropTypes.node,
    className: PropTypes.string,
    textAlign: PropTypes.string,
};

CardHeader.defaultProps = {
    className: "",
    textAlign: "center",
};

export default CardHeader;
