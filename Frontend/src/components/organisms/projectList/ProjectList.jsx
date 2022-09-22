import React from 'react';
import PropTypes from 'prop-types';
import { CustomList } from '../customList/CustomList';
import BootcampCard from '../../molecules/bootcampCard/BootcampCard';
import CustomButton from '../../atoms/customButton/CustomButton';
import { Grid } from 'semantic-ui-react';
import BasicImage from '../../atoms/basicImage/BasicImage';
import TextInput from '../../atoms/textInput/TextInput';
import iconRow from '../../../images/icons/icon-rows.png';
import iconGrid from '../../../images/icons/icon-grid.png';
import iconDownload from '../../../images/icons/icon-download-menu.png';
import DropDownInput from '../../atoms/dropDownInput/DropDownInput';
import bootcampFilterByCriteria from '../../../api/bootcamp/fetch/BootcampFilterByCriteria';
import './style.css';

export function ProjectList({
  data,
  onNewElementClick,
  handleEdit,
  handleDelete,
  setComponent,
}) {
  function toolBarContent(searchDataLogic) {
    return (
      <>
        <Grid.Column width={5} className="optionSection">
          <TextInput
            label={
              <DropDownInput
                placeholder="All Projects"
                options={[
                  {
                    key: 'All Projects',
                    text: 'All Projects',
                    value: 'All Projects',
                  },
                ]}
                className="textInputDropdown"
              />
            }
            icon="search"
            placeholder="Search Project ..."
            className="textInputSearch"
            onChange={searchDataLogic}
          />
        </Grid.Column>
        <Grid.Column floated="right" width={5} className="optionSection">
          <BasicImage src={iconRow} size="mini" className="sizeIconRow" />
          <BasicImage src={iconGrid} size="mini" className="sizeIconGrid" />
          <BasicImage
            src={iconDownload}
            size="mini"
            className="sizeIconDownload"
          />
        </Grid.Column>
      </>
    );
  }

  function renderCard(element) {
    return (
      <Grid.Column key={`row-${element.id}`} className="project-card-column">
        {
          <BootcampCard
            bootcamp={element}
            setComponent={setComponent}
            handleEdit={handleEdit}
            handleDelete={handleDelete}
          />
        }
      </Grid.Column>
    );
  }

  const addProjectButton = (
    <CustomButton
      label={'New Project'}
      onClick={onNewElementClick}
      color="blue"
      size="medium"
      variant="buttonStyle"
      circular={false}
    />
  );

  return (
    <CustomList
      title="Projects"
      data={data}
      toolBarContent={toolBarContent}
      searchLogic={bootcampFilterByCriteria}
      addButton={addProjectButton}
      renderCard={renderCard}
      tableVariant="custom-project-list"
    />
  );
}

ProjectList.prototype = {
  data: PropTypes.array,
  onNewElementClick: PropTypes.func,
  handleEdit: PropTypes.func,
  handleDelete: PropTypes.func,
  setComponent: PropTypes.func,
};

ProjectList.defaultProps = {
  data: [],
  onNewElementClick: () => {},
  handleEdit: () => {},
  handleDelete: () => {},
  setComponent: () => {},
};
