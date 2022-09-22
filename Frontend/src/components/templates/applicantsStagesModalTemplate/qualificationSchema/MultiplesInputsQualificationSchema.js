import QualificationSchema from './QualificationSchema'
import TextField from '../../../molecules/textField/TextField';
import { Form } from 'semantic-ui-react';

export default class MultiplesInputsQualificationSchema extends QualificationSchema {

  constructor() {
    super();
    this.type = "";
    this.labels = "";
    this.qualifications = "";
  }

  qualifyAndSerializeSchema(qualifications) {
    this.qualifications = qualifications;
    return JSON.stringify(this);
  }

  deserializeSchema(schema, schemaResult) {
    if (schemaResult.applicantQualification) {
      this.qualifications = schemaResult.applicantQualification.qualifications;
    }
    this.type = schema.type;
    this.labels = schema.labels;
  }

  render(parameters) {
    const formFields = this.labels.map((label) =>
      <CustomField>
        <TextField
          value={this.qualifications}
          className={parameters.graphicProperties.textBoxClassName}
          labelVariant={parameters.graphicProperties.labelClassName}
          labelValue={label}
          labelWidth={parameters.graphicProperties.labelWidth}
          inputWidth={parameters.graphicProperties.inputWidth}
          onChange={parameters.onChange}
        />
      </CustomField>
    )
    return formFields
  }
}
