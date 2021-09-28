import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import axios from 'axios'
import VueAxios from 'vue-axios'
import UserInfo from './models/UserInfo'

if (localStorage.getItem("DM.UserInfo")) {
    store.state.userInfo = JSON.parse(localStorage.getItem("DM.UserInfo") || JSON.stringify(new UserInfo()));
}

router.beforeEach((to, from, next) => {
    if (to.meta.title) {
        document.title = to.meta.title as string;
    }
    if (to.matched.some(r => r.meta.requireAuth)) {
        if (store.state.userInfo.isLogin) {
            next();
        } else {
            next({
                path: "/login",
                query: { redirect: to.fullPath }
            });
        }
    } else {
        next();
    }
});

createApp(App).use(store).use(router).use(VueAxios, axios.create({
    baseURL: "http://localhost:5000/",
    timeout: 1000
})).mount('#app')
