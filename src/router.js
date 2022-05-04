import VueRouter from 'vue-router';

import Channels from './views/Channels/index.vue';
import Comments from './views/Comments/index.vue';
import Episodes from './views/Episodes/index.vue';
import SubEpisodeList from './views/Episodes/subList.vue';
import SubEpisodeDetail from './views/Episodes/subDetail.vue';
import Users from './views/Users/index.vue';
import UserDetail from './views/Users/detail.vue';
import Login from './views/Login/index.vue';
import Tags from './views/Tags/index.vue';
import Banner from './views/Banners/index.vue';
import Publish from './views/Publish/index.vue';

const routes = [
    {
        path: '/publish',
        component: Publish
    }, {
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
            path: 'subList',
            component: SubEpisodeList
        }, {
            path: 'subDetail',
            component: SubEpisodeDetail
        }]
    }, {
        path: '/users',
        component: Users,
        children: [{
            path: 'detail',
            component: UserDetail
        }]
    }, {
        path: '/tags',
        component: Tags
    }, {
        path: '/banners',
        component: Banner
    }
];

export default new VueRouter({routes});