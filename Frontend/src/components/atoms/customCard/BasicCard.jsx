import React from 'react'
import PropTypes from "prop-types";
import { Card } from 'semantic-ui-react'

function BasicCard({
  children,
  className,
  fluid
}) {
  return (
    <Card 
      fluid={fluid}
      className={className}
    >
      {children}
    </Card>
  )
}

BasicCard.PropType = {
  children: PropTypes.node,
  className: PropTypes.string,
  fluid: PropTypes.bool,
}

BasicCard.defaultProps = {
  className: '',
  fluid: false,
}
export default BasicCard

