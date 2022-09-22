import QualificationSchema from './QualificationSchema'
import TextField from '../../../molecules/textField/TextField';
import { Form } from 'semantic-ui-react';

export default class SingleInputQualificationSchema extends QualificationSchema {

  constructor() {
    super();
    this.type = "";
    this.label = "";
    this.qualification = "";
  }

  qualifyAndSerializeSchema(qualification) {
    this.qualification = qualification;
    return JSON.stringify(this);
  }

  deserializeSchema(schema, schemaResult) {
    if (schemaResult.applicantQualification) {
      this.qualification = schemaResult.applicantQualification.qualification;
    }

    this.type = schema.type;
    this.label = schema.label;
  }

  render(parameters) {
    return <CustomField>
      <TextField
        value={this.qualification}
        className={parameters.graphicProperties.textBoxClassName}
        labelVariant={parameters.graphicProperties.labelClassName}
        labelValue={this.label}
        labelWidth={parameters.graphicProperties.labelWidth}
        inputWidth={parameters.graphicProperties.inputWidth}
        onChange={parameters.onChange}
        disabled={parameters.graphicProperties.disabled}
      />
    </CustomField>
  }
}
