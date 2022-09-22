import React from 'react';
import { Grid } from 'semantic-ui-react';
import defaultProfileImg from '../../../images/logos/logo-profile.png';
import CustomButton from '../../atoms/customButton/CustomButton';
import useEditForm from '../../../hooks/useEditForm';
import './style.css';
import BasicImage from '../../atoms/basicImage/BasicImage';
import { CURRENT_USER, doNothing } from '../../../utils/constants/constants';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import EditProfileForm from '../editProfileForm/EditProfileForm';

const EditProfileInformation = ({
  currentUser = CURRENT_USER,
  setCurrentUser = doNothing,
  closeSession = doNothing,
}) => {
  const { isEnableEditForm, hideEditForm, showEditForm } = useEditForm();

  return (
    <div className='page-information-section'>
      {isEnableEditForm ? (
        <EditProfileForm
          onCloseEditForm={hideEditForm}
          currentUser={currentUser}
          setCurrentUser={setCurrentUser}
          closeSession={closeSession}
        />
      ) : (
        <Grid columns={2}>
          <Grid.Column>
            <Grid columns={2}>
              <Grid.Row>
                <div>
                  <BasicImage
                    circular={true}
                    src={
                      currentUser.photo
                        ? `data:image/jpeg;base64,${currentUser.photo}`
                        : defaultProfileImg
                    }
                  />
                  <CustomLabel
                    size='massive'
                    value={`${currentUser.firstname} ${currentUser.lastname}`}
                  />
                </div>
              </Grid.Row>
              <Grid.Row>
                <Grid.Column>
                  <div>First Name</div>
                  <div>{currentUser.firstname}</div>
                </Grid.Column>
                <Grid.Column>
                  <div>Last Name</div>
                  <div>{currentUser.lastname}</div>
                </Grid.Column>
              </Grid.Row>
              <Grid.Row>
                <Grid.Column>
                  <div>Cell Number</div>
                  <div>{currentUser.phoneNumber}</div>
                </Grid.Column>
                <Grid.Column>
                  <div>Username</div>
                  <div>{currentUser.username}</div>
                </Grid.Column>
              </Grid.Row>
              <Grid.Row>
                <Grid.Column>
                  <div>Email</div>
                  <div>{currentUser.email}</div>
                </Grid.Column>
              </Grid.Row>
            </Grid>
          </Grid.Column>
          <Grid.Column textAlign='right'>
            <CustomButton
              id='user-edit-button'
              onClick={showEditForm}
              label='Edit'
              circular={false}
              color='blue'
            />
          </Grid.Column>
        </Grid>
      )}
    </div>
  );
};

export default EditProfileInformation;
