<template>
  <main class="form-signin">
    <form v-on:submit.prevent="onFormSubmit">
      <h1 class="h3 mb-3 fw-normal">实验室设备管理系统</h1>
      <h1 class="h3 mb-3 fw-normal">请登录</h1>
      <label for="inputEmail" class="visually-hidden">username</label>
      <input
        v-model="username"
        type="text"
        class="form-control"
        placeholder="Username"

        autofocus
      />
      <label for="inputPassword" class="visually-hidden">Password</label>
      <input
        v-model="password"
        type="password"
        class="form-control"
        placeholder="Password"

      />
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Sign in
      </button>
    </form>
  </main>
</template>

<script lang="ts">
import UserInfo from "@/models/UserInfo";
import store from "@/store";
import { Options, Vue } from "vue-class-component";
import { useStore } from "vuex";

@Options({
  store: useStore()
})
export default class SignIn extends Vue {
  private username = "";
  private password = "";

  private onFormSubmit() {
    const object = {
      userId: Number.parseInt(this.username),
      password: this.password,
    };
    const json = JSON.stringify(object);
    fetch("http://localhost:5000/auth/login", {
      method: "POST",
      headers: new Headers({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
      }),
      body: json,
      mode: "cors"
    })
      .then((response) => {
        if (response.ok) {
          return response.json();
        } else {
          if (response.status == 400) {
            alert("登录失败！请检查用户名或密码");
          } else {
            alert("登录失败！请检查用户名或密码");
          }
        }
      })
      .then((responseJson) => {
        const userInfo: UserInfo = new UserInfo();
          if (responseJson.statusCode != 200) {
            alert("登录失败！请检查用户名或密码");
            return;
          }
        userInfo.username = JSON.parse(decodeURIComponent(escape(window.atob((responseJson.content as string).split(".")[1])))).userName;
        userInfo.isLogin = true;
        userInfo.accessToken = responseJson.content;
        store.commit("setUserInfo", userInfo);
        this.$router.push({ path: "/" });
      })
      .catch((error) => console.error(error));
  }
}
</script>

<style scoped>
.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
  height: 100%;
  display: flex;
  align-items: center;
  text-align: center;
}
.form-signin .checkbox {
  font-weight: 400;
}
.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>