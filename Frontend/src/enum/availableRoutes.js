export const AvailableRoutes = {
  LOGIN: '/login',
  HOME: '/',
};

export const DefaultPaths = {
  SETTINGS: 'SettingsPage',
  APPLICANTS: 'ApplicantList',
  BOOTCAMPS: 'BootcampList',
  USERS: 'UsersListPage',
  EDIT_PROFILE: 'EditProfile',
};

export const SettingsPaths = {
  DEFAULT: DefaultPaths.SETTINGS,
  USERS_ROLES: {
    DEFAULT: `${DefaultPaths.SETTINGS}[${DefaultPaths.USERS}]`,
    CREATE_USER: `${DefaultPaths.SETTINGS}[${DefaultPaths.USERS}][CreateUser]`,
    EDIT_USER: `${DefaultPaths.SETTINGS}[${DefaultPaths.USERS}][EditUser]`,
    USER_PROFILE: `${DefaultPaths.SETTINGS}[${DefaultPaths.USERS}][UserProfile]`,
  },
  APPLICANTS: `${DefaultPaths.SETTINGS}[Applicants]`,
  PASSWORD_EXPIRATION: `${DefaultPaths.SETTINGS}[PasswordExpiration]`,
};

export const ApplicantsPaths = {
  DEFAULT: DefaultPaths.APPLICANTS,
  PROFILE: `${DefaultPaths.APPLICANTS}[ApplicantProfile]`,
};

export const BootcampsPaths = {
  DEFAULT: DefaultPaths.BOOTCAMPS,
  PROFILE: `${DefaultPaths.BOOTCAMPS}[BootcampProfile]`,
  APPLICANT_PROFILE: `${DefaultPaths.BOOTCAMPS}[BootcampProfile[ApplicantProfile]]`,
};
