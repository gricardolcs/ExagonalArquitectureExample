import React from 'react';
import ComponentCard from '../../molecules/componentCard/ComponentCard';
import InputNumberBlockOrganism from '../../organisms/inputNumberBlockOrganism/InputNumberBlockOrganism';
import Structure from '../../../data/userSettingsForms/structure';
import AccountBlockAttemptsFields from '../../../data/userSettingsForms/accountBlockAttemptsFields.json';
import UpdateAccountExpirationDayFields from '../../../data/userSettingsForms/updateAccountExpirationDayFields.json';
import { DEFAULT_CONFIG } from '../../../utils/accountSettingsUtils/constants';

import './style.css';

export const UserSettingsTemplate = ({
  accountExpirationDayConfig = DEFAULT_CONFIG,
  accountBlockAttemptConfig = DEFAULT_CONFIG,
}) => {
  const userPasswordExpirationBlock = () => (
    <InputNumberBlockOrganism
      fields={UpdateAccountExpirationDayFields}
      structure={Structure}
      defaultValues={accountExpirationDayConfig.defaultValues}
      onSubmit={accountExpirationDayConfig.onsubmit}
      blockclassName={accountExpirationDayConfig.blockClassName}
      title={accountExpirationDayConfig.title}
      className={accountExpirationDayConfig.className}
      numberValue={accountExpirationDayConfig.value}
      valueClassName={accountExpirationDayConfig.className}
    />
  );
  const userBlockAttemptsBlock = () => (
    <InputNumberBlockOrganism
      fields={AccountBlockAttemptsFields}
      structure={Structure}
      defaultValues={accountBlockAttemptConfig.defaultValues}
      onSubmit={accountBlockAttemptConfig.onsubmit}
      blockclassName={accountBlockAttemptConfig.blockClassName}
      title={accountBlockAttemptConfig.title}
      className={accountBlockAttemptConfig.className}
      numberValue={accountBlockAttemptConfig.value}
      valueClassName={accountBlockAttemptConfig.valueClassName}
    />
  );

  return (
    <>
      <div className='numeric-block'>
        <ComponentCard
          headerText='User Password Expiration'
          BodyComponent={userPasswordExpirationBlock}
        />
      </div>
      <div className='numeric-block'>
        <ComponentCard
          headerText='Set User Block Attempts'
          BodyComponent={userBlockAttemptsBlock}
        />
      </div>
    </>
  );
};
