import React from 'react';
import PropTypes from 'prop-types';
import { Card } from 'semantic-ui-react';

function CardContent({ children, className, textAlign, onClick }) {
  return (
    <Card.Content className={className} textAlign={textAlign} onClick={onClick}>
      {children}
    </Card.Content>
  );
}

CardContent.prototype = {
  children: PropTypes.node,
  className: PropTypes.string,
  textAlign: PropTypes.string,
  onClick: PropTypes.func,
};

CardContent.defaultProps = {
  className: '',
  textAlign: 'center',
  onClick: () => {},
};

export default CardContent;
