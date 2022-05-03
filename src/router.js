import VueRouter from 'vue-router';

import Channels from './views/Channels/index.vue';
import Comments from './views/Comments/index.vue';
import Episodes from './views/Episodes/index.vue';
import SubEpisodeList from './views/Episodes/subList.vue';
import SubEpisodeDetail from './views/Episodes/subDetail.vue';
import Users from './views/Users/index.vue';
import Login from './views/Login/index.vue';

const routes = [
    {
        path: '/login',
        component: Login
    }, {
        path: '/channels',
        component: Channels
    }, {
        path: '/comments',
        component: Comments
    }, {
        path: '/episodes',
        component: Episodes,
        children: [{
            path: 'SubList',
            component: SubEpisodeList
        }, {
            path: 'subDetail',
            component: SubEpisodeDetail
        }]
    }, {
        path: '/users',
        component: Users
    }
];

export default new VueRouter({routes});