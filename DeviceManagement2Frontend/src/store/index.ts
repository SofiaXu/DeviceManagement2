import UserInfo from '@/models/UserInfo'
import { createStore } from 'vuex'

export default createStore({
  state: {
    userInfo: new UserInfo()
  },
  mutations: {
    setUserInfo(state, userInfo: UserInfo) {
      state.userInfo = userInfo;
      if (localStorage.getItem("DM.UserInfo") == null) {
        localStorage.removeItem("DM.UserInfo");
      }
      localStorage.setItem("DM.UserInfo", JSON.stringify(state.userInfo));
    },
    removeUserInfo(state) {
      state.userInfo = new UserInfo();
      if (localStorage.getItem("DM.UserInfo")) {
        localStorage.removeItem("DM.UserInfo");
      }
    }
  },
  actions: {
  },
  modules: {
  }
})
