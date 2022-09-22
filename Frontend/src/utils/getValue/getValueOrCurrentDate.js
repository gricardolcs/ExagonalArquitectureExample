import moment from "moment";

function getValueOrCurrentDate(value) {
    return value === null || value === ''? moment().format('YYYY-MM-DD'): moment(value).format('YYYY-MM-DD');
}

const GetDateUtils = {
    getValueOrCurrentDate
}

export default GetDateUtils;
