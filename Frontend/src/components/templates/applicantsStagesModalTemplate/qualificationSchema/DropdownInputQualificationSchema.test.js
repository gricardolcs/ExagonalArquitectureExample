import DropdownInputQualificationSchema from "./DropdownInputQualificationSchema";
import EnumSchemaType from "./EnumSchemaType";

describe("Serialize qualification schema", () => {
  test("Test 1: Human Development stage", () => {
    const qualificationSchema = new DropdownInputQualificationSchema();
    const schema = {
      type: EnumSchemaType.DROPDOWN,
      label: "Recommended",
      qualification: "YES",
      elements: ["YES", "NO"],
    };
    qualificationSchema.deserializeSchema(schema, {});
    const currentResult = qualificationSchema.qualifyAndSerializeSchema(
      schema.qualification
    );
    const expectedResult =
      '{"type":"DROPDOWN","label":"Recommended","qualification":"YES",'
      +'"options":[{"text":"YES","value":"YES"},{"text":"NO","value":"NO"}]}';
    expect(currentResult).toEqual(expectedResult);
  });
});
