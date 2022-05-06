export const ENTRIES = [
    {
        path: '/publish',
        name: '发布'
    },
    {
        path: '/episodes',
        type: 'manage',
        name: '剧情管理'
    },
    // {
    //     path: '/comments',
    //     type: 'manage',
    //     name: '评论管理'
    // },
    {
        path: '/channels',
        type: 'manage',
        name: '频道管理'
    },
    {
        path: '/tags',
        type: 'manage',
        name: '聚合标签'
    },
    {
        path: '/rank',
        type: 'manage',
        name: '排行榜'
    },
    {
        path: '/banners',
        type: 'manage',
        name: '广告系统'
    },
    {
        path: '/users',
        type: 'manage',
        name: '用户管理'
    },
    {
        path: '/contentAnalyse',
        type: 'data',
        name: '内容分析'
    }
];
export const CHANNEL_TAGS = [{
    name: '剧集标签',
    value: 0
}, {
    name: '渠道标签',
    value: 1
}, {
    name: '聚合标签',
    value: 2
}, {
    name: '其他标签',
    value: 3
}];