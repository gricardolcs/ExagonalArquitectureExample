import "@testing-library/jest-dom/extend-expect";

import { createBasicAxiosInstance } from "../../APIRESTConnectionFactory";
import { projectTypeFetch } from "./ProjectTypeFetch";

jest.mock("../../APIRESTConnectionFactory");

describe("Fetch Project Types tests", () => {
  const projectType = {
    id: 1,
    name: "QA",
  };
  const projectTypeBodyMock = [projectType];

  test("Fetch Project Type", async () => {
    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.resolve({ data: projectTypeBodyMock }),
      };
    });
    await projectTypeFetch((data) => {
      expect(data).toEqual(projectTypeBodyMock);
    });
  });

  test("Fetch empty", async () => {
    createBasicAxiosInstance.mockImplementation(() => {
      return {
        get: () => Promise.resolve({ data: [] }),
      };
    });
    await projectTypeFetch((data) => {
      expect(data).toEqual([]);
    });
  });
});
