import { queryStringify } from "./util";
const go = (pageName, query) => {
    let url = `/pages/${pageName}/index`;
    const querystring = queryStringify(query);
    if (querystring) {
        url += '?' + querystring;
    }
    wx.navigateTo({
        url
    });
};
const back = (delta = 1) => {
    wx.navigateBack({
        delta
    });
}

module.exports = {
    go,
    back
}