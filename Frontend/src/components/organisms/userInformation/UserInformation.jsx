import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from 'semantic-ui-react';
import defaultProfileImg from '../../../images/logos/logo-profile.png';
import {
  CURRENT_USER,
  FIELD_LABEL,
  USER_FIELD_WIDHT,
  FORM_ROW,
} from '../../../utils/constants/constants';
import BasicForm from '../../atoms/basicForm/BasicForm';
import BasicImage from '../../atoms/basicImage/BasicImage';
import CustomField from '../../atoms/customField/CustomField';
import CustomLabel from '../../atoms/customLabel/CustomLabel';
import DropDownField from '../../molecules/dropDownField/DropDownField';
import PasswordField from '../../molecules/passwordField/PasswordField';
import TextField from '../../molecules/textField/TextField';
import './style.css';

const statusOptions = [
  { key: 1, text: 'ACTIVE', value: 'ACTIVE' },
  { key: 2, text: 'INACTIVE', value: 'INACTIVE' },
  { key: 3, text: 'BLOCKED', value: 'BLOCKED' },
];

const UserInformation = ({ currentUser = CURRENT_USER }) => {
  const isView = true;

  return (
    <BasicForm className='user-form'>
      <Grid>
        <Grid.Column className='user-information-fields' width={16}>
          <Grid.Row className={FORM_ROW}>
            <Grid>
              <Grid.Column width={8}>
                <div className='user-information-profile'>
                  <BasicImage
                    circular={true}
                    src={
                      currentUser.photo
                        ? `data:image/jpeg;base64,${currentUser.photo}`
                        : defaultProfileImg
                    }
                  />
                  <CustomLabel
                    value={`${currentUser.firstname} ${currentUser.lastname}`}
                  />
                </div>
              </Grid.Column>
            </Grid>
          </Grid.Row>
          <Grid.Row className={FORM_ROW}>
            <Grid>
              <Grid.Column width={8}>
                <CustomField>
                  <TextField
                    value={currentUser.firstname}
                    className={USER_FIELD_WIDHT}
                    labelValue='First Name'
                    labelWidth='5'
                    labelColor='black'
                    labelVariant={FIELD_LABEL}
                    inputWidth='11'
                    readOnly={isView}
                  />
                </CustomField>
              </Grid.Column>
              <Grid.Column width={8}>
                <CustomField>
                  <TextField
                    value={currentUser.lastname}
                    className={USER_FIELD_WIDHT}
                    labelValue='Last Name'
                    labelWidth='5'
                    labelColor='black'
                    labelVariant={FIELD_LABEL}
                    inputWidth='11'
                    readOnly={isView}
                  />
                </CustomField>
              </Grid.Column>
            </Grid>
          </Grid.Row>
          <Grid.Row className={FORM_ROW}>
            <Grid>
              <Grid.Column width={8}>
                <CustomField>
                  <TextField
                    value={currentUser.phoneNumber}
                    className={USER_FIELD_WIDHT}
                    labelValue='Cell Number'
                    labelWidth='5'
                    labelColor='black'
                    labelVariant={FIELD_LABEL}
                    inputWidth='11'
                    readOnly={isView}
                  />
                </CustomField>
              </Grid.Column>
              <Grid.Column width={8}>
                <CustomField>
                  <TextField
                    value={currentUser.username}
                    className={USER_FIELD_WIDHT}
                    labelValue='Username'
                    labelWidth='5'
                    labelColor='black'
                    labelVariant={FIELD_LABEL}
                    inputWidth='11'
                    readOnly={isView}
                  />
                </CustomField>
              </Grid.Column>
            </Grid>
          </Grid.Row>
          <Grid.Row className={FORM_ROW}>
            <Grid>
              <Grid.Column width={8}>
                <CustomField>
                  <TextField
                    value={currentUser.email}
                    className={USER_FIELD_WIDHT}
                    labelValue='Email'
                    labelWidth='5'
                    labelColor='black'
                    labelVariant={FIELD_LABEL}
                    inputWidth='11'
                    readOnly={isView}
                  />
                </CustomField>
              </Grid.Column>
            </Grid>
          </Grid.Row>
          <Grid.Row className={FORM_ROW}>
            <Grid>
              <Grid.Column width={8}>
                <CustomField>
                  <PasswordField
                    value={'Contra1#'}
                    className={USER_FIELD_WIDHT}
                    labelValue='Password'
                    labelWidth='5'
                    labelColor='black'
                    labelVariant={FIELD_LABEL}
                    inputWidth='11'
                    readOnly={isView}
                  />
                </CustomField>
              </Grid.Column>
              <Grid.Column width={8}>
                <CustomField>
                  <PasswordField
                    value={'Contra1#'}
                    className={USER_FIELD_WIDHT}
                    labelValue='Confirm Password'
                    labelWidth='5'
                    labelColor='black'
                    labelVariant={FIELD_LABEL}
                    inputWidth='11'
                    readOnly={isView}
                  />
                </CustomField>
              </Grid.Column>
            </Grid>
          </Grid.Row>
          <Grid.Row className={FORM_ROW}>
            <Grid>
              <Grid.Column width={4}>
                <CustomField>
                  <DropDownField
                    value={currentUser.status}
                    className={USER_FIELD_WIDHT}
                    labelValue='Status'
                    labelWidth='5'
                    labelColor='black'
                    labelVariant={FIELD_LABEL}
                    inputWidth='11'
                    options={statusOptions}
                    allowAdditions={false}
                    readOnly={isView}
                  />
                </CustomField>
              </Grid.Column>
            </Grid>
          </Grid.Row>
        </Grid.Column>
      </Grid>
    </BasicForm>
  );
};

export default UserInformation;

UserInformation.prototype = {
  currentUser: PropTypes.object,
};
