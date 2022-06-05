import { get, post } from "./request"

// 追剧
export const getWatchedHistory = (pageNum = 1) => {
    return post({
        url: '/user/myWatch.json',
        data: {
            pageSize: 10,
            pageNum
        }
    });
}
export const getFollowList = (pageNum = 1) => {
    return post({
        url: '/user/myFollow.json',
        data: {
            pageSize: 10,
            pageNum
        }
    });
}
export const addFollow = (videoId) => {
    return get({
        url: '/user/followVideo.json',
        param: {
            videoId
        }
    });
}
export const removeFollow = videoId => {
    return get({
        url: '/user/cancelFollowVideo.json',
        param: {
            videoId
        }
    });
}

