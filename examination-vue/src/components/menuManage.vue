<template>
  <div>
    <el-card class="box-card" shadow="never">
      <el-button size="mini" @click="add">添加</el-button>
    </el-card>
    <el-table :data="tableData" border style="width: 100%;margin-top: 20px;"
    :header-cell-style="{background: 'rgb(242, 243, 244)',color:'#515a6e'}"
    row-key="id" default-expand-all
    :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column fixed prop="id" label="ID" v-if="false">
      </el-table-column>
      <el-table-column width="200" prop="name" label="菜单名称">
      </el-table-column>
      <el-table-column width="100" prop="icon" label="图标">
        <template slot-scope="scope">
          <i :class="scope.row.icon" style="font-size:25px;margin-top:2px;color:#606266 !important;"></i>
        </template>
      </el-table-column>
      <el-table-column prop="masterid" label="上级菜单" v-if="false">
      </el-table-column>
      <el-table-column width="200" prop="mastername" label="上级菜单">
      </el-table-column>
      <el-table-column prop="route" label="路由">
      </el-table-column>
      <el-table-column :formatter="dateFormat" prop="createtime" label="创建时间" v-if="false">
      </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="look(scope.row)">查看</el-button>
          <el-button size="mini" @click="update(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-dialog :title="title" :visible.sync="dialogFormVisible">
        <el-form :rules="rules" :model="menu" ref="menu">
          <el-form-item style="display:none;" label="id" label-width="100px">
            <el-input v-model="menu.id"></el-input>
          </el-form-item>
          <el-form-item label="菜单名称：" label-width="100px" prop="name">
            <el-input :disabled="dialogInputButtonVisible" v-model="menu.name" clearable></el-input>
          </el-form-item>
          <el-form-item label="图标：" label-width="100px" prop="icon">
            <el-input :disabled="dialogInputButtonVisible" v-model="menu.icon" clearable></el-input>
          </el-form-item>
          <el-form-item label="排序：" label-width="100px" prop="sort">
            <el-input-number v-model="menu.sort" label="排序" :disabled="dialogInputButtonVisible"></el-input-number>
          </el-form-item>
          <el-form-item label="上级菜单：" label-width="100px" prop="masterid">
            <el-select v-model="menu.masterid" placeholder="请选择上级菜单" :disabled="dialogInputButtonVisible">
              <el-option v-for="item in topLevel" :value="item.id" :label="item.name" :key="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="路由：" label-width="100px" prop="route">
            <el-input :disabled="dialogInputButtonVisible" v-model="menu.route" clearable></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false" :disabled="dialogInputButtonVisible">取 消</el-button>
          <el-button type="primary" @click="submit('menu')" :disabled="dialogInputButtonVisible">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import formatDate from '../utils/dateUtil.js';
export default {
  methods: {
    add() {
      this.title = "添加菜单";
      this.getTopLevel();
      this.menu.name = "";
      this.menu.icon = "";
      this.menu.sort = 0;
      this.menu.masterid = "";
      this.menu.route = "";
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = false;
    },
    update(row) {
      this.title = "修改菜单";
      this.getTopLevel();
      this.menu.id = row.id;
      this.menu.name = row.name;
      this.menu.icon = row.icon;
      this.menu.sort = row.sort;
      this.menu.masterid = row.masterid;
      this.menu.route = row.route;
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = false;
    },
    del(row) {
      const that = this;
      that.$confirm('是否删除该菜单?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var curId = row.id;
        that.$http
          .post('/authority/deleteMenu', {
            "id": curId
          })
          .then(function (response) {
            if (response.data.code == 200) {
              that.$message({
                showClose: true,
                type: 'success',
                message: '操作成功'
              });
              that.getDataPage();
            }
          })
      });
    },
    look(row) {
      this.title = "查看菜单";
      this.getTopLevel();
      this.menu.name = row.name;
      this.menu.icon = row.icon;
      this.menu.sort = row.sort;
      this.menu.masterid = row.masterid;
      this.menu.route = row.route;
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = true;
    },
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          return false;
        }else {
          const that = this;
          if (that.title == "添加菜单") {
            that.$http
              .post('/authority/addMenu', this.menu)
              .then(function (response) {
                if (response.data.code == 200) {
                  that.$message({
                    showClose: true,
                    type: 'success',
                    message: '操作成功'
                  });
                  that.getDataPage();
                  that.dialogFormVisible = false;
                }
              })
          } else if (this.title == "修改菜单") {
            this.$http
              .post('/authority/updateMenu', this.menu)
              .then(function (response) {
                if (response.data.code == 200) {
                  that.$message({
                    showClose: true,
                    type: 'success',
                    message: '操作成功'
                  });
                  that.getDataPage();
                  that.dialogFormVisible = false;
                }
              })
          }
        }
      });
    },
    getDataPage() {
      const that = this;
      this.$http
        .get('/authority/getMenu')
        .then(function (response) {
          if (response.data.code == 200) {
            that.tableData = response.data.data;
          }
        })
    },
    //时间格式化 
    dateFormat(row, column) {
      let date = new Date(row[column.property]);
      return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
    },
    getTopLevel() {
      const that = this;
      this.$http
        .get('/authority/getTopLevelMenuAll',)
        .then(function (response) {
          if (response.data.code == 200) {
            that.topLevel = response.data.data;
          }
        })
    }
  },
  mounted() {
    this.getDataPage();
  },
  data() {
    return {
      menu: {
        id: "",
        name: "",
        icon: "",
        sort: 0,
        masterid: "",
        route: "",
      },
      title: "",
      dialogInputButtonVisible: false,
      dialogFormVisible: false,
      tableData: [],
      topLevel: [],
      rules: {
        name: [
          { required: true, message: '菜单名称不能为空', trigger: 'blur' },
          { max: 20, message: "菜单名称长度不能超过20个字符", trigger: "blur" }
        ],
        route: [
          { max: 50, message: "菜单路由长度不能超过50个字符", trigger: "blur" }
        ]
      }
    }
  }
}
</script>
<style>
</style>