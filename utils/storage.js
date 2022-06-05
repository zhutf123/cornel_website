export const getStorage = (key) => {
    return new Promise(resolve => {
        wx.getStorage({
            key,
            success: res => {
                resolve(res.data);
            }
        });
    });
}

export const setStorage = (key, value) => {
    wx.setStorage({
        key,
        data: value
    });
}