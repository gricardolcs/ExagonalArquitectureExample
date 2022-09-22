import React, { Fragment } from "react";
import Media from 'react-media';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react'
import { Loading } from '../../atoms/loading/Loading'
import CustomButton from '../../atoms/customButton/CustomButton'
import BootcampCard from '../bootcampCard/BootcampCard';
import './style.css'

const BootcampListCardTemplate = ({
  tableData,
  handleEdit,
  handleDelete,
  setComponent,
  handleMoreButton,
  isButtonVisible,
}) => {

  if (tableData === undefined) {
    return <Loading></Loading>
  }

  function renderCard(item) {
    return (<Grid.Column key={`row-${item.id}`} className='bootcamp-card-container'>
      <BootcampCard bootcamp={item} setComponent={setComponent} handleEdit={handleEdit} handleDelete={handleDelete} />
    </Grid.Column>)
  }

  if (tableData === undefined) {
    return <Loading></Loading>
  }

  function renderCards(maxCardsInRow) {
    return (<Grid className='bootcamp-table' relaxed divided='vertically' columns={maxCardsInRow}>
      {
        tableData.map(item => { return (renderCard(item)) })
      }
    </Grid>)
  }

  return (
    <div>
      <Media queries={{
        mini: "(max-width: 599px)", // 1 card
        tiny: "(min-width: 600px) and (max-width: 1000px)", // 2 cards
        small: "(min-width: 1001px) and (max-width: 1365px)", // 3 cards
        medium: "(min-width: 1366px) and (max-width: 1799px)", // 4 cards
        large: "(min-width: 1800px)", // 5 cards
      }}>
        {matches => (
          <Fragment>
            {matches.mini && renderCards(1)}
            {matches.tiny && renderCards(2)}
            {matches.small && renderCards(3)}
            {matches.medium && renderCards(4)}
            {matches.large && renderCards(5)}
          </Fragment>
        )}
      </Media>
      {isButtonVisible && <Grid className='button-container' centered>
        <CustomButton
          label='more'
          variant='bootcamp-list-more-button'
          onClick={handleMoreButton}
        >
        </CustomButton>
      </Grid>}
    </div>
  );
};

BootcampListCardTemplate.prototype = {
  tableData: PropTypes.array,
  handleEdit: PropTypes.func,
  setComponent: PropTypes.func,
  handleDelete: PropTypes.func,
  handleMoreButton: PropTypes.func,
  isButtonVisible: PropTypes.bool
}

BootcampListCardTemplate.defaultProps = {
  handleEdit: () => { },
  handleDelete: () => { },
  setComponent: () => { },
  handleMoreButton: () => { },
  isButtonVisible: true
}

export default BootcampListCardTemplate;
