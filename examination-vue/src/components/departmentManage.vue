<template>
  <div>
    <el-card class="box-card" shadow="never">
      <el-button size="mini" @click="add">添加</el-button>
    </el-card>
    <el-table :data="tableData" border style="width: 100%;margin-top: 20px;"
      :header-cell-style="{ background: 'rgb(242, 243, 244)', color: '#515a6e' }" 
      row-key="id" default-expand-all
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">
      <el-table-column fixed prop="id" label="ID" v-if="false">
      </el-table-column>
      <el-table-column prop="name" label="部门名称">
      </el-table-column>
      <el-table-column prop="mastername" label="上级部门">
      </el-table-column>
      <el-table-column prop="sort" label="排序" v-if="false">
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
        <el-form :rules="rules" :model="department" ref="department">
          <el-form-item style="display:none;" label="id" label-width="100px">
            <el-input v-model="department.id"></el-input>
          </el-form-item>
          <el-form-item label="部门名称：" label-width="100px" prop="name">
            <el-input :disabled="dialogInputButtonVisible" v-model="department.name" clearable></el-input>
          </el-form-item>
          <el-form-item label="排序：" label-width="100px" prop="sort">
            <el-input-number v-model="department.sort" label="排序" :disabled="dialogInputButtonVisible">
            </el-input-number>
          </el-form-item>
          <el-form-item label="上级部门：" label-width="100px" prop="masterid">
            <el-cascader v-model="department.masterid" placeholder="请选择上级部门"
            :options="tableData"
            filterable
            @change="handleChange"
            :disabled="dialogInputButtonVisible"
            :props="{ value: 'id',label: 'name',checkStrictly: true,expandTrigger:'hover' }"></el-cascader>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false" :disabled="dialogInputButtonVisible">取 消</el-button>
          <el-button type="primary" @click="submit('department')" :disabled="dialogInputButtonVisible">确 定</el-button>
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
      this.title = "添加部门";
      this.department.name = "";
      this.department.sort = 0;
      this.department.masterid = "";
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = false;
    },
    update(row) {
      this.title = "修改部门";
      this.department.id = row.id;
      this.department.name = row.name;
      this.department.sort = row.sort;
      this.department.masterid = row.masterid;
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = false;
    },
    del(row) {
      const that = this;
      that.$confirm('是否删除该部门?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var curId = row.id;
        that.$http
          .post('/authority/deleteDepartment', {
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
      this.title = "查看部门";
      this.department.name = row.name;
      this.department.sort = row.sort;
      this.department.masterid = row.masterid;
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = true;
    },
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          return false;
        } else {
          const that = this;
          if (that.title == "添加部门") {
            that.$http
              .post('/authority/addDepartment', this.department)
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
          } else if (this.title == "修改部门") {
            this.$http
              .post('/authority/updateDepartment', this.department)
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
        .get('/authority/getDepartmentTree')
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
    handleChange(itemIdArr){
      //获取id
      let val=itemIdArr[itemIdArr.length-1];
      this.department.masterid=val;
    }
  },
  mounted() {
    this.getDataPage();
  },
  data() {
    return {
      department: {
        id: "",
        name: "",
        sort: 0,
        masterid: "",
        createtime: ""
      },
      title: "",
      dialogInputButtonVisible: false,
      dialogFormVisible: false,
      tableData: [],
      rules: {
        name: [
          { required: true, message: '部门名称不能为空', trigger: 'blur' },
          { max: 20, message: "部门名称长度不能超过20个字符", trigger: "blur" }
        ]
      }
    }
  }
}
</script>
<style>
</style>