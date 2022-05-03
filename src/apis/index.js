import axios from "axios";
import router from '../router';

axios.interceptors.response.use(function({data: res}) {
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

/**
private Integer id;
private Boolean vip;
private String channel;
private Integer status;
private String title;
private Integer pageSize;
private Integer pageNum;
private Integer offSet; 
*/
export function getEpisodesList(params) {
    return axios.post('/admin/teleplayList.json', params);
}