import { createBasicAxiosInstance } from "../../APIRESTConnectionFactory";
import { getApplicantsByBootcampId } from "./ApplicantsFetchByBootcamp";

jest.mock("../../APIRESTConnectionFactory");

describe("Applicants fetch by bootcamp", () => {
  const bootcampId = -1;
  const page = 0;
  const size = 20;
  const applicants = [
    { id: -1, fullName: "Pedro Choque", stages: [] },
    { id: -2, fullName: "Andres david lizandro mamani", stages: [] },
    { id: -3, fullName: "Alex Gabino Flores Vargas", stages: [] },
    { id: -4, fullName: "Gabriel Jonathan Rojas Montaño", stages: [] },
    { id: -5, fullName: "Pablo Nataniel Flores Garcia", stages: [] },
  ];
  const getMockApplicants = () => {
    const content = applicants;
    return {
      content,
      totalPages: 0,
      totalElements: content.length,
      number: 3,
    };
  };
  test("It should response successfully when the bootcamp id exists", async () => {
    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: ({ params }) =>
          Promise.resolve({
            data: getMockApplicants(params),
          }),
      };
    });
    await getApplicantsByBootcampId(bootcampId, false, page, size, (data) => {
      expect(data).toEqual(getMockApplicants());
    });
  });

  test("It should response fail when the bootcamp does not exist", async () => {
    const messageError = "The bootcamp does not exist";
    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.reject({ response: { data: messageError } }),
      };
    });
    expect(await getApplicantsByBootcampId.bind(bootcampId)).rejects.toThrow(
      messageError
    );
  });
});
