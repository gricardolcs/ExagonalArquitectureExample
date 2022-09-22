import "@testing-library/jest-dom/extend-expect";

import { createBasicAxiosInstance } from "../../APIRESTConnectionFactory";
import { departmentFetch } from "./DepartmentFetch";

jest.mock("../../APIRESTConnectionFactory");

describe("Fetch Departments tests", () => {
  const department = {
    id: 1,
    name: "QA",
  };
  const departmentBodyMock = [department];

  test("Fetch Department", async () => {
    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.resolve({ data: departmentBodyMock }),
      };
    });
    await departmentFetch((data) => {
      expect(data).toEqual(departmentBodyMock);
    });
  });

  test("Fetch empty", async () => {
    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.resolve({ data: [] }),
      };
    });
    await departmentFetch((data) => {
      expect(data).toEqual([]);
    });
  });
});
