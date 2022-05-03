import App from './App.vue';
import router from './router';
import './apis';

new Vue({
    render: h => h(App),
    components: App,
    router
}).$mount('#app');