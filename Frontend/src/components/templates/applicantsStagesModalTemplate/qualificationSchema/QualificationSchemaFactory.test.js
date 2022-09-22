import QualificationSchemaFactory from './QualificationSchemaFactory';
import SingleInputQualificationSchema from './SingleInputQualificationSchema';
import MultiplesInputsQualificationSchema from './MultiplesInputsQualificationSchema';
import DropdownInputQualificationSchema from  './DropdownInputQualificationSchema';
import EnumSchemaType from './EnumSchemaType'

describe('Factory qualification schema', () => {

  test('Test 1: Verify it is possible to create a instance of "SingleInputQualificationSchema"', () => {
    const qualificationSchema = QualificationSchemaFactory.getBySchemaType(EnumSchemaType.SINGLE_INPUT);
    expect(qualificationSchema).toBeInstanceOf(SingleInputQualificationSchema);
  })

  test('Test 2: Verify it is possible to create a instance of "MultiplesInputsQualificationSchema" ', () => {
    let qualificationSchema = QualificationSchemaFactory.getBySchemaType(EnumSchemaType.MULTIPLE_INPUT);
    expect(qualificationSchema).toBeInstanceOf(MultiplesInputsQualificationSchema);
  })

  test('Test 3: Verify it is possible to create a instance of "DropdownInputQualificationSchema"', () => {
    const qualificationSchema = QualificationSchemaFactory.getBySchemaType(EnumSchemaType.DROPDOWN);
    expect(qualificationSchema).toBeInstanceOf(DropdownInputQualificationSchema);
  })
})
