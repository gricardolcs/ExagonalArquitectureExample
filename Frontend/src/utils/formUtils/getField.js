import CustomTextField from '../../components/molecules/CustomTextField/CustomTextField';
import DropDownField from '../../components/molecules/dropDownField/DropDownField';
import DateField from '../../components/molecules/dateField/DateField';
import FileField from '../../components/molecules/fileField/FileField';
import ProfileUploadCard from '../../components/molecules/profileUploadCard/ProfileUploadCard';
import TextAreaField from '../../components/molecules/textAreaField/TextAreaField';
import NumberField from '../../components/molecules/numberField/NumberField';
import CustomPasswordField from '../../components/molecules/customPasswordField/CustomPasswordField';
import { errorMessages, patterns } from '../../utils/constants/constants';

export const getField = (
  type,
  setValue,
  trigger,
  data,
  errors,
  options,
  defaultValue = '',
  message = {}
) => {
  switch (type) {
    case 'input':
      return (
        <CustomTextField
          name={data.propertyName}
          setValue={setValue}
          trigger={trigger}
          data={data}
          errors={errors}
          defaultValue={defaultValue}
        />
      );
    case 'dropdown':
      return (
        <DropDownField
          name={data.propertyName}
          setValue={setValue}
          trigger={trigger}
          className={data.className}
          inputWidth={data.inputWidth}
          labelValue={data.label}
          labelWidth={data.labelWidth}
          labelColor={data.labelColor}
          labelVariant={data.labelVariant}
          options={options}
          placeholder={data.placeholder}
          allowAdditions={data.allowAdditions}
          required={data.required}
          errors={errors}
          defaultValue={defaultValue}
          message={message}
        />
      );
    case 'date':
      return (
        <DateField
          name={data.propertyName}
          setValue={setValue}
          trigger={trigger}
          className={data.className}
          inputWidth={data.inputWidth}
          iconColor={data.iconColor}
          labelValue={data.label}
          labelWidth={data.labelWidth}
          labelIcon={data.labelIcon}
          labelColor={data.labelColor}
          labelVariant={data.labelVariant}
          minDate={data.minDate}
          placeholder={data.placeholder}
          required={data.required}
          errors={errors}
        />
      );
    case 'file':
      return (
        <FileField
          name={data.propertyName}
          setValue={setValue}
          trigger={trigger}
          className={data.className}
          labelValue={data.label}
          labelWidth={data.labelWidth}
          labelColor={data.labelColor}
          labelVariant={data.labelVariant}
          inputWidth={data.inputWidth}
          placeholder={data.placeholder}
          iconInside={data.iconInside}
          required={data.required}
          errors={errors}
        />
      );
    case 'upload-photo':
      return (
        <ProfileUploadCard
          name={data.propertyName}
          setValue={setValue}
          trigger={trigger}
          defaultValue={defaultValue}
        />
      );
    case 'text-area':
      return (
        <TextAreaField
          name={data.propertyName}
          setValue={setValue}
          trigger={trigger}
          className={data.className}
          inputWidth={data.inputWidth}
          labelWidth={data.labelWidth}
          labelValue={data.label}
          labelVariant={data.labelVariant}
          labelColor={data.labelColor}
          placeholder={data.placeholder}
          required={data.required}
          variantTextArea={data.propertyName}
        />
      );
    case 'number':
      return (
        <NumberField
          name={data.propertyName}
          setValue={setValue}
          trigger={trigger}
          className={data.className}
          inputWidth={data.inputWidth}
          min={data.min}
          value={data.value}
          labelWidth={data.labelWidth}
          labelValue={data.label}
          labelVariant={data.labelVariant}
          labelColor={data.labelColor}
          placeholder={data.placeholder}
          required={data.required}
          errors={errors}
          defaultValue={defaultValue}
        />
      );
    case 'inputPassword':
      return (
        <CustomPasswordField
          name={data.propertyName}
          setValue={setValue}
          trigger={trigger}
          data={data}
          errors={errors}
        />
      );
    default:
      return <></>;
  }
};

export function buildErrors(field) {
  if (field.fieldErrors) {
    const keys = Object.keys(field.fieldErrors[0]);
    keys.forEach((error) => {
      switch (error) {
        case 'pattern':
          const type = field.fieldErrors[0][error]['type'];
          field.fieldErrors[0][error]['value'] = patterns[type] || '';
          field.fieldErrors[0][error]['message'] =
            errorMessages[`${type}FormatMessage`] || '';
          break;
        case 'min':
          field.fieldErrors[0][error]['message'] = errorMessages.minValue(
            field.fieldErrors[0].min.value
          );
          break;
        default:
          field.fieldErrors[0][error]['message'] = errorMessages[error];
          break;
      }
    });
  }
}

function getOptions(optionsToDropdown) {
  let options = [];
  for (const property in optionsToDropdown) {
    options.push(property);
  }
  return getElementsToDropdown(options);
}

function getElementsToDropdown(options) {
  return options.map((element, index) => {
    return {
      key: index,
      text: element.text ? element.text : element,
      value: element.value ? element.value : element,
    };
  });
}

const GetField = {
  getField,
  buildErrors,
  getOptions,
  getElementsToDropdown,
};

export default GetField;
