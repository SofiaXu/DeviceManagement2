<template>
  <div class="d-flex flex-column">
    <div class="row flex-row my-3">
      <div class="col-auto">
        <button type="button" class="btn btn-primary" @click="showCreateBox()">
          ➕
        </button>
      </div>

    </div>
    <div class="row">
      <table class="table table-striped">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">用户名</th>
            <th scope="col">用户真实姓名</th>
            <th scope="col">用户年龄</th>
            <th scope="col">用户性别</th>
            <th scope="col">用户组</th>
            <th scope="col">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(user, index) in users" :key="index">
            <td>{{ user.userId }}</td>
            <td>{{ user.userName }}</td>
            <td>{{ user.userRealName }}</td>
            <td>{{ user.userAge }}</td>
            <td>{{ user.userGender == 1 ? "男" : "女" }}</td>
            <td>{{ user.userType.userTypeName }}</td>
            <td>
              <button
                type="button"
                class="btn btn-primary"
                @click="editUser(user)"
              >
                ✏
              </button>
              <button
                type="button"
                class="ms-2 btn btn-secondary"
                @click="deleteUser(user.userId)"
              >
                🗑️
              </button>
              <button
                type="button"
                class="ms-2 btn btn-secondary"
                @click="changePassword(user.userId)"
              >
                🔒
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="row">
        <nav aria-label="Page navigation example">
          <ul class="pagination">
            <li class="page-item" :class="{ disabled: page.currentPage == 1 }">
              <a
                class="page-link"
                href="#"
                aria-label="Previous"
                v-on:click.prevent="previousPage()"
              >
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            <li
              class="page-item"
              :class="{ disabled: users.length != page.pageSize }"
            >
              <a
                class="page-link"
                href="#"
                aria-label="Next"
                v-on:click.prevent="nextPage()"
              >
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
        </nav>
      </div>
      <EditableModal
        ref="createBox"
        v-on:save="onCreateSave()"
        v-on:close="onCreateClose()"
      >
        <template v-slot:title>
          <h5>添加</h5>
        </template>
        <template v-slot:default>
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">用户名</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newUser.userName"

              />
            </div>
          </div>
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">用户真实姓名</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newUser.userRealName"

              />
            </div>
          </div>
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">用户年龄</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newUser.userAge"

              />
            </div>
          </div>
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">用户性别</label>
            <div class="col-sm-9">
              <div class="form-check form-check-inline">
                <input
                  class="form-check-input"
                  type="radio"
                  value="1"
                  v-model="newUser.userGender"
                />
                <label class="form-check-label">男</label>
              </div>
              <div class="form-check form-check-inline">
                <input
                  class="form-check-input"
                  type="radio"
                  value="2"
                  v-model="newUser.userGender"
                />
                <label class="form-check-label">女</label>
              </div>
            </div>
          </div>
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">用户组</label>
            <div class="col-sm-9">
              <select class="form-select" v-model="newUser.userType.userTypeId">
                <option
                  v-for="userType in userTypes"
                  :key="userType.userTypeId"
                  :value="userType.userTypeId"
                >
                  {{ userType.userTypeName }}
                </option>
              </select>
            </div>
          </div>
        </template>
      </EditableModal>
      <EditableModal
        ref="editBox"
        v-on:save="onEditSave()"
        v-on:close="getData()"
      >
        <template v-slot:title>
          <h5>编辑 {{ editableUser.userId }}</h5>
        </template>
        <template v-slot:default>
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">用户名</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableUser.userName"

              />
            </div>
          </div>
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">用户真实姓名</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableUser.userRealName"

              />
            </div>
          </div>
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">用户年龄</label>
            <div class="col-sm-9">
              <input
                type="number"
                class="form-control"
                v-model="editableUser.userAge"

              />
            </div>
          </div>
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">用户性别</label>
            <div class="col-sm-9">
              <div class="form-check form-check-inline">
                <input
                  class="form-check-input"
                  type="radio"
                  value="1"
                  v-model="editableUser.userGender"
                />
                <label class="form-check-label" for="inlineRadio1">男</label>
              </div>
              <div class="form-check form-check-inline">
                <input
                  class="form-check-input"
                  type="radio"
                  value="2"
                  v-model="editableUser.userGender"
                />
                <label class="form-check-label" for="inlineRadio2">女</label>
              </div>
            </div>
          </div>
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">用户组</label>
            <div class="col-sm-9">
              <select
                class="form-select"
                v-model="editableUser.userType.userTypeId"
              >
                <option
                  v-for="userType in userTypes"
                  :key="userType.userTypeId"
                  :value="userType.userTypeId"
                >
                  {{ userType.userTypeName }}
                </option>
              </select>
            </div>
          </div>
        </template>
      </EditableModal>
      <EditableModal
        ref="changePasswordBox"
        v-on:save="onChangePasswordSave()"
        v-on:close="onChangePasswordClose()"
      >
        <template v-slot:title>
          <h5>更改密码 {{ editableUser.id }}</h5>
        </template>
        <template v-slot:default>
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">新密码</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="changePasswordRequest.password"

              />
            </div>
          </div>
        </template>
      </EditableModal>
    </div>
  </div>
</template>

