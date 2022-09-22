import moment from "moment";

function getDateTime(date) {
    const actualDate = moment(date);
    const currentDate = moment();
    actualDate.hour(currentDate.hours() - 4);
    actualDate.minute(currentDate.minutes());
    actualDate.second(currentDate.seconds());
    return actualDate;
}

const GetDateTime = {
    getDateTime
}

export default GetDateTime;