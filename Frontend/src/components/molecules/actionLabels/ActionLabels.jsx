import React from "react";
import PropTypes from 'prop-types';
import BasicLabel from '../../atoms/basicLabel/BasicLabel'
import { Grid } from "semantic-ui-react";
import GetValue from "../../../utils/getValue/getValueOrEmpty";
import './style.css';

function ActionLabels({
  arrayLabels,
  width
}) {

  return (
    <Grid.Column width={width} >
      {
        arrayLabels.map(item => {
          return (
            <BasicLabel
              key={item.id}
              color={item.color}
              variant={GetValue.getValueOrEmpty(item.variant)}
              size={GetValue.getValueOrEmpty(item.size)}
              handleOnClick={() => item.onClick(item)}>
              <div className='text'>{GetValue.getValueOrEmpty(item.content)}</div>
            </BasicLabel>
          )
        })
      }
    </Grid.Column>
  );
};

ActionLabels.prototype = {
  arrayLabels: PropTypes.array,
  width: PropTypes.number
}

ActionLabels.defaultProps = {
  arrayLabels: [],
  width: 12
}

export default ActionLabels;
