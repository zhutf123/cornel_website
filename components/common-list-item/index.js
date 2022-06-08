import route from '../../utils/route';

Component({
    properties: {
        item: {
            type: Object,
            value: {}
        }
    },
    methods: {
        jumpToVideo(e) {
            const {id} = e.currentTarget.dataset;
            route.go('videoDetail', {
                videoId: id
            });
        },
        clickLike(e) {
            const {id} = e.currentTarget.dataset;

        }
    }
});