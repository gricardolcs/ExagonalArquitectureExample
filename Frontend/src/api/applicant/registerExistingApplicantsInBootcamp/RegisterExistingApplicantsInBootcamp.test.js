import { createBasicAxiosInstance } from "../../APIRESTConnectionFactory";
import { registerExistingApplicantsInBootcamp } from "./RegisterExistingApplicantsInBootcamp";

jest.mock("../../APIRESTConnectionFactory");

describe("Register Existing Applicants In Bootcamp", () => {
    test("It should response successfully when the bootcamp id exists", async () => {
        const promiseData = {
            registeredApplicants: [-1, -2, -3]
        };

        createBasicAxiosInstance.mockImplementation(() => {
            return {
                put: () =>
                    Promise.resolve({
                        data: promiseData,
                    }),
            };
        });
        await registerExistingApplicantsInBootcamp(-1, [-1, -2, -3], (data) => {
            expect(data).toEqual(promiseData);
        });
    });
})