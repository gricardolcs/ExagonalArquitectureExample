import APIRESTConnectionFactory from '../../APIRESTConnectionFactory';
import { fetchAllApplicantWorkflowsByApplicantId } from './FetchAllApplicantWorkflowsByApplicantId';

jest.mock('../../APIRESTConnectionFactory');

describe("Fetch all applicant workflows by applicant id", () => {
    test("Test 1: Fetch all applicant workflows succesfully", async () => {
        const expectedResult = [
            {
                applicantId: 1,
                bootcampId: 1,
                applicantQualifications: [
                    {
                        stageId: 1,
                        stageName: "Initial",
                        comment: "Passed successfully",
                        applicantQualification: [
                            {
                                label: "Score:",
                                qualification: 5,
                                type: "NUMERIC_INPUT"
                            }
                        ],
                        qualificationStatus: "PASSED",
                        isEnglishType: false
                    },
                    {
                        stageId: 2,
                        stageName: "Psychometric",
                        comment: "Passed successfully",
                        applicantQualification: [
                            {
                                label: "CA:",
                                qualification: 6,
                                type: "NUMERIC_INPUT"
                            },
                            {
                                label: "Mixed Control:",
                                qualification: 5,
                                type: "NUMERIC_INPUT"
                            }
                        ],
                        qualificationStatus: "PASSED",
                        isEnglishType: false
                    },
                    {
                        stageId: 3,
                        stageName: "Dev Test",
                        comment: "Passed successfully",
                        applicantQualification: [
                            {
                                label: "Score:",
                                qualification: 56,
                                type: "NUMERIC_INPUT"
                            }
                        ],
                        qualificationStatus: "PASSED",
                        isEnglishType: false
                    },
                    {
                        stageId: 4,
                        stageName: "English",
                        comment: "Passed successfully",
                        applicantQualification: [
                            {
                                label: "Reading",
                                qualification: 56,
                                type: "NUMERIC_INPUT"
                            },
                            {
                                label: "Listening",
                                qualification: 56,
                                type: "NUMERIC_INPUT"
                            },
                            {
                                label: "Speaking",
                                qualification: 56,
                                type: "NUMERIC_INPUT"
                            },
                            {
                                label: "Writing",
                                qualification: 56,
                                type: "NUMERIC_INPUT"
                            },
                            {
                                label: "Score(AVG):",
                                qualification: 56,
                                type: "NUMERIC_INPUT"
                            }
                        ],
                        qualificationStatus: "PASSED",
                        isEnglishType: true
                    },
                    {
                        stageId: 5,
                        stageName: "HD",
                        comment: "Passed successfully",
                        applicantQualification: [
                            {
                                label: "Recommended:",
                                elements: [
                                    "YES",
                                    "NO"
                                ],
                                selectedElement: "YES",
                                type: "DROPDOWN_INPUT"
                            },
                            {
                                label: "Upload Report:",
                                type: "UPLOAD_FILE_INPUT"
                            }
                        ],
                        qualificationStatus: "PASSED",
                        isEnglishType: false
                    },
                    {
                        stageId: 6,
                        stageName: "Contract",
                        comment: "Passed successfully",
                        applicantQualification: [
                            {
                                label: "Status:",
                                elements: [
                                    "Signed",
                                    "On Hold",
                                    "Declined"
                                ],
                                selectedElement: "Signed",
                                type: "DROPDOWN_INPUT"
                            }
                        ],
                        qualificationStatus: "PASSED",
                        isEnglishType: false
                    }
                ]
            }
        ]

        APIRESTConnectionFactory.createBasicAxiosInstance.mockImplementation(() => {
            return {
                get: () => Promise.resolve({ data: expectedResult }),
            }
        });
        await fetchAllApplicantWorkflowsByApplicantId(1, (response) => {
            expect(response).toEqual(expectedResult);
        });
    });

    test("Test 2: Fetch all applicant workflows empty", async () => {
        const expectedResult = []
        APIRESTConnectionFactory.createBasicAxiosInstance.mockImplementation(() => {
            return {
                get: () => Promise.resolve({ data: expectedResult }),
            }
        });
        await fetchAllApplicantWorkflowsByApplicantId(1, (response) => {
            expect(response).toEqual(expectedResult);
        });
    });
        
});