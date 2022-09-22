import SingleInputQualificationSchema from './SingleInputQualificationSchema';
import EnumSchemaType from './EnumSchemaType'

describe('Serialize qualification schema', () => {

  test('Test 1: Initial stage', () => {
    const qualificationSchema = new SingleInputQualificationSchema();
    const schema = {
      type: EnumSchemaType.SINGLE_INPUT,
      label: "Assessment(0-5)",
      qualification: 5
    }
    qualificationSchema.deserializeSchema(schema, {});
    const actualResult = qualificationSchema.qualifyAndSerializeSchema(schema.qualification);
    const expectedResult = '{"type":"SINGLE_INPUT","label":"Assessment(0-5)","qualification":5}';
    expect(actualResult).toEqual(expectedResult);
  })

  test('Test 2: Psychometric stage', () => {
    const qualificationSchema = new SingleInputQualificationSchema();
    const schema = {
      type: EnumSchemaType.SINGLE_INPUT,
      label: "Result",
      qualification: 5
    }
    qualificationSchema.deserializeSchema(schema, {});
    const actualResult = qualificationSchema.qualifyAndSerializeSchema(schema.qualification);
    const expectedResult = '{"type":"SINGLE_INPUT","label":"Result","qualification":5}';
    expect(actualResult).toEqual(expectedResult);
  })

  test('Test 3: Technical stage', () => {
    const qualificationSchema = new SingleInputQualificationSchema();
    const schema = {
      type: EnumSchemaType.SINGLE_INPUT,
      label: "Assessment(0-5)",
      qualification: 8
    }
    qualificationSchema.deserializeSchema(schema, {});
    const actualResult = qualificationSchema.qualifyAndSerializeSchema(schema.qualification);
    const expectedResult = '{"type":"SINGLE_INPUT","label":"Assessment(0-5)","qualification":8}';
    expect(actualResult).toEqual(expectedResult);
  })

})
