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

// 推荐tab
export function getTabs() {
    return get({
        url: '/user/channelList.json'
    });
}
export function getBannerList() {
    return post({
        url: '/user/bannerList.json'
    });
}
export function getTeleplayList({channel, pageNum = 1}) {
    return post({
        url: '/user/teleplayList.json',
        data: {
            channel,
            pageSize: 10,
            pageNum
        }
    });
}
export function getRankInfoList() {
    return post({
        url: '/user/rankInfoList.json'
    });
}
export function changeRankInfo(rankInfoId, pageNum) {
    return post({
        url: '/user/changeRankInfo.json',
        data: {
            rankInfoId,
            pageSize: 10,
            pageNum
        }
    });
}


export function getVideoInfo(videoId) {
    return get({
        url: '/user/videoInfo.json',
        param: {
            videoId
        }
    });
}