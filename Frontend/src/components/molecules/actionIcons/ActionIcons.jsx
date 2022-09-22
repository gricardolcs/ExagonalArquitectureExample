import React from "react";
import PropTypes from 'prop-types';
import CustomIcon from '../../atoms/customIcon/CustomIcon'
import GetValue from '../../../utils/getValue/getValueOrEmpty';
import { Grid } from 'semantic-ui-react'
function ActionIcons({ arrayIcons, id, gridVariant }) {
  return (
    <Grid doubling className={gridVariant}>
      {arrayIcons.map(item => {
        return (<CustomIcon
          key={`icon-${item.name}`}
          name={GetValue.getValueOrEmpty(item.name)}
          color={item.color}
          className={GetValue.getValueOrEmpty(item.variant)}
          size={GetValue.getValueOrEmpty(item.size)}
          onClick={item.onClick ? () => { item.onClick(id) } : () => { }}
        >
        </CustomIcon>)
      })}
    </Grid >
  );
};

ActionIcons.prototype = {
  id: PropTypes.string,
  arrayIcons: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string,
      color: PropTypes.string,
      variant: PropTypes.string,
      size: PropTypes.string,
      onClick: PropTypes.func
    })
  ),
  gridVariant: PropTypes.string
}

ActionIcons.defaultProps = {
  id: '',
  arrayIcons: [{
    name: '',
    color: '',
    variant: '',
    size: '',
    onClick: () => { }
  }],
  gridVariant: ''
}

export default ActionIcons;
