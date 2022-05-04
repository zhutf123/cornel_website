import axios from "axios";
import router from '../router';

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