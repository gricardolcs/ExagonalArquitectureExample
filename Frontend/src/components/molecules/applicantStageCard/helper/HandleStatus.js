import EnumQualificationStatus from "../../../organisms/stagePreviewOrganism/EnumQualificationStatus";

export function verifyStatus(status) {
    return status === EnumQualificationStatus.IN_PROGRESS
           || status === EnumQualificationStatus.FAILED 
           || status === EnumQualificationStatus.WITHDRAW
           || status === EnumQualificationStatus.ON_HOLD;
}

const HandleStatus = {
    verifyStatus
}

export default HandleStatus;
