import React from 'react';
import PropTypes from 'prop-types';
import { TableMolecule } from '../../molecules/table/TableMolecule';
import CustomPagination from '../../atoms/customPagination/CustomPagination';
import { Grid } from 'semantic-ui-react';
import DropDownField from '../../molecules/dropDownField/DropDownField';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import { doNothing } from '../../../utils/constants/constants';
import './style.css';

export function TableOrganismDeprecated({
  tableHeaders = [],
  tableData = [],
  tableRowAttributes = [],
  textAlign = '',
  labelValue = '',
  basic = '',
  handleEdit = doNothing,
  handleDelete = doNothing,
  textAlignHeaders = '',
  totalPages = 1,
  setNumberOfApplicantsToShow = doNothing,
  activePage = 1,
  setActivePage = doNothing,
  numberApplicantsOption = [],
  paginationInterval = '',
}) {
  return (
    <Grid container stackable>
      <Grid className='gridMobile'>
        <Grid.Row only='mobile' className='paddingZero'>
          <CustomLabel value='Participants' size='large' />
        </Grid.Row>
      </Grid>
      <Grid.Row>
        <TableMolecule
          tableHeaders={tableHeaders}
          tableData={tableData}
          tableRowAttributes={tableRowAttributes}
          textAlign={textAlign}
          textAlignHeaders={textAlignHeaders}
          basic={basic}
          handleEdit={handleEdit}
          handleDelete={handleDelete}
          headerVariant='classNameHeader'
          cellVariant='classNameCell'
        />
      </Grid.Row>
      {tableData.length !== 0 && (
        <Grid.Row verticalAlign='bottom'>
          <Grid stackable doubling columns={3}>
            <Grid.Column width={3} textAlign='left' className='contentMin'>
              <DropDownField
                labelValue={labelValue}
                onChange={setNumberOfApplicantsToShow}
                options={numberApplicantsOption}
                labelWidth='11'
                inputWidth='5'
                labelSize='large'
                compact={true}
                classNameFields='paddingTrim'
                placeholder='20'
              />
            </Grid.Column>
            <Grid.Column width={3} className='paddingTrim contentMin'>
              <CustomLabel value={paginationInterval} size='large' />
            </Grid.Column>
            <Grid.Column width={10} textAlign='right'>
              <CustomPagination
                activePage={activePage}
                totalPages={totalPages}
                onChange={setActivePage}
              />
            </Grid.Column>
          </Grid>
        </Grid.Row>
      )}
    </Grid>
  );
}

TableOrganismDeprecated.prototype = {
  tableHeaders: PropTypes.array,
  tableData: PropTypes.array,
  tableRowAttributes: PropTypes.array,
  textAlign: PropTypes.string,
  handleEdit: PropTypes.func,
  handleDelete: PropTypes.func,
  textAlignHeaders: PropTypes.string,
  totalPages: PropTypes.number,
  setNumberOfApplicantsToShow: PropTypes.func,
  activePage: PropTypes.number,
  setActivePage: PropTypes.func,
  numberApplicantsOption: PropTypes.array,
  paginationInterval: PropTypes.string,
};
