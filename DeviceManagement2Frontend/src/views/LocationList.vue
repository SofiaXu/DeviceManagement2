<template>
  <div class="d-flex flex-column">
    <div class="row flex-row my-3">
      <div class="col-auto">
        <button type="button" class="btn btn-primary" @click="showCreateBox()">➕
          
        </button>
      </div>

    </div>
    <div class="row">
      <table class="table table-striped">
        <thead>
          <tr>            <th scope="col">位置ID</th>
            <th scope="col">详细位置</th>
<th scope="col">操作</th>
          </tr>
        </thead>
        <tbody>          <tr v-for="(model, index) in models" :key="index">
            <td>{{ model.locationId }}</td>
            <td>{{ model.locationName }}</td>
            <td>
              <button
                type="button"
                class="btn btn-primary"
                @click="editModel(model)"
              >
                ✏
              </button>
              <button
                type="button"
                class="ms-2 btn btn-secondary"
                @click="deleteModel(model.locationId)"
              >
                🗑️
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="row">
        <nav aria-label="Page navigation example">
          <ul class="pagination">
            <li class="page-item" :class="{ disabled: page.currentPage == 1 }">
              <a class="page-link" href="#" aria-label="Previous" v-on:click.prevent="previousPage()">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            <li
              class="page-item"
              :class="{ disabled: models.length != page.pageSize }"
            >
              <a class="page-link" href="#" aria-label="Next" v-on:click.prevent="nextPage()">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </ul>
        </nav>
      </div>      <EditableModal
        ref="createBox"
        v-on:save="onCreateSave()"
        v-on:close="onCreateClose()"
      >
        <template v-slot:title>
          <h5>添加</h5>
        </template>
        <template v-slot:default>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">位置ID</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newModel.locationId"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">详细位置</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newModel.locationName"

              />
            </div>
          </div>        </template>
      </EditableModal>      <EditableModal ref="editBox" v-on:save="onEditSave()" v-on:close="getData()">
        <template v-slot:title>
          <h5>编辑</h5>
        </template>
        <template v-slot:default>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">位置ID</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableModel.locationId"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">详细位置</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableModel.locationName"

              />
            </div>
          </div>        </template>
      </EditableModal>
    </div>
  </div>
</template><script lang="ts">
import { Location } from "@/models/Location";
import { Options, Vue } from "vue-class-component";
import Pagination from "@/models/Pagination";
import { useStore } from "vuex";
import store from "@/store";
import EditableModal from "@/components/EditableModal.vue";

@Options({
  store: useStore(),
  components: {
    EditableModal,
  },
})
export default class LocationList extends Vue {
  private models: Location[] = [];
  private searchText = "";
  private page: Pagination = new Pagination();
  private accessToken = store.state.userInfo.accessToken;
  private newModel: Location = new Location();
  private editableModel: Location = new Location();

  mounted() {
    this.getData();
  }

  deleteModel(id: number) {
    if (!confirm(`是否删除 ID: ${id}`)) {
      return;
    }
    fetch(`http://localhost:5000/api/location/delete/${id}`, {
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

  editModel(model: Location) {
    this.editableModel = model;
    (this.$refs.editBox as EditableModal).show();
  }

  showCreateBox() {
    this.newModel = new Location();
    (this.$refs.createBox as EditableModal).show();
  }

  onEditSave() {
    let requestJson = JSON.stringify(this.editableModel);
    fetch(`http://localhost:5000/api/location/edit`, {
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
    let requestJson = JSON.stringify(this.newModel);
    fetch(`http://localhost:5000/api/location`, {
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
      `http://localhost:5000/api/location?pageSize=${this.page.PageSize}&pageIndex=${this.page.CurrentPage}`,
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
        this.models = responseJson.content as Location[];
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
}
</script>
