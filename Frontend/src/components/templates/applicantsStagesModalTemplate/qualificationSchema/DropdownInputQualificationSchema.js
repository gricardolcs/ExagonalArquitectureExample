import QualificationSchema from './QualificationSchema'
import DropDownField from '../../../molecules/dropDownField/DropDownField'
import { Form } from 'semantic-ui-react'

export default class DropdownInputQualificationSchema extends QualificationSchema {
  constructor() {
    super();
    this.type = "";
    this.label = "";
    this.qualification = "";
    this.options = "";
  }

  qualifyAndSerializeSchema(qualification) {
    this.qualification = qualification;
    return JSON.stringify(this);
  }

  deserializeSchema(schema, schemaResult) {
    if (schemaResult.applicantQualification) {
      this.qualification = schemaResult.applicantQualification.qualification;
    }

    this.options = getDropdownOptions(schema.elements)
    this.type = schema.type;
    this.label = schema.label;

    function getDropdownOptions(array) {
      let arrayOptions = [];
      array.forEach(element => {
        const newOption = { text: `${element}`, value: `${element}` }
        arrayOptions = [...arrayOptions, newOption]
      });
      return arrayOptions
    }
  }
  render(parameters) {
    return <CustomField>
      <DropDownField
        value={(this.qualification) ? (this.qualification) : (this.options[0].value)}
        placeholder={this.qualification}
        onChange={parameters.onChange}
        labelSize='large'
        labelWidth={parameters.graphicProperties.labelWidth}
        inputWidth={parameters.graphicProperties.inputWidth}
        className={parameters.graphicProperties.classNameDropdown}
        labelValue={this.label}
        classNameFields={parameters.graphicProperties.classNameFields}
        disabled={parameters.graphicProperties.disabled}
        options={this.options}
        compact={true}
        search={false}
        clearable={false}
        allowAdditions={false}
      />
    </CustomField>
  }
}