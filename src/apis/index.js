import axios from "axios";
import router from '../router';

axios.interceptors.request.use(function(config) {
    if (config.data) {
        const data = config.data;
        const newData = {};
        Object.keys(data).map(key => {
            if (data[key] !== '') {
                newData[key] = data[key];
            }
        });
        config.data = newData;
    }

    return config;
});
axios.interceptors.response.use(function(resp) {
    const res = resp.data;
    if (res.status === 1000) {
        if (res.msg) {
            window.Vue.prototype.$message.error(res.msg);
        }
        router.push('/login');
        return {
            status: -1
        };
    }
    return res;
});

export function login(params) {
    return axios.post('/admin/login.json', params);
}

// 剧集接口
export function getEpisodeList(params) {
    return axios.post('/admin/teleplayList.json', params);
}
export function updateEpisode(params) {
    return axios.post('/admin/operateTeleplay.json', params);
}
export function getSubEpisodeList(params) {
    return axios.post('/admin/teleplayVideoList.json', params);
}
export function getSubEpisodeDetail(params) {
    return axios.post('/admin/teleplayVideoDetail.json', params);
}
export function updateSubEpisode(params) {
    return axios.post('/admin/operateTeleplayVideo.json', params);
}
export function suggestEpisode(input) {
    return axios.get(`/admin/suggestTeleplay.json?name=${input}`);
}


// 频道接口
export function getChannelList(params) {
    return axios.post('/admin/channelList.json', params);
}
export function delChannel(channelId) {
    return axios.get(`/admin/delChannel.json?id=${channelId}`);
}
export function updateChannel(params) {
    return axios.post('/admin/operateChannel.json', params);
}
export function suggestChannel(input) {
    return axios.get(`/admin/suggestChannel.json?name=${input}`);
}

// 聚合标签接口
export function getTagsList(params) {
    return axios.post('/admin/channelGroupList.json', params);
}
export function updateTag(params) {
    return axios.post('/admin/operateChannelGroup.json', params);
}
export function delTag(id) {
    return axios.get(`/admin/delChannelGroup.json?id=${id}`);
}
export function offlineTag({groupId, channelId}) {
    return axios.get(`/admin/removeChildChannel.json?groupId=${groupId}&channelId=${channelId}`)
}

// 广告系统接口
export function getBannerList(params) {
    return axios.post('/admin/bannerList.json', params);
}
export function updateBanner(params) {
    return axios.post('/admin/operateBannerInfo.json', params);
}

// 用户接口
export function getUserList(params) {
    return axios.post('/admin/userList.json', params);
}
export function getUserPayList(userId) {
    return axios.get(`/admin/queryUserPayList.json?userId=${userId}`);
}