<script lang="ts">
import { User } from "@/models/User";
import { Options, Vue } from "vue-class-component";
import Pagination from "@/models/Pagination";
import { useStore } from "vuex";
import store from "@/store";
import EditableModal from "@/components/EditableModal.vue";
import { UserType } from "@/models/UserType";
import { ChangePasswordRequest } from "@/models/ChangePasswordRequest";

@Options({
  store: useStore(),
  components: {
    EditableModal,
  },
})
export default class UserList extends Vue {
  private users: User[] = [];
  private searchText = "";
  private page: Pagination = new Pagination();
  private accessToken = store.state.userInfo.accessToken;
  private newUser: User = new User();
  private editableUser: User = new User();
  private userTypes: UserType[] = [];
  private changePasswordRequest: ChangePasswordRequest =
    new ChangePasswordRequest();

  mounted() {
    this.getData();
    this.getListData();
    this.newUser.userType = new UserType();
  }

  deleteUser(id: number) {
    if (!confirm(`是否删除 ID: ${id}`)) {
      return;
    }
    fetch(`http://localhost:5000/api/user/delete/${id}`, {
      method: "DELETE",
      headers: new Headers({
        Authorization: "Bearer " + this.accessToken,
        Accept: "application/json",
      }),
      mode: "cors",
    })
      .then((response) => {
        return response.json();
      })
      .then((responseJson) => {
        if (responseJson.statusCode != 200) {
          alert("出现错误，请重试");
          return;
        }
        alert("删除成功");
        this.getData();
      })
      .catch((error) => console.error(error));
  }

  editUser(user: User) {
    this.editableUser = user;
    (this.$refs.editBox as EditableModal).show();
  }

  showCreateBox() {
    this.newUser = new User();
    this.newUser.userType = new UserType();
    (this.$refs.createBox as EditableModal).show();
  }

  onEditSave() {
    let requestJson = JSON.stringify(this.editableUser);
    fetch(`http://localhost:5000/api/user/edit`, {
      method: "POST",
      headers: new Headers({
        Authorization: "Bearer " + this.accessToken,
        Accept: "application/json",
        "Content-Type": "application/json",
      }),
      mode: "cors",
      body: requestJson,
    })
      .then((response) => {
        return response.json();
      })
      .then((responseJson) => {
        if (responseJson.statusCode != 200) {
          alert("出现错误，请重试");
          return;
        }
        alert("修改成功");
        this.getData();
      })
      .catch((error) => console.error(error));
  }

  onCreateSave() {
    let requestJson = JSON.stringify(this.newUser);
    fetch(`http://localhost:5000/api/user`, {
      method: "POST",
      headers: new Headers({
        Authorization: "Bearer " + this.accessToken,
        Accept: "application/json",
        "Content-Type": "application/json",
      }),
      mode: "cors",
      body: requestJson,
    })
      .then((response) => {
        return response.json();
      })
      .then((responseJson) => {
        if (responseJson.statusCode != 200) {
          alert("出现错误，请重试");
          return;
        }
        alert("添加成功");
        this.getData();
        this.onCreateClose();
      })
      .catch((error) => console.error(error));
  }

  onCreateClose() {
    return;
  }

  getData() {
    fetch(
      `http://localhost:5000/api/user?pageSize=${this.page.PageSize}&pageIndex=${this.page.CurrentPage}`,
      {
        method: "GET",
        headers: new Headers({
          Authorization: "Bearer " + this.accessToken,
        }),
        mode: "cors",
      }
    )
      .then((response) => {
        return response.json();
      })
      .then((responseJson) => {
        if (responseJson.statusCode != 200) {
          alert("出现错误，请重试");
          return;
        }
        this.users = responseJson.content as User[];
      })
      .catch((error) => console.error(error));
  }

  getListData() {
    fetch(`http://localhost:5000/api/userType`, {
      method: "GET",
      headers: new Headers({
        Authorization: "Bearer " + this.accessToken,
      }),
      mode: "cors",
    })
      .then((response) => {
        return response.json();
      })
      .then((responseJson) => {
        if (responseJson.statusCode != 200) {
          alert("出现错误，请重试");
          return;
        }
        this.userTypes = responseJson.content as UserType[];
      })
      .catch((error) => console.error(error));
  }

  nextPage() {
    this.page.CurrentPage++;
    this.getData();
  }

  previousPage() {
    this.page.CurrentPage--;
    this.getData();
  }

  changePassword(id: number) {
    this.changePasswordRequest.id = id;
    (this.$refs.changePasswordBox as EditableModal).show();
  }

  onChangePasswordSave() {
    let requestJson = JSON.stringify(this.changePasswordRequest);
    fetch(`http://localhost:5000/api/user/changePassword/${this.changePasswordRequest.id}`, {
      method: "POST",
      headers: new Headers({
        Authorization: "Bearer " + this.accessToken,
        Accept: "application/json",
        "Content-Type": "application/json",
      }),
      mode: "cors",
      body: requestJson,
    })
      .then((response) => {
        return response.json();
      })
      .then((responseJson) => {
        if (responseJson.statusCode != 200) {
          alert("出现错误，请重试");
          return;
        }
        alert("修改成功");
        this.getData();
      })
      .catch((error) => console.error(error));
  }

  onChangePasswordClose() {
    this.changePasswordRequest = new ChangePasswordRequest();
  }
}
</script>
