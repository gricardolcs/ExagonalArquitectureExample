import React from 'react';
import PropTypes from 'prop-types';
import TableMolecule from '../../../components/molecules/table/TableMolecule';
import { Grid } from 'semantic-ui-react';
import CustomButton from '../../../components/atoms/customButton/CustomButton';
import CustomLabel from '../../../components/atoms/customLabel/CustomLabel';
import { CreateSkillsCategoryModalOrganism } from '../../../components/organisms/createSkillsCategoryModalOrganism/CreateSkillsCategoryModalOrganism';
import { doNothing } from '../../../utils/constants/constants';
import './style.css';

export const SkillsCategoryTemplate = ({
  tableHeaders = [],
  tableData = [],
  tableRowAttributes = [],
  onCloseModal = doNothing,
  onOpenModal = doNothing,
  openModal = false,
  handleCloseModal = doNothing,
  headerModal = '',
  classNameModal = '',
  getCategories = doNothing,
  buttonLabel = '',
  tableRowActions = [],
}) => {
  return (
    <>
      <Grid>
        <Grid.Row className='newCategoryTitlePadding'>
          <CustomLabel value='Skills Category' size='big' elementType='h1' />
        </Grid.Row>
        <Grid.Row className='newCategoryButtonPadding'>
          <CustomButton
            label={'+Add Category'}
            onClick={onOpenModal}
            color='blue'
            size='medium'
            variant='buttonStyle'
            circular={false}
          />
        </Grid.Row>
        <TableMolecule
          textAlign='left'
          textAlignHeaders='left'
          basic='very'
          tableVariant='skill-category-table'
          tableHeaders={tableHeaders}
          tableData={tableData}
          tableRowAttributes={tableRowAttributes}
          tableRowActions={tableRowActions}
        />
      </Grid>
      <CreateSkillsCategoryModalOrganism
        onClose={onCloseModal}
        onOpen={onOpenModal}
        open={openModal}
        handleCloseModal={handleCloseModal}
        header={headerModal}
        className={classNameModal}
        getCategories={getCategories}
        buttonLabel={buttonLabel}
        modalSize={'mini'}
      />
    </>
  );
};

SkillsCategoryTemplate.prototype = {
  tableHeaders: PropTypes.array,
  tableData: PropTypes.array,
  tableRowAttributes: PropTypes.array,
  onCloseModal: PropTypes.func,
  onOpenModal: PropTypes.func,
  openModal: PropTypes.array,
  open: PropTypes.bool,
  handleCloseModal: PropTypes.func,
  headerModal: PropTypes.string,
  classNameModal: PropTypes.string,
  getCategories: PropTypes.func,
  buttonLabel: PropTypes.string,
  tableRowActions: PropTypes.array,
};
