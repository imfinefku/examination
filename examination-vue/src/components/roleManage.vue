<template>
  <div>
    <el-card class="box-card" shadow="never">
      <el-button size="mini" @click="add">添加</el-button>
    </el-card>
    <el-table :data="tableData" border style="width: 100%;margin-top: 20px;"
      :header-cell-style="{background: 'rgb(242, 243, 244)',color:'#515a6e'}">
      <el-table-column fixed prop="id" label="ID" v-if="false">
      </el-table-column>
      <el-table-column prop="name" label="角色名称">
      </el-table-column>
      <el-table-column prop="description" label="描述">
      </el-table-column>
      <el-table-column :formatter="dateFormat" prop="createtime" label="创建时间">
      </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="look(scope.row)">查看</el-button>
          <el-button size="mini" @click="update(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top:20px;float:right;">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[5, 10, 15, 20]"
        :page-size="pageSize" :current-page="currentPage" background layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
    <div>
      <el-dialog id="roleManage" :title="title" :visible.sync="dialogFormVisible">
        <el-form :rules="rules" :model="role" ref="role">
          <el-form-item style="display:none;" label="id" label-width="100px" prop="id">
            <el-input v-model="role.id"></el-input>
          </el-form-item>
          <el-form-item label="角色名称：" label-width="100px" prop="name">
            <el-input :disabled="dialogInputButtonVisible" v-model="role.name" clearable></el-input>
          </el-form-item>
          <el-form-item label="描述：" label-width="100px" prop="description">
            <el-input :disabled="dialogInputButtonVisible" v-model="role.description" clearable></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false" :disabled="dialogInputButtonVisible">取 消</el-button>
          <el-button type="primary" @click="submit('role')" :disabled="dialogInputButtonVisible">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import formatDate from '../utils/dateUtil.js';
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
    add() {
      this.title = "添加角色";
      this.role.name = "";
      this.role.description = "";
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = false;
    },
    update(row) {
      this.role.id = row.id;
      this.role.name = row.name;
      this.role.description = row.description;
      this.title = "修改角色";
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = false;
    },
    del(row) {
      const that = this;
      that.$confirm('是否删除该角色?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var curId = row.id;
        that.$http
          .post('/authority/deleteRole', {
            "id": curId
          })
          .then(function (response) {
            if (response.data.code == 200) {
              that.$message({
                showClose: true,
                type: 'success',
                message: '操作成功'
              });
              that.currentPage = 1;
              that.getDataPage(1, that.pageSize);
            }
          })
      });
    },
    look(row) {
      this.role.name = row.name;
      this.role.description = row.description;
      this.title = "查看角色";
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = true;
    },
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          return false;
        } else {
          const that = this;
          if (that.title == "添加角色") {
            that.$http
              .post('/authority/addRole', that.role)
              .then(function (response) {
                if (response.data.code == 200) {
                  that.$message({
                    showClose: true,
                    type: 'success',
                    message: '操作成功'
                  });
                  that.getDataPage(that.currentPage, that.pageSize);
                  that.dialogFormVisible = false;
                }
              })
          } else if (this.title == "修改角色") {
            this.$http
              .post('/authority/updateRole', that.role)
              .then(function (response) {
                if (response.data.code == 200) {
                  that.$message({
                    showClose: true,
                    type: 'success',
                    message: '操作成功'
                  });
                  that.getDataPage(that.currentPage, that.pageSize);
                  that.dialogFormVisible = false;
                }
              })
          }
        }
      });
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
    //时间格式化 
    dateFormat(row, column) {
      let date = new Date(row[column.property]);
      return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
    }
  },
  mounted() {
    this.getDataPage(this.currentPage, this.pageSize);
  },
  data() {
    return {
      role: {
        id: "",
        name: "",
        description: ""
      },
      title: "",
      dialogInputButtonVisible: false,
      dialogFormVisible: false,
      total: 0,
      pageSize: 5,
      currentPage: 1,
      tableData: [],
      rules: {
        name: [
          { required: true, message: '角色名称不能为空', trigger: 'blur' },
          { max: 20, message: "角色名称长度不能超过20个字符", trigger: "blur" }
        ],
        description: [
          { max: 100, message: "描述长度不能超过100个字符", trigger: "blur" }
        ]
      }
    }
  }
}
</script>
<style>

</style>