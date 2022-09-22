import { createBasicAxiosInstance } from "../../APIRESTConnectionFactory";
import { uploadApplicantsByCsvFile } from "./ApplicantsFileUpload";

jest.mock('../../APIRESTConnectionFactory');

describe("Applicants file upload Tests", () => {
    const bootcampId = -98765432101234;
    test("It should respond successfully when the applicants file is correct", async () => {
        const uploadResult = {
            applicantsIds: [
                -12345678909123
            ],
            failedMessage: "",
            failedRows: []
        };
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                post: () => Promise.resolve({
                    data: uploadResult
                })
            }
        });
        const applicants = [
            {
                fullName: 'Pedro Choque',
                emailAddress: 'mijael.mabo@gmail.com',
                phoneNumber: '+59171548221',
                birthday: '12/15/1989',
                currentCity: 'Cochabamba',
                'What is your career?': 'System Engineering'
            }
        ]
        const response = await uploadApplicantsByCsvFile(bootcampId, applicants);
        expect(response.failedRows).toEqual([]);
        expect(response.failedMessage).toBe("");
    });

    test("It should show a message of error when the csv file is invalid", async () => {
        const messageError = "Invalid file";
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                post: () => Promise.reject({ response: { data: messageError } })
            }
        });
        const file = 'applicant_with_errors.csv';
        expect(uploadApplicantsByCsvFile.bind(bootcampId, file)).rejects.toThrow(messageError);
    });

    test("It should show a message of error when the csv file has invalid applicants", async () => {
        const messageError = "Error when loading the listed applicants row, please review" +
            " them in the file applicant_with_errors.csv";
        const uploadResult = {
            applicantsIds: [],
            failedMessage: messageError,
            failedRows: [1]
        }
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                post: () => Promise.resolve({
                    data: uploadResult
                })
            }
        });
        const formData = new FormData();
        const file = 'applicant_with_errors.csv';
        formData.append('file', file);
        const response = await uploadApplicantsByCsvFile(bootcampId, formData);
        expect(response.applicantsIds).toEqual([]);
        expect(response.failedMessage).toEqual(messageError);
        expect(response.failedRows).toEqual([1]);
    });
})
