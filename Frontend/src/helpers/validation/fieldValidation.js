import { doNothing } from '../../utils/constants/constants';

const validateEqualFields = ({
  fieldToCompare = '',
  errorMessage = '',
  getValues = doNothing,
}) => (value) => value === getValues(fieldToCompare) || errorMessage;

const registerValidation = ({
  field = '',
  comparation = doNothing,
  register = doNothing,
}) => {
  register(field, {
    validate: {
      comparation,
    },
  });
};

export const passwordValidation = ({
  field = '',
  fieldToCompare = '',
  errorMessage = '',
  getValues = doNothing,
  register = doNothing,
}) => {
  registerValidation({
    field,
    comparation: validateEqualFields({
      fieldToCompare,
      errorMessage,
      getValues,
    }),
    register,
  });
};
