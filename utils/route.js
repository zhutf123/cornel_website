import { queryStringify } from "./util";

function genUrl(pageName, query) {
    let url = `/pages/${pageName}/index`;
    const querystring = queryStringify(query);
    if (querystring) {
        url += '?' + querystring;
    }
    return url;
}
const go = (pageName, query) => {
    const url = genUrl(pageName, query);
    wx.navigateTo({
        url
    });
};
const back = (delta = 1) => {
    wx.navigateBack({
        delta
    });
};

const relaunch = (pageName, query) => {
    const url = genUrl(pageName, query);
    wx.reLaunch({
        url
    });
};

module.exports = {
    go,
    back,
    relaunch
}