import React, { Fragment, useCallback } from 'react';
import { Grid } from 'semantic-ui-react';
import PropTypes from 'prop-types';
import defaultProfileImg from '../../../images/logos/logo-profile.png';
import defaultSettingImg from '../../../images/logos/logo-settings.png';
import defaultLogoImg from '../../../images/logos/logo-navbar.png';
import DefaultLogo from '../../molecules/defaultLogo/DefaultLogo';
import BasicImage from '../../atoms/basicImage/BasicImage';
import CustomDropDown from '../../atoms/customDropDown/CustomDropDown';
import { useHistory } from 'react-router-dom';
import logoutUser from '../../../api/logout/LogoutUser';
import { useApplicationContext } from '../../../context/ApplicationContext';
import { AvailableRoutes } from '../../../enum/availableRoutes';
import { getName, getEmail } from '../../../helpers/jwtSession/jwtSession';
import { SESSION_COMPONENT } from '../../../utils/constants/constants';
import { DefaultPaths } from '../../../enum/availableRoutes';
import './style.css';

function HeaderTemplate({ appName, content, setComponent }) {
  const logoSize = 'mini';
  const history = useHistory();
  const { actions } = useApplicationContext();

  const handleOnSignout = useCallback(() => {
    logoutUser().then(() => {
      sessionStorage.setItem('token', '');
      sessionStorage.setItem(SESSION_COMPONENT, '');
      actions.onLogout();
      history.push(AvailableRoutes.LOGIN);
    });
  }, [history, actions]);

  const onHandleClickSettings = () => {
    sessionStorage.setItem(SESSION_COMPONENT, DefaultPaths.SETTINGS);
    setComponent(DefaultPaths.SETTINGS);
  };

  const onHandleClickEditProfileOption = () => {
    sessionStorage.setItem(SESSION_COMPONENT, DefaultPaths.EDIT_PROFILE);
    setComponent(DefaultPaths.EDIT_PROFILE);
  };

  return (
    <Fragment>
      <div className={'header-template'}>
        <Grid className={'grid-header-template'}>
          <Grid.Column width={2}>
            <DefaultLogo
              srcLogo={defaultLogoImg}
              sizeLogo={logoSize}
              textSide={appName}
            />
          </Grid.Column>
          <Grid.Column width={12} textAlign='center'>
            {content}
          </Grid.Column>

          <Grid.Column width={2} className={'header-grid-column'}>
            <Grid columns={4}>
              <Grid.Column
                width={3}
                className={'header-grid-column'}
              ></Grid.Column>
              <Grid.Column width={5} className={'header-grid-column'}>
                <BasicImage
                  src={defaultSettingImg}
                  size={logoSize}
                  className={'header-navbar-right-tool'}
                  onClick={onHandleClickSettings}
                />
              </Grid.Column>
              <Grid.Column
                width={5}
                className={'header-grid-column drop-down-header-column'}
              >
                <CustomDropDown
                  menuVariant='user-custom-menu'
                  headerVariant='user-menu-header'
                  variant='drop-down-header'
                  header={
                    <div>
                      <div
                        className='header-navbar-menu-username'
                        style={{ fontWeight: 'bold' }}
                      >
                        {getName()}
                      </div>
                      <span style={{ color: 'Gray' }}>{getEmail()}</span>
                    </div>
                  }
                  direction='left'
                  trigger={
                    <div>
                      <BasicImage
                        src={defaultProfileImg}
                        size={logoSize}
                        className={'header-navbar-right-tool-image'}
                      />
                    </div>
                  }
                  items={[
                    {
                      text: 'Edit Profile',
                      handleOnClick: onHandleClickEditProfileOption,
                    },
                    {
                      text: <label>Sign out</label>,
                      handleOnClick: handleOnSignout,
                    },
                  ]}
                />
              </Grid.Column>
              <Grid.Column
                width={3}
                className={'header-grid-column'}
              ></Grid.Column>
            </Grid>
            <Grid columns={4} className={'username-grid'}>
              <Grid.Column
                width={3}
                className={'header-grid-column'}
              ></Grid.Column>
              <Grid.Column
                width={5}
                className={'header-grid-column'}
              ></Grid.Column>
              <Grid.Column width={5} className={'header-grid-column'}>
                <p className='header-navbar-username'>{getName()}</p>
              </Grid.Column>
              <Grid.Column
                width={3}
                className={'header-grid-column'}
              ></Grid.Column>
            </Grid>
          </Grid.Column>
        </Grid>
      </div>
    </Fragment>
  );
}

HeaderTemplate.prototype = {
  appName: PropTypes.string,
  content: PropTypes.elementType,
  user: PropTypes.object,
};

HeaderTemplate.defaultProps = {
  appName: 'Recruitment System',
  content: <div></div>,
  user: { name: 'Name', email: 'Email' },
};

export default HeaderTemplate;
