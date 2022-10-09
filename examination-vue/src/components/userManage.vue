<template>
  <div>

    <el-card class="box-card" shadow="never">
      <el-button size="mini" @click="add">添加</el-button>
    </el-card>
    <el-table :data="tableData" border style="width: 100%;margin-top: 20px;"
      :header-cell-style="{ background: 'rgb(242, 243, 244)', color: '#515a6e' }">
      <el-table-column fixed prop="id" label="ID" v-if="false">
      </el-table-column>
      <el-table-column prop="name" label="用户名称">
      </el-table-column>
      <el-table-column prop="username" label="账号">
      </el-table-column>
      <el-table-column prop="departmentname" label="所属部门">
      </el-table-column>
      <el-table-column :formatter="dateFormat" prop="lastlogintime" label="上次登录时间">
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
      <el-dialog :title="title" :visible.sync="dialogFormVisible">
        <el-form :rules="rules" :model="user" ref="user">
          <el-form-item style="display:none;" label="id" label-width="100px" prop="id">
            <el-input v-model="user.id"></el-input>
          </el-form-item>
          <el-form-item label="用户名称：" label-width="100px" prop="name">
            <el-input :disabled="dialogInputButtonVisible" v-model="user.name" clearable></el-input>
          </el-form-item>
          <el-form-item label="账号：" label-width="100px" :style="usernameDisplay" prop="username">
            <el-input :disabled="dialogInputButtonVisible" v-model="user.username" clearable></el-input>
          </el-form-item>
          <el-form-item label="密码：" label-width="100px" prop="password">
            <el-input :disabled="dialogInputButtonVisible" v-model="user.password" clearable></el-input>
          </el-form-item>
          <el-form-item label="角色：" label-width="100px" prop="userRoleList">
            <el-select v-model="user.userRoleList" multiple placeholder="请选择角色" :disabled="dialogInputButtonVisible">
              <el-option v-for="item in roleList" :key="item.id" :label="item.name" :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="所属部门：" label-width="100px" prop="departmentid">
            <el-cascader v-model="user.departmentid" placeholder="请选择所属部门" :options="departmentData" filterable
              @change="handleChange" :disabled="dialogInputButtonVisible"
              :props="{ value: 'id', label: 'name', checkStrictly: true, expandTrigger: 'hover' }"></el-cascader>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false" :disabled="dialogInputButtonVisible">取 消</el-button>
          <el-button type="primary" @click="submit('user')" :disabled="dialogInputButtonVisible">确 定</el-button>
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
      this.title = "添加用户";
      this.user.name = "";
      this.user.username = "";
      this.user.password = "";
      this.user.userRoleList = [];
      this.user.departmentid = "";
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = false;
      this.usernameDisplay = "";
    },
    update(row) {
      this.user.id = row.id;
      this.user.name = row.name;
      this.user.username = row.username;
      this.user.password = row.password;
      this.user.userRoleList = row.userRoleLists;
      this.user.departmentid = row.departmentid;
      this.title = "修改用户";
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = false;
      this.usernameDisplay = "display:none";
    },
    del(row) {
      const that = this;
      that.$confirm('是否删除该用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var curId = row.id;
        that.$http
          .post('/authority/deleteUser', {
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
      this.user.id = row.id;
      this.user.name = row.name;
      this.user.username = row.username;
      this.user.password = row.password;
      this.user.userRoleList = row.userRoleLists;
      this.user.departmentid = row.departmentid;
      this.title = "查看用户";
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = true;
      this.usernameDisplay = "";
    },
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          return false;
        } else {
          const that = this;
          if (that.title == "添加用户") {
            that.$http
              .post('/authority/addUser', {
                "name": that.user.name,
                "username": that.user.username,
                "password": that.user.password,
                "userRoleLists": that.user.userRoleList,
                "departmentid": that.user.departmentid
              })
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
          } else if (that.title == "修改用户") {
            that.$http
              .post('/authority/updateUser', {
                "id": that.user.id,
                "name": that.user.name,
                "username": that.user.username,
                "password": that.user.password,
                "userRoleLists": that.user.userRoleList,
                "departmentid": that.user.departmentid
              })
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
        .get('/authority/getUserPage', {
          params: param
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.tableData = response.data.data;
            that.total = response.data.count;
          }
        })
    },
    getRoleAll() {
      const that = this;
      this.$http
        .get('/authority/getRoleAll')
        .then(function (response) {
          if (response.data.code == 200) {
            that.roleList = response.data.data;
          }
        })
    },
    //时间格式化
    dateFormat(row, column) {
      if (row[column.property] == null) {
        return "";
      }
      let date = new Date(row[column.property]);
      return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
    },
    getDepartmentData() {
      const that = this;
      this.$http
        .get('/authority/getDepartmentTree')
        .then(function (response) {
          if (response.data.code == 200) {
            that.departmentData = response.data.data;
          }
        })
    },
    handleChange(itemIdArr) {
      //获取id
      var val = itemIdArr[itemIdArr.length - 1];
      this.user.departmentid = val;
    }
  },
  mounted() {
    this.getDataPage(this.currentPage, this.pageSize);
    this.getRoleAll();
    this.getDepartmentData();
  },
  data() {
    return {
      user: {
        id: "",
        name: "",
        username: "",
        password: "",
        userRoleList: [],
        departmentid: ""
      },
      title: "",
      usernameDisplay: "",
      dialogInputButtonVisible: false,
      dialogFormVisible: false,
      total: 0,
      pageSize: 5,
      currentPage: 1,
      tableData: [],
      roleList: [],
      departmentData: [],
      rules: {
        name: [
          { required: true, message: '用户名称不能为空', trigger: 'blur' },
          { max: 20, message: "用户名称长度不能超过20个字符", trigger: "blur" }
        ],
        username: [
          { required: true, message: '账号不能为空', trigger: 'blur' },
          { max: 20, message: "账号长度不能超过20个字符", trigger: "blur" }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { max: 20, message: "密码长度不能超过20个字符", trigger: "blur" }
        ]
      }
    }
  }
}
</script>
<style>
.el-select .el-tag__close.el-icon-close {
  color: #fff !important;
}
</style>