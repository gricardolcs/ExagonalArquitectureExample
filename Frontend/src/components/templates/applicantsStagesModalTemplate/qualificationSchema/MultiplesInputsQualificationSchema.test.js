import MultiplesInputsQualificationSchema from './MultiplesInputsQualificationSchema';
import EnumSchemaType from './EnumSchemaType'

describe('Serialize qualification schema', () => {

  test('Test 1: English stage', () => {
    const qualificationSchema = new MultiplesInputsQualificationSchema();
    const schema = {
      type: EnumSchemaType.MULTIPLE_INPUT,
      labels: ["Reading", "Listening", "Writing", "Speaking", "Grade"],
      qualifications: [90, 90, 90, 90, 90]
    }
    qualificationSchema.deserializeSchema(schema, {});
    const actualResult = qualificationSchema.qualifyAndSerializeSchema(schema.qualifications);
    const expectedResult = '{"type":"MULTIPLE_INPUT","labels":["Reading","Listening","Writing","Speaking","Grade"],"qualifications":[90,90,90,90,90]}';
    expect(actualResult).toEqual(expectedResult);
  })
})
