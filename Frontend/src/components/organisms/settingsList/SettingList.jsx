import React from 'react';
import { Grid } from 'semantic-ui-react';
import SettingCard from '../../molecules/settingCard/SettingCard';
import { CustomList } from '../customList/CustomList';
import { doNothing } from '../../../utils/constants/constants';
import PropTypes from 'prop-types';

const SettingsList = ({ data = {}, setComponent }) => {
  const renderList = () => {
    return Object.keys(data).map((current, key) => {
      const setting = data[current];
      return (
        <SettingCard
          key={key}
          value={setting}
          setComponent={setComponent}
          {...setting}
        />
      );
    });
  };

  const generateListData = () => {
    return Object.keys(data).map((current) => {
      return data[current];
    });
  };

  const renderCard = (setting) => {
    return (
      <Grid.Column key={`row-${setting.id}`} className="applicant-card-column">
        {<SettingCard value={setting} setComponent={setComponent} />}
      </Grid.Column>
    );
  };
  return (
    <CustomList
      title="Setups"
      data={generateListData()}
      renderCard={renderCard}
      tableVariant="custom-applicant-list"
      withCounter={false}
    >
      {renderList()}
    </CustomList>
  );
};

SettingsList.propTypes = {
  data: PropTypes.object,
  setComponent: PropTypes.func,
};

SettingsList.defaultProps = {
  data: {},
  setComponent: doNothing,
};

export default SettingsList;
