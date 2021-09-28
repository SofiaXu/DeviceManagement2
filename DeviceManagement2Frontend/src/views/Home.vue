<template>
  <div>
    <header
      class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow"
    >
      <router-link class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" to="/"
        >实验室设备管理系统</router-link
      >
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="#" v-on:click.prevent="logout()">{{ username }}</a>
        </li>
      </ul>
    </header>
    <div class="container-fluid">
      <div class="row">
        <sidebar class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
          <sidebar-item
            v-for="(nav, index) in navList"
            :key="index"
            :to="nav.To"
            >{{ nav.Name }}</sidebar-item
          >
        </sidebar>
        <router-view
          class="col-md-9 ms-sm-auto col-lg-10 px-md-4"
        ></router-view>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Sidebar from "@/components/Sidebar.vue";
import SidebarItem from "@/components/SidebarItem.vue";
import NavItem from "@/models/NavItem";
import store from "@/store";
import { Options, Vue } from "vue-class-component";
import { useStore } from "vuex";
@Options({
  components: {
    Sidebar,
    SidebarItem,
  },
  store: useStore(),
})
export default class Home extends Vue {
  private navList: NavItem[] = [
    { Name: "设备信息列表", To: "/DeviceBaseInformationList" } as NavItem,
    { Name: "设备批次列表", To: "/DeviceBatchList" } as NavItem,
    { Name: "报修列表", To: "/DeviceMaintainList" } as NavItem,
    { Name: "设备编号列表", To: "/DeviceList" } as NavItem,
    { Name: "生产商列表", To: "/DeviceProviderList" } as NavItem,
    { Name: "设备状态列表", To: "/DeviceStatusList" } as NavItem,
    { Name: "设备类型列表", To: "/DeviceTypeList" } as NavItem,
    { Name: "职工列表", To: "/EmployeeList" } as NavItem,
    { Name: "位置列表", To: "/LocationList" } as NavItem,
    { Name: "维修厂列表", To: "/MaintainerList" } as NavItem,
    { Name: "报废设备列表", To: "/UselessDeviceList" } as NavItem,
    { Name: "用户列表", To: "/UserList" } as NavItem,
    { Name: "用户类型列表", To: "/UserTypeList" } as NavItem,
  ];
  private username = store.state.userInfo.username;

  logout() {
    store.commit("removeUserInfo");
    this.$router.push("/Login")
  }
}
</script>

<style scoped>
</style>