import '@testing-library/jest-dom/extend-expect'
import { createBasicAxiosInstance } from '../../APIRESTConnectionFactory';
import { getApplicantsPageByFullNameAndStages } from './ApplicantFetchByFullNameAndStage';

jest.mock('../../APIRESTConnectionFactory');

describe('ApplicantFetchByFullNameAndStage', () => {
    const bootcampId = -1;
    const applicants = [
        {
            id: 1,
            fullName: 'Adrian Monasterios Gutierrez',
            stages: [
                {
                    id: 6,
                    name: 'Initial',
                    isQualified: true
                },
                {
                    id: 7,
                    name: 'English',
                    isQualified: true
                }
            ]
        },
        {
            id: 2,
            fullName: 'Fernando Mercado Flores',
            stages: [
                {
                    id: 6,
                    name: 'Initial',
                    isQualified: true
                },
                {
                    id: 7,
                    name: 'English',
                    isQualified: false
                }
            ]
        },
        {
            id: 3,
            fullName: 'Fernando Montecinos Mamani',
            stages: [
                {
                    id: 6,
                    name: 'Initial',
                    isQualified: true
                },
                {
                    id: 7,
                    name: 'English',
                    isQualified: false
                }
            ]
        },
        {
            id: 4,
            fullName: 'Uriel Eleazar Tejeiro Garcia',
            stages: [
                {
                    id: 6,
                    name: 'Initial',
                    isQualified: true
                },
                {
                    id: 7,
                    name: 'English',
                    isQualified: true
                }
            ]
        },
        {
            id: 5,
            fullName: 'Carlos Gutierrez Flores',
            stages: [
                {
                    id: 6,
                    name: 'Initial',
                    isQualified: true
                },
                {
                    id: 7,
                    name: 'English',
                    isQualified: false
                }
            ]
        },
        {
            id: 6,
            fullName: 'Jorge Antonio Loayza Rios',
            stages: [
                {
                    id: 6,
                    name: 'Initial',
                    isQualified: true
                },
                {
                    id: 7,
                    name: 'English',
                    isQualified: false
                }
            ]
        }
    ]

    const getMockApplicantsByFullName = ({ search }) => {
        const content = applicants
            .filter((applicant) => applicant.fullName.includes(search))
        return {
            content
        }
    }

    const getMockApplicantsByStages = ({ filters }) => {
        const content = applicants.filter((applicant) => {
            const stage = applicant.stages
                .filter((stage) => filters.includes(stage.id))[0];
            return !stage.isQualified;
        })
        return {
            content
        }
    }

    const getMockApplicantsByFullNameAndStages = ({ search, filters }) => {
        const content = applicants.filter((applicant) => {
            if (applicant.fullName.includes(search)) {
                const stage = applicant.stages
                    .filter((stage) => filters.includes(stage.id))[0];
                return !stage.isQualified;
            }
        })
        return {
            content,
            totalPages: 1,
            totalElements: content.length,
            number: 1
        }
    }

    test('Test 1: should get a applicants page when searching by full name', async () => {
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                get: (url, { params }) => {
                    return Promise.resolve({
                        data: getMockApplicantsByFullName(params)
                    })
                }
            }
        });
        const fullName = 'Fernando'
        const filters = []
        getApplicantsPageByFullNameAndStages(bootcampId, fullName, filters, (data) => {
            expect(data.content).toHaveLength(2);
        });
    })

    test('Test 2: should get applicants when filtering by stage', async () => {
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                get: (url, { params }) => Promise.resolve({
                    data: getMockApplicantsByStages(params)
                })
            }
        });
        const fullName = ''
        const filters = [7]
        getApplicantsPageByFullNameAndStages(bootcampId, fullName, filters, (data) => {
            expect(data.content).toHaveLength(4);
        });
    })

    test('Test 3: should get applicants when searching by full name and filtering stage', async () => {
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                get: (url, { params }) => Promise.resolve({
                    data: getMockApplicantsByFullNameAndStages(params)
                })
            }
        });
        const fullName = 'Gutierrez'
        const filters = [7]
        getApplicantsPageByFullNameAndStages(bootcampId, fullName, filters, (data) => {
            expect(data.content).toHaveLength(1);
        });
    })

    test('Test 4: should throw an error when a bootcamp doesn\'t exist', async () => {
        const messageExpected = 'The bootcamp doesn\'t have applicants';
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                post: () => Promise.reject({ response: { data: messageExpected } })
            }
        });
        const fullName = 'Gutierrez'
        const filters = []
        await expect(getApplicantsPageByFullNameAndStages(bootcampId, fullName, filters))
            .rejects.toThrowError(messageExpected);
    })

    test('Test 5: should throw an error when a stage doesn\'t exist', async () => {
        const messageExpected = 'The Initial stage doesn\'t exist';
        createBasicAxiosInstance.mockImplementation(() => {
            return {
                post: () => Promise.reject({ response: { data: messageExpected } })
            }
        });
        const stage = 'Initial'
        await expect(getApplicantsPageByFullNameAndStages(bootcampId, '', stage))
            .rejects.toThrowError(messageExpected)
    })
})
