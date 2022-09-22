import SingleInputQualificationSchema from './SingleInputQualificationSchema';
import MultiplesInputsQualificationSchema from './MultiplesInputsQualificationSchema';
import DropdownInputQualificationSchema from './DropdownInputQualificationSchema';
import EnumSchemaType from './EnumSchemaType'

const getBySchemaType = (schemaType) => {
  let qualificationSchema;
  switch (schemaType) {
    case EnumSchemaType.SINGLE_INPUT:
      qualificationSchema = new SingleInputQualificationSchema();
      break;

    case EnumSchemaType.MULTIPLE_INPUT:
      qualificationSchema = new MultiplesInputsQualificationSchema();
      break;

    case EnumSchemaType.DROPDOWN:
      qualificationSchema = new DropdownInputQualificationSchema();
      break;

    default:
      qualificationSchema = new SingleInputQualificationSchema();
      break;
  }
  return qualificationSchema;
}

const qualificationSchemaFactory = {
  getBySchemaType
};

export default qualificationSchemaFactory;
