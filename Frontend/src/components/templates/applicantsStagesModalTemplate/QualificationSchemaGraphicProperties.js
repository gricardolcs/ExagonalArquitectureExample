import EnumSchemaType from './qualificationSchema/EnumSchemaType'

function getGraphicPropertiesBySchema(schema) {
  let labelWidth = 10
  let inputWidth = 6
  let labelClassName = 'stage-label';
  let labelClassNameGeneric = labelClassName + '-width-generic';
  let labelClassNameLong = labelClassName + '-width-initial';
  let modalClass = 'stage-modal-generic'
  let classNameFields = 'stage-dropdown'
  let classNameDropdown = 'stage-dropdown-option'
  if (schema.type === EnumSchemaType.SINGLE_INPUT) {
    labelWidth = 11;
    inputWidth = 5;
    labelClassName = `${labelClassName} ${labelClassNameGeneric}`;
  } else if (schema.type === EnumSchemaType.MULTIPLE_INPUT) {
    labelWidth = 9;
    inputWidth = 7;
    labelClassName = `${labelClassName} ${labelClassNameGeneric}`;
    modalClass = 'stage-modal';
  }
  if (schema.type === EnumSchemaType.DROPDOWN) {
    labelWidth = 11;
    inputWidth = 5;
    labelClassName = `${labelClassName} ${labelClassNameGeneric}`;
  }
  if (schema.label && schema.label.toString().length > 10) {
    labelClassName = `${labelClassName} ${labelClassNameLong}`;
  }
  return {
    labelWidth,
    inputWidth,
    labelClassName,
    modalClass,
    classNameFields,
    classNameDropdown
  };
}

const qualificationSchemaGraphicProperties = {
  getGraphicPropertiesBySchema,
};

export default qualificationSchemaGraphicProperties;
