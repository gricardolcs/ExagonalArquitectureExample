import React, { useState } from 'react';
import Media from 'react-media';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import CustomButton from '../../atoms/customButton/CustomButton';
import { Loading } from '../../atoms/loading/Loading';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import './style.css';

/**
 * Component to wrap list of cards and handle actions by passed props
 * @author Cristhian Ortiz
 * @property {string} title - text for the list title
 * @property {array} data - array of objects to display on cards
 * @property {function} searchLogic - handler for search data on the list
 * @property {node} addButton - button that allows us to add new data
 * @property {function} toolBarContent - all the components to handle the data
 * @property {function} renderCard - handler for render the card according the need
 * @property {string} tableVariant - the style that the table will have
 * @property {string} cardVariant - the style that the card panel will have
 * @property {node} emptyData - view if there is not data
 */
export function CustomList({
  title,
  data,
  searchLogic,
  addButton,
  toolBarContent,
  renderCard,
  tableVariant,
  cardVariant,
  emptyData,
  withCounter = true,
}) {
  const [filteredData, setFilteredData] = useState([]);
  const [criteria, setCriteria] = useState('');

  if (data === undefined) {
    return <Loading></Loading>;
  }

  function renderCards(maxCardsInRow) {
    const listToRender = criteria.length > 0 ? filteredData : data;
    return (
      <Grid className={`list-grid ${cardVariant}`} columns={maxCardsInRow}>
        {listToRender &&
          listToRender.map((element) => {
            return renderCard(element);
          })}
      </Grid>
    );
  }

  function searchData(textToSearch) {
    setCriteria(textToSearch);
    searchLogic(textToSearch, setFilteredData);
  }

  return (
    <Grid style={{ marginTop: 0 }}>
      <Grid.Row>
        <Grid.Column
          className={
            cardVariant === 'cards-table' ? 'list-title' : 'list-title-content'
          }
          width={3}
        >
          <CustomLabel elementType="h1" size="big">
            {title}
          </CustomLabel>
          {withCounter && (
            <CustomButton
              label={criteria.length > 0 ? filteredData.length : data.length}
              color="orange"
              size="mini"
              variant="buttonStyle"
            />
          )}
        </Grid.Column>
        <Grid.Column floated="right" width={5} className="optionSection">
          {addButton}
        </Grid.Column>
      </Grid.Row>
      {toolBarContent && <Grid.Row>{toolBarContent(searchData)}</Grid.Row>}
      {data.length > 0 ? (
        <div>
          <Media
            queries={{
              mini: '(max-width: 599px)', // 1 card
              tiny: '(min-width: 600px) and (max-width: 1000px)', // 2 cards
              small: '(min-width: 1001px) and (max-width: 1365px)', // 3 cards
              medium: '(min-width: 1366px) and (max-width: 1799px)', // 4 cards
              large: '(min-width: 1800px)', // 5 cards
            }}
          >
            {(matches) => (
              <div className={tableVariant}>
                {matches.mini && renderCards(1)}
                {matches.tiny && renderCards(2)}
                {matches.small && renderCards(3)}
                {matches.medium && renderCards(4)}
                {matches.large && renderCards(5)}
              </div>
            )}
          </Media>
        </div>
      ) : (
        emptyData
      )}
    </Grid>
  );
}

CustomList.prototype = {
  title: PropTypes.string,
  data: PropTypes.array,
  searchLogic: PropTypes.func,
  addButton: PropTypes.node,
  toolBarContent: PropTypes.node,
  renderCard: PropTypes.func,
  cardStyle: PropTypes.string,
  emptyData: PropTypes.node,
};

CustomList.defaultProps = {
  title: '',
  data: [],
  searchLogic: () => {},
  addButton: '',
  toolBarContent: '',
  renderCard: () => {},
  cardStyle: 'cards-table',
  emptyData: '',
};
