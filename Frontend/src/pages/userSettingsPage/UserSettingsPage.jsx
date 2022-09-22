/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect, useState } from 'react';
import { UserSettingsTemplate } from '../../components/templates/userSettingsTemplate/UserSettingsTemplate';
import getUserAccountConfigurationById from '../../api/settings/fetch/getUserAccountConfigurationById';
import updateAccountBlockAttemps from '../../api/settings/update/updateAccountBlockAttemps';
import updateExpirationDays from '../../api/settings/update/updateExpirationDays';
import updateAccountPasswordExpirationDay from '../../api/settings/update/updateAccountPasswordExpirationDay';
import { DEFAULT_ACCOUNT_SETTINGS_ID } from '../../utils/accountSettingsUtils/constants';

export const UserSettingsPage = () => {
  const [values, setValues] = useState({
    blockAttempts: 0,
    passwordExpirationInDays: 0,
  });

  useEffect(() => {
    getUserAccountConfigurationById(DEFAULT_ACCOUNT_SETTINGS_ID).then(
      (data) => {
        setValues({
          ...values,
          ...data,
        });
      }
    );
  }, []);

  const onSubmitAccountBlockAttempts = (data) => {
    updateAccountBlockAttemps(
      DEFAULT_ACCOUNT_SETTINGS_ID,
      data.blockAttempts
    ).then(() => {
      setValues({ ...values, ...data });
    });
  };

  const onSubmitExpirationDays = (data) => {
    updateExpirationDays(
      DEFAULT_ACCOUNT_SETTINGS_ID,
      data.passwordExpirationInDays
    ).then(() => {
      setValues({ ...values, ...data });
      updateAccountPasswordExpirationDay(data.passwordExpirationInDays);
    });
  };

  return (
    <UserSettingsTemplate
      accountExpirationDayConfig={{
        defaultValues: values,
        onsubmit: onSubmitExpirationDays,
        blockClassName: 'edit-numeric-block',
        title: 'Expiration days',
        className: 'numeric-block-body',
        value: values.passwordExpirationInDays,
        valueClassName: 'numeric-body-value',
      }}
      accountBlockAttemptConfig={{
        defaultValues: values,
        onsubmit: onSubmitAccountBlockAttempts,
        blockClassName: 'edit-numeric-block',
        title: 'Block Attempts',
        className: 'numeric-block-body',
        value: values.blockAttempts,
        valueClassName: 'numeric-body-value',
      }}
    />
  );
};
