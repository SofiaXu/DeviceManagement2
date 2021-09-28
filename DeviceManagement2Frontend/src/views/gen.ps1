$head = @"
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
"@;
$tableTitle = "";
foreach($i in $props) {
    $tableTitle += "            <th scope=`"col`">$($i.Groups["typeComment"].Value)</th>`n"
}
$tableTitle += @"
          </tr>
        </thead>
        <tbody>
"@
$tableBody = "          <tr v-for=`"(model, index) in models`" :key=`"index`">`n";
foreach($i in $props) {
    $tableBody += "            <td>{{ model.$($i.Groups["propName"].Value) }}</td>`n"
}
$tableBody += @"
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
                @click="deleteModel(model.$($props[0].Groups["propName"].Value))"
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
      </div>
"@
$addBox = @"
      <EditableModal
        ref="createBox"
        v-on:save="onCreateSave()"
        v-on:close="onCreateClose()"
      >
        <template v-slot:title>
          <h5>添加</h5>
        </template>
        <template v-slot:default>
"@
foreach($i in $props) {
    $addBox += @"
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">$($i.Groups["typeComment"].Value)</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="newModel.$($i.Groups["propName"].Value)"
                required
              />
            </div>
          </div>
"@;
}
$addBox += @"
        </template>
      </EditableModal>
"@
$editBox = @"
      <EditableModal ref="editBox" v-on:save="onEditSave()" v-on:close="getData()">
        <template v-slot:title>
          <h5>编辑</h5>
        </template>
        <template v-slot:default>
"@
foreach($i in $props) {
    $editBox += @"
          <div class="mb-3 row">
            <label class="col-sm-3 col-form-label">$($i.Groups["typeComment"].Value)</label>
            <div class="col-sm-9">
              <input
                type="text"
                class="form-control"
                v-model="editableModel.$($i.Groups["propName"].Value)"
                required
              />
            </div>
          </div>
"@;
}
$editBox += @"
        </template>
      </EditableModal>
    </div>
  </div>
</template>
"@
$template = $head + $tableTitle + $tableBody + $addBox + $editBox;

$tscode = @"
<script lang="ts">
import { $($model.BaseName.Replace("Model", "")) } from "@/models/$($model.BaseName.Replace("Model", ""))";
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
export default class $($model.BaseName.Replace("Model", ""))List extends Vue {
  private models: $($model.BaseName.Replace("Model", ""))[] = [];
  private searchText = "";
  private page: Pagination = new Pagination();
  private accessToken = store.state.userInfo.accessToken;
  private newModel: $($model.BaseName.Replace("Model", "")) = new $($model.BaseName.Replace("Model", ""))();
  private editableModel: $($model.BaseName.Replace("Model", "")) = new $($model.BaseName.Replace("Model", ""))();

  mounted() {
    this.getData();
  }

  deleteModel(id: number) {
    if (!confirm(``是否删除 ID: `${id}``)) {
      return;
    }
    fetch(``http://localhost:5000/api/$($model.BaseName.SubString(0, 1).ToLower() + $model.BaseName.SubString(1).Replace("Model", ""))/delete/`${id}``, {
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
          alert("网络连接失败");
          return;
        }
        alert("删除成功");
        this.getData();
      })
      .catch((error) => console.error(error));
  }

  editModel(model: $($model.BaseName.Replace("Model", ""))) {
    this.editableModel = model;
    (this.`$refs.editBox as EditableModal).show();
  }

  showCreateBox() {
    (this.`$refs.createBox as EditableModal).show();
  }

  onEditSave() {
    let requestJson = JSON.stringify(this.editableModel);
    fetch(``http://localhost:5000/api/$($model.BaseName.SubString(0, 1).ToLower() + $model.BaseName.SubString(1).Replace("Model", ""))/edit``, {
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
          alert("网络连接失败");
          return;
        }
        alert("修改成功");
        this.getData();
      })
      .catch((error) => console.error(error));
  }

  onCreateSave() {
    let requestJson = JSON.stringify(this.newModel);
    fetch(``http://localhost:5000/api/$($model.BaseName.SubString(0, 1).ToLower() + $model.BaseName.SubString(1).Replace("Model", ""))``, {
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
          alert("网络连接失败");
          return;
        }
        alert("添加成功");
        this.getData();
        this.onCreateClose();
      })
      .catch((error) => console.error(error));
  }

  onCreateClose() {
    this.newModel = new $($model.BaseName.Replace("Model", ""))();
  }

  getData() {
    fetch(
      ``http://localhost:5000/api/$($model.BaseName.SubString(0, 1).ToLower() + $model.BaseName.SubString(1).Replace("Model", ""))?pageSize=`${this.page.PageSize}&pageIndex=`${this.page.CurrentPage}``,
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
          alert("网络连接失败");
          return;
        }
        this.models = responseJson.content as $($model.BaseName.Replace("Model", ""))[];
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
"@