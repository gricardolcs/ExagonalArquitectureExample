import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import { doNothing } from '../../../utils/constants/constants';
import CustomButton from '../../atoms/customButton/CustomButton';
import TableOrganism from '../../organisms/table/TableOrganism';

const UserListTemplate = ({
  tableHeaderData = [],
  tableBodyData = [],
  tableFieldsBody = [],
  onCreateUser = doNothing,
  onClickTableRow = doNothing,
}) => {
  return (
    <Grid>
      <Grid.Row>
        <Grid.Column>
          <CustomButton
            label={'+   Add User'}
            onClick={onCreateUser}
            color='blue'
            size='medium'
            variant='buttonStyle'
            circular={false}
          />
        </Grid.Column>
      </Grid.Row>
      <Grid.Row>
        <Grid.Column>
          <TableOrganism
            headerData={tableHeaderData}
            bodyData={tableBodyData}
            bodyFieldsData={tableFieldsBody}
            onClickRow={onClickTableRow}
            rowVariant={'rowCellData'}
          />
        </Grid.Column>
      </Grid.Row>
    </Grid>
  );
};

export default UserListTemplate;

UserListTemplate.prototype = {
  tableHeaderData: PropTypes.array,
  tableBodyData: PropTypes.array,
  tableFieldsBody: PropTypes.array,
  onCreateUser: PropTypes.func,
  onClickTableRow: PropTypes.func,
};
