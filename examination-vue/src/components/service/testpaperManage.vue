<template>
  <div>
    <el-card class="box-card" shadow="never">
      <router-link to="/home/examinationManage/testpaperAdd">
        <el-button size="mini">添加</el-button>
      </router-link>
    </el-card>
    <el-table :data="tableData" border style="width: 100%;margin-top: 20px;"
      :header-cell-style="{background: 'rgb(242, 243, 244)',color:'#515a6e'}">
      <el-table-column fixed prop="id" label="ID" v-if="false">
      </el-table-column>
      <el-table-column prop="name" label="试卷名称">
      </el-table-column>
      <el-table-column prop="subjectName" label="科目" width="150">
      </el-table-column>
      <el-table-column prop="xznum" label="选择题数量" width="150">
      </el-table-column>
      <el-table-column prop="pdnum" label="判断题数量" width="150">
      </el-table-column>
      <el-table-column prop="jdnum" label="简答题数量" width="150">
      </el-table-column>
      <el-table-column prop="score" label="总分" width="150">
      </el-table-column>
      <el-table-column prop="time" label="考试时间(分钟)" width="150">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="220">
        <template slot-scope="scope">
          <el-button size="mini" @click="look(scope.row)">预览</el-button>
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
    <div class="testPaper">
      <el-dialog 
      :visible.sync="dialogFormVisibleTestPaper" 
      :fullscreen="true">
        <div class="questionClass">
          <div class="testPaperHeader">
            <div style="font-size:20px;">{{testpaper.name}}
              <el-button type="primary" @click="dialogFormVisibleTestPaper=false" style="float:right;margin-top: 10px;margin-left:20px;">关闭
              </el-button>
            </div>
            <div>考试时间：{{testpaper.time}}分钟
              &nbsp;&nbsp;&nbsp;总分：{{testpaper.score}}
            </div>
          </div>
          <div v-for="(question,index) in testpaper.questionList" class="questionClass2">
            {{index+1}}.{{question.content}} ({{question.score}}分)
            <div style="margin-top:10px;">
              <el-image v-if="question.imgurl" :src="getImg(question.imgurl)"
                :preview-src-list="getPreviewImg(question.imgurl)" style="width: 200px; height: 200px">
              </el-image>
            </div>
            <div v-if="question.type=='单选题'" style="margin-top:10px;margin-bottom: 20px;">
              A.{{question.a}}<br>
              B.{{question.b}}<br>
              C.{{question.c}}<br>
              D.{{question.d}}<br>
              <div>
                <el-radio v-model="question.reply" label="A">A</el-radio>
                <el-radio v-model="question.reply" label="B">B</el-radio>
                <el-radio v-model="question.reply" label="C">C</el-radio>
                <el-radio v-model="question.reply" label="D">D</el-radio>
              </div>
            </div>
            <div v-if="question.type=='判断题'" style="margin-top:10px;margin-bottom: 20px;">
              <div>
                <el-radio v-model="question.reply" label="对">对</el-radio>
                <el-radio v-model="question.reply" label="错">错</el-radio>
              </div>
            </div>
            <div v-if="question.type=='简答题'" style="margin-top:10px;margin-bottom: 20px;">
              <div>
                <el-input v-model="question.reply" type="textarea" :rows="5">
                </el-input>
              </div>
            </div>
          </div>
        </div>
      </el-dialog>
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
    update(row) {
      this.$router.replace({
        path: '/home/examinationManage/testpaperAdd',
        query:{
          id:row.id
        }
      });
    },
    look(row){
      this.dialogFormVisibleTestPaper=true;
      const that = this;
      var param = new URLSearchParams();
      param.append("id", row.id);
      that.$http
        .get('/examinationManage/getTestPaperById', {
          params: param
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.testpaper = response.data.data;
          }
        })
    },
    del(row) {
      const that = this;
      that.$confirm('是否删除该试卷?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var curId = row.id;
        that.$http
          .post('/examinationManage/deleteTestPaper', {
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
    getDataPage(currentPage, pageSize) {
      const that = this;
      var param = new URLSearchParams();
      param.append("page", currentPage);
      param.append("limit", pageSize);
      this.$http
        .get('/examinationManage/getTestPaperPage', {
          params: param
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.tableData = response.data.data;
            that.total = response.data.count;
          }
        })
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
    }
  },
  mounted() {
    this.getSubject();
    this.getDataPage(this.currentPage, this.pageSize);
  },
  data() {
    return {
      testPaper: {
        id: "",
        name: "",
        subject: "",
        xznum: "",
        pdnum: "",
        jdnum: "",
        score: "",
        time: "",
        testPaperQuestionList: []
      },
      testpaper:{},
      title: "",
      dialogInputButtonVisible: false,
      dialogFormVisible: false,
      dialogFormVisibleTestPaper:false,
      total: 0,
      pageSize: 5,
      currentPage: 1,
      tableData: [],
      subjectList: [],
      quesTypeList: [
        {
          "name": "单选题",
          "code": "单选题"
        }, {
          "name": "判断题",
          "code": "判断题"
        }, {
          "name": "简答题",
          "code": "简答题"
        }
      ],
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
  .questionClass {
  margin-top: 1px;
  margin-left: 20%;
  margin-right: 20%;
  background: white;
}

.questionClass2 {
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 20px;
  line-height: 30px;
  border-bottom: 1px solid black;
}

.testPaper .el-dialog {
  background: #eeeeee;
}

.testPaper .el-dialog__header {
  display: none;
}

.testPaper .dj-dialog-content {
  padding: 0;
  overflow: unset;
}

.testPaperHeader {
  text-align: center;
  line-height: 35px;
  border-bottom: 1px solid black;
  margin-left: 20px;
  margin-right: 20px;
}
</style>