export function emptyInstance() {
    return {
        id: '',
        name: '',
        description: '',
        startDate: new Date().toISOString().split('T')[0],
        endDate: '',
        workflowType: '',
        participantsExpectedQuantity: 0
    }
}

const BootcampUtils = {
    emptyInstance
}

export default BootcampUtils