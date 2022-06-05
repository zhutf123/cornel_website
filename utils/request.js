import { queryStringify } from "./util";

function genRequestHeaders(header = {}) {
    return {
        ...header,
        'cookie': 'uid=08df82cfc8fe11ec9fedacde48001122;'
    };
}


const DOMAIN = 'https://wx.ydwlys.com';
export const request = ({url, method = 'GET', param, data, header}) => {
    let api = DOMAIN + url;
    const params = queryStringify(param);
    if (params) {
        api += (api.includes('?') ? '&' : '?') + params;
    }
    return new Promise((resolve, reject) => {
        wx.request({
            url: api,
            method,
            data,
            header: genRequestHeaders(header),
            enableHttp2: true,
            success: (res) => {
                if (res.statusCode === 200) {
                    resolve(res.data);
                    return;
                }
                reject(res);
            },
            fail: reject
        });
    }).catch(e => {
        console.log(e);
        return {};
    });
}

export const get = (params) => {
    return request({...params, method: 'GET'});
};
export const post = (params) => {
    return request({...params, method: 'POST'});
}