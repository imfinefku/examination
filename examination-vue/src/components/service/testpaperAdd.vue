<template>
  <el-card class="box-card" shadow="never" style="width:60%;margin-left:20%;">
    <div>
      <el-form :rules="rules" :model="testPaper" ref="testPaper">
        <el-form-item style="display:none;" label="id" label-width="100px" prop="id">
          <el-input v-model="testPaper.id"></el-input>
        </el-form-item>
        <el-form-item label="试卷名称：" label-width="150px" prop="name">
          <el-input v-model="testPaper.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="科目：" label-width="150px" prop="subject">
          <el-select v-model="testPaper.subject" placeholder="请选择科目">
            <el-option v-for="item in subjectList" :value="item.id" :label="item.name" :key="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="考试时间(分钟)：" label-width="150px" prop="time">
          <el-input-number v-model="testPaper.time" :min="1"></el-input-number>
        </el-form-item>
        <el-button size="mini" @click="addQuestion()" style="margin-bottom:10px;">添加试题</el-button>
        <el-table :data="testPaper.testPaperQuestionList"
        :header-cell-style="{ background: 'rgb(242, 243, 244)', color: '#515a6e' }">
          <el-table-column label="题干" prop="content">
          </el-table-column>
          <el-table-column label="题型" prop="type">
          </el-table-column>
          <el-table-column label="科目" prop="subjectName">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" @click="deleteRows(scope)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer" style="margin-top:20px;text-align:center;">
        <el-button @click="back()" style="margin-right:200px;">取 消</el-button>
        <el-button type="primary" @click="submit('testPaper')">确 定
        </el-button>
      </div>
      <el-dialog title="添加试题" :visible.sync="dialogFormVisible">
        <el-table ref="questionTable" :data="tableData" border style="width: 100%;margin-top: 20px;"
          :header-cell-style="{ background: '#f8f8f9', color: '#515a6e' }">
          <el-table-column type="selection" width="55">
          </el-table-column>
          <el-table-column fixed prop="id" label="ID" v-if="false">
          </el-table-column>
          <el-table-column prop="content" label="题干">
          </el-table-column>
          <el-table-column prop="type" label="题型">
          </el-table-column>
          <el-table-column prop="subjectName" label="所属科目">
          </el-table-column>
        </el-table>
        <div style="margin-top:20px;float:right;">
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :page-sizes="[5, 10, 15, 20]" :page-size="pageSize" :current-page="currentPage" background
            layout="total, sizes, prev, pager, next, jumper" :total="total">
          </el-pagination>
        </div>
        <div slot="footer" class="dialog-footer" style="margin-top:30px;">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button @click="getTableData()" type="primary">确 定
          </el-button>
        </div>
      </el-dialog>
    </div>
  </el-card>
</template>
  
<script>
export default {
  methods: {
    submit(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          return false;
        } else {
          const that = this;
          if (that.testPaper.id == "") {
            that.$http
              .post('/examinationManage/addTestPaper', that.testPaper)
              .then(function (response) {
                if (response.data.code == 200) {
                  that.$message({
                    showClose: true,
                    type: 'success',
                    message: '操作成功'
                  });
                  that.$router.replace({
                    path: '/home/examinationManage/testpaperManage'
                  });
                }
              })
          } else if (that.testPaper.id != "") {
            this.$http
              .post('/examinationManage/updateTestPaper', that.testPaper)
              .then(function (response) {
                if (response.data.code == 200) {
                  that.$message({
                    showClose: true,
                    type: 'success',
                    message: '操作成功'
                  });
                  that.$router.replace({
                    path: '/home/examinationManage/testpaperManage'
                  });
                }
              })
          }
        }
      });
    },
    getSubject() {
      const that = this;
      this.$http
        .get('/examinationManage/getSubjectAll')
        .then(function (response) {
          if (response.data.code == 200) {
            that.subjectList = response.data.data;
          }
        })
    },
    addQuestion() {
      this.dialogFormVisible = true;
      this.getDataPage(1, this.pageSize);
    },
    //删除行
    deleteRows(scope) {
      this.testPaper.testPaperQuestionList.splice(scope.$index, 1);
    },
    back() {
      this.$router.replace({
        path: '/home/examinationManage/testpaperManage'
      });
    },
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
        .get('/examinationManage/getQuestionPage', {
          params: param
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.tableData = response.data.data;
            that.total = response.data.count;
          }
        })
    },
    getTableData() {
      for (var i = 0; i < this.$refs.questionTable.selection.length; i++) {
        var questionObj = {
          content: this.$refs.questionTable.selection[i].content,
          type: this.$refs.questionTable.selection[i].type,
          subjectName: this.$refs.questionTable.selection[i].subjectName,
          question: this.$refs.questionTable.selection[i].id
        };
        this.testPaper.testPaperQuestionList.push(questionObj);
      }
      this.dialogFormVisible = false;
    },
    getTestPagerById(id) {
      const that = this;
      var param = new URLSearchParams();
      param.append("id", id);
      this.$http
        .get('/examinationManage/getTestPagerById', {
          params: param
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.testPaper=response.data.data;
          }
        })
    }
  },
  mounted() {
    this.getSubject();
    if (this.$route.query.id==null){
      this.testPaper.id="";
    }else{
      this.testPaper.id=this.$route.query.id;
      this.getTestPagerById(this.testPaper.id);
    }
  },
  data() {
    return {
      testPaper: {
        id: "",
        name: "",
        subject: "",
        time:"",
        testPaperQuestionList: []
      },
      dialogFormVisible: false,
      subjectList: [],
      tableData: [],
      pageSize: 5,
      currentPage: 1,
      total: 0,
      rules: {
        name: [
          { required: true, message: '试卷名称不能为空', trigger: 'blur' },
          { max: 20, message: "试卷名称长度不能超过20个字符", trigger: "blur" }
        ]
      }
    }
  }
}
</script>
<style>
</style>