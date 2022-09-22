export const AUTHENTICATION = 'Authentication';

export const SESSION_COMPONENT = 'component';

export const SESSION_USER = 'current_user';

export const doNothing = () => {};

export const START = 'Start';

export const FINISH = 'Finish';

export const EDIT = 'Edit';

export const DELETE = 'Delete';

export const userStatusColors = {
  ACTIVE: 'green',
  INACTIVE: 'grey',
  BLOCKED: 'red',
};

export const CURRENT_USER = {
  id: '',
  firstname: '',
  lastname: '',
  email: '',
  phoneNumber: '',
  username: '',
  password: '',
  status: '',
  photo: '',
};
export const headerProfileCard = 'User';
export const FIELD_WIDTH = 'inputCreateForm';
export const FIELD_LABEL = 'labelCreateForm';
export const FORM_ROW = 'rowCreateForm';
export const USER_FIELD_WIDHT = `user-${FIELD_WIDTH}`;

export const patterns = {
  email: /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
  password: /(?=.*[a-z\u00f1])(?=.*[A-Z\u00d1])(?=.*\d)(?=.*[!@#$%^&*_=+-]).{8,16}/,
};

export const errorMessages = {
  confirmPasswordDoesNotMatch: `Password confirmation doesn't match`,
  required: 'This field is required',
  minLength: 'This field must contain more characters',
  emailFormatMessage: 'Email must be in xxxx@xxx.xxx format',
  valueAsNumber: 'Only numbers are allowed',
  min: 'The number must be a positive number',
  passwordFormatMessage:
    'Password must have a length between 8 and 16 characters with at least 1 uppercase letter 1 lowercase letter 1 number and 1 special character like "!@#$%^&*_=+-" format',
  minValue: (value) => `Value must be greater than ${value}`,
};
