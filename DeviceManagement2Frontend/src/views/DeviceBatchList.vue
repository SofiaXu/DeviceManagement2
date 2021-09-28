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
          <tr>            <th scope="col">设备批次ID</th>
            <th scope="col">设备基本信息</th>
            <th scope="col">设备生产商</th>
            <th scope="col">设备购买人</th>
            <th scope="col">购买日期</th>
            <th scope="col">批次号</th>
            <th scope="col">购买数量</th>
            <th scope="col">单价</th>
<th scope="col">操作</th>
          </tr>
        </thead>
        <tbody>          <tr v-for="(model, index) in models" :key="index">
            <td>{{ model.deviceBatchId }}</td>
            <td>{{ model.deviceBaseInformationId }}</td>
            <td>{{ model.deviceProviderId }}</td>
            <td>{{ model.devicePurchaserId }}</td>
            <td>{{ model.devicePurchaseDate }}</td>
            <td>{{ model.deviceBatchNumber }}</td>
            <td>{{ model.deviceCount }}</td>
            <td>{{ model.devicePrice }}</td>
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
                @click="deleteModel(model.deviceBatchId)"
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
            <label class="col-sm-3 col-form-label">设备批次ID</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newModel.deviceBatchId"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">设备基本信息</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newModel.deviceBaseInformationId"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">设备生产商</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newModel.deviceProviderId"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">设备购买人</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newModel.devicePurchaserId"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">购买日期</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newModel.devicePurchaseDate"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">批次号</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newModel.deviceBatchNumber"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">购买数量</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newModel.deviceCount"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">单价</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newModel.devicePrice"

              />
            </div>
          </div>        </template>
      </EditableModal>      <EditableModal ref="editBox" v-on:save="onEditSave()" v-on:close="getData()">
        <template v-slot:title>
          <h5>编辑</h5>
        </template>
        <template v-slot:default>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">设备批次ID</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableModel.deviceBatchId"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">设备基本信息</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableModel.deviceBaseInformationId"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">设备生产商</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableModel.deviceProviderId"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">设备购买人</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableModel.devicePurchaserId"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">购买日期</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableModel.devicePurchaseDate"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">批次号</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableModel.deviceBatchNumber"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">购买数量</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableModel.deviceCount"

              />
            </div>
          </div>          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">单价</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableModel.devicePrice"

              />
            </div>
          </div>        </template>
      </EditableModal>
    </div>
  </div>
</template><script lang="ts">
import { DeviceBatch } from "@/models/DeviceBatch";
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
export default class DeviceBatchList extends Vue {
  private models: DeviceBatch[] = [];
  private searchText = "";
  private page: Pagination = new Pagination();
  private accessToken = store.state.userInfo.accessToken;
  private newModel: DeviceBatch = new DeviceBatch();
  private editableModel: DeviceBatch = new DeviceBatch();

  mounted() {
    this.getData();
  }

  deleteModel(id: number) {
    if (!confirm(`是否删除 ID: ${id}`)) {
      return;
    }
    fetch(`http://localhost:5000/api/deviceBatch/delete/${id}`, {
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

  editModel(model: DeviceBatch) {
    this.editableModel = model;
    (this.$refs.editBox as EditableModal).show();
  }

  showCreateBox() {
    this.newModel = new DeviceBatch();
    (this.$refs.createBox as EditableModal).show();
  }

  onEditSave() {
    let requestJson = JSON.stringify(this.editableModel);
    fetch(`http://localhost:5000/api/deviceBatch/edit`, {
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
    fetch(`http://localhost:5000/api/deviceBatch`, {
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
      `http://localhost:5000/api/deviceBatch?pageSize=${this.page.PageSize}&pageIndex=${this.page.CurrentPage}`,
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
        this.models = responseJson.content as DeviceBatch[];
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
