import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import CustomButton from '../../atoms/customButton/CustomButton';
import UserInformation from '../userInformation/UserInformation';
const UserProfile = ({ currentUser = {}, enableEditForm }) => {
  return (
    <div className="user-profile">
      <Grid>
        <Grid.Column width={'13'}>
          <UserInformation currentUser={currentUser} />
        </Grid.Column>
        <Grid.Column width={'3'}>
          <CustomButton
            id={'user-edit-btn'}
            onClick={enableEditForm}
            label={'Edit'}
            type="Submit"
            circular={false}
            color="blue"
            variant={'user-form-btn'}
          />
        </Grid.Column>
      </Grid>
    </div>
  );
};

export default UserProfile;

UserProfile.prototype = {
  currentUser: PropTypes.object,
};
