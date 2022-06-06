export const formatTime = date => {
    const year = date.getFullYear()
    const month = date.getMonth() + 1
    const day = date.getDate()
    const hour = date.getHours()
    const minute = date.getMinutes()
    const second = date.getSeconds()

    return `${[year, month, day].map(formatNumber).join('/')} ${[hour, minute, second].map(formatNumber).join(':')}`
}

const formatNumber = n => {
    n = n.toString()
    return n[1] ? n : `0${n}`
}

export const queryStringify = (obj = {}) => {
    const keys = Object.keys(obj);
    if (keys.length === 0) {
        return '';
    }
    return keys.map(key => `${key}=${obj[key]}`).join('&');
}

export const Toast = {
    show(msg = '', duration = 3000) {
        wx.showToast({
            icon: 'none',
            title: msg,
            duration
        });
    }
}