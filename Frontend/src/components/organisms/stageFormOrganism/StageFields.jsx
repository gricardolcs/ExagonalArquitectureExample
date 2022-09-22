import DropDownField from '../../molecules/dropDownField/DropDownField';
import NumberField from '../../molecules/numberField/NumberField';
import FileField from '../../molecules/fileField/FileField';
import TextField from '../../molecules/textField/TextField';
import EnumSchemaInputType from './EnumSchemaInputType';
import GetValueUtils from '../../../utils/getValue/getValueOrEmpty';
import { commonProps, dropdownProps } from './CommonProps';
import uploadIcon from '../../../images/icons/icon-upload.png';
import BasicImage from '../../atoms/basicImage/BasicImage';
import CustomField from '../../atoms/customField/CustomField';
import './style.css';

export function getStageFields(props, type, onChange, onSubmitFileReport) {
  switch (type) {
    case EnumSchemaInputType.TEXT_INPUT:
      return (
        <CustomField key={props.label} variant="field-section stage-fields">
          <TextField
            {...commonProps}
            onChange={(value) => onChange(props.label, value)}
            value={GetValueUtils.getValueOrEmpty(props.qualification)}
            labelValue={props.label}
          />
        </CustomField>
      );
    case EnumSchemaInputType.NUMERIC_INPUT:
      return (
        <CustomField key={props.label} variant="field-section stage-fields">
          <NumberField
            {...commonProps}
            onChange={(value) => onChange(props.label, value)}
            value={GetValueUtils.getValueOrEmpty(props.qualification)}
            labelValue={props.label}
            errorNumber={props.errorMessageQualification}
          />
        </CustomField>
      );
    case EnumSchemaInputType.DROPDOWN_INPUT:
      let elements = [...props.elements];
      if (elements.length === 0) {
        switch (props.label) {
          case 'Status:':
            elements = ['Signed', 'Declined', 'On Hold'];
            break;
          case 'Recommended:':
            elements = ['YES', 'NO'];
            break;
          default:
            break;
        }
      }
      const options = elements.map((element) => {
        return { text: element, value: element };
      });

      return (
        <CustomField key={props.label} variant="field-section">
          <DropDownField
            {...dropdownProps}
            value={props.selectedElement}
            labelValue={props.label}
            onChange={(value) => onChange(props.label, value, elements)}
            options={options}
            allowAdditions={false}
          />
        </CustomField>
      );
    case EnumSchemaInputType.UPLOAD_FILE_INPUT:
      return (
        <FileField
          {...commonProps}
          labelValue={props.label}
          iconColor="black"
          imagePosition="right"
          variant="upload-section"
          imageVariant="image-upload"
          buttonContent={
            <BasicImage src={uploadIcon} className="upload-button" />
          }
          buttonVariant="button-upload"
          handleFile={onSubmitFileReport}
          onChange={(fileName) => onChange(props.label, fileName)}
          errorFile={props.errorMessageFile}
        />
      );
    default:
      return null;
  }
}

const StageFields = {
  getStageFields,
};

export default StageFields;
