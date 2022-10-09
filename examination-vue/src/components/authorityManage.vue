<template>
  <div>
    <div style="width: 50%;margin-top: 20px;float: left;">
      <el-table :data="tableData" border highlight-current-row @current-change="handleCurrentChangeTable"
      :header-cell-style="{background: 'rgb(242, 243, 244)',color:'#515a6e'}">
        <el-table-column fixed prop="id" label="ID" v-if="false">
        </el-table-column>
        <el-table-column prop="name" label="角色名称">
        </el-table-column>
        <el-table-column prop="description" label="描述">
        </el-table-column>
      </el-table>
      <div style="margin-top:20px;float:right;">
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
          :page-sizes="[5, 10, 15, 20]" :page-size="pageSize" :current-page="currentPage" background
          layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
      </div>
    </div>
    <div style="width: 45%;margin-top: 20px;float: left;margin-left: 20px;">
      <el-card class="box-card" shadow="never">
        <el-tree :data="treeData" show-checkbox node-key="id" ref="tree" :props="defaultProps" default-expand-all
          highlight-current>
        </el-tree>
        <el-button size="mini" type="primary" @click="save" style="float:right;margin-bottom:20px;margin-top:20px;">保存
        </el-button>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
      this.getDataPage(this.currentPage, this.pageSize);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getDataPage(this.currentPage, this.pageSize);
    },
    getDataPage(currentPage, pageSize) {
      const that = this;
      var param = new URLSearchParams();
      param.append("page", currentPage);
      param.append("limit", pageSize);
      this.$http
        .get('/authority/getRolePage', {
          params: param
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.tableData = response.data.data;
            that.total = response.data.count;
          }
        })
    },
    handleCurrentChangeTable(currentRow) {
      this.currentId = currentRow.id;
      const that = this;
      var param = new URLSearchParams();
      param.append("roleid", this.currentId);
      this.$http
        .get('/authority/getRoleMenu', {
          params: param
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.$refs.tree.setCheckedKeys(response.data.data);
          }
        })
    },
    getTreePage() {
      const that = this;
      this.$http
        .get('/authority/getMenuTree')
        .then(function (response) {
          if (response.data.code == 200) {
            that.treeData = response.data.data;
          }
        })
    },
    save() {
      if (this.currentId==null || this.currentId==""){
        this.$message.error({message:'请选择一个角色'});
        return;
      }
      const that = this;
      that.$http
        .post('/authority/addRoleMenu', {
          "roleid": this.currentId,
          "allSelectMenu": that.$refs.tree.getCheckedKeys(),
          "halfSelectMenu": that.$refs.tree.getHalfCheckedKeys()
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.$message({
              showClose: true,
              type: 'success',
              message: '操作成功'
            });
          }
        })
    }
  },
  mounted() {
    this.getDataPage(this.currentPage, this.pageSize);
    this.getTreePage();
  },
  data() {
    return {
      currentId: "",
      total: 0,
      pageSize: 5,
      currentPage: 1,
      tableData: [],
      treeData: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  }
}
</script>
<style>
</style>