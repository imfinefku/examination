<template>
  <div>
    <el-table :data="tableData" border style="width: 100%;margin-top: 20px;"
      :header-cell-style="{background: 'rgb(242, 243, 244)',color:'#515a6e'}">
      <el-table-column fixed prop="id" label="id" v-if="false">
      </el-table-column>
      <el-table-column fixed prop="testpaper" label="testpaper" v-if="false">
      </el-table-column>
      <el-table-column prop="name" label="考试名称">
      </el-table-column>
      <el-table-column prop="subjectName" label="考试科目">
      </el-table-column>
      <el-table-column prop="score" label="考试总分">
      </el-table-column>
      <el-table-column prop="qualifiedscore" label="合格分数">
      </el-table-column>
      <el-table-column :formatter="typeFormat" prop="type" label="考试权限">
      </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="joinExamination(scope.row)">参加考试</el-button>
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
      <el-dialog title="参加考试" :visible.sync="dialogFormVisible">
        <el-form :model="examination" ref="examination">
          <el-form-item style="display:none;" label="testpaper" label-width="100px" prop="testpaper">
            <el-input v-model="examination.testpaper"></el-input>
          </el-form-item>
          <el-form-item label="考试名称：" label-width="100px">
            {{examination.name}}
          </el-form-item>
          <el-form-item label="考试科目：" label-width="100px">
            {{examination.subjectName}}
          </el-form-item>
          <el-form-item label="考试总分：" label-width="100px">
            {{examination.score}}
          </el-form-item>
          <el-form-item label="通过分数：" label-width="100px">
            {{examination.qualifiedscore}}
          </el-form-item>
          <el-form-item label="考试时间：" label-width="100px">
            {{examination.time}} 分钟
          </el-form-item>
          <el-form-item v-if="examination.type==1" label="输入口令：" label-width="100px">
            <el-input v-model="examination.password" clearable></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="surejoin()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div class="testPaper">
      <el-dialog :visible.sync="dialogFormVisibleExam" :fullscreen="true" @open="examDialogOpen()"
        @close="examDialogClose()">
        <div id="timerClass">
          距离考试结束还有：
          <div style="color:red;">{{djs}}</div>
        </div>
        <div class="questionClass">
          <div class="testPaperHeader">
            <div style="font-size:20px;">{{testPaper.name}}
              <el-button type="primary" @click="suresubmit()" style="float:right;margin-top: 10px;margin-left:20px;">交卷
              </el-button>
            </div>
            <div>考试时间：{{testPaper.time}}分钟
              &nbsp;&nbsp;&nbsp;总分：{{testPaper.score}}
            </div>
          </div>
          <div v-for="(question,index) in testPaper.questionList" class="questionClass2">
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
    countDown() {
      let m = parseInt(this.seconds / 60);
      m = m < 10 ? "0" + m : m
      let s = parseInt(this.seconds % 60);
      s = s < 10 ? "0" + s : s
      this.djs = m + '分钟' + s + '秒'
    },
    examDialogOpen() {
      this.seconds = this.testPaper.time * 60;
      //考试时间倒计时监听器
      this.timer=setInterval(() => {
        if (this.seconds < 1) {
          this.submit();
          this.$message({
            showClose: true,
            type: 'success',
            message: '时间到，考试结束'
          });
        } else {
          this.seconds = this.seconds - 1;
          this.countDown();
        }
      }, 1000);
      this.handleFullScreen();
    },
    examDialogClose() {
      clearInterval(this.timer);
      this.handleExitFullScreen();
    },
    getData() {
      const that = this;
      var param = new URLSearchParams();
      param.append("id", that.joinid);
      that.$http
        .get('/examinationManage/getTestPaperByJoinId', {
          params: param
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.testPaper = response.data.data;
            that.dialogFormVisibleExam = true;
          }
        })
    },
    getImg(url) {
      if (url != null && url != "") {
        url = this.$http.defaults.baseURL + "/file/" + url;
      }
      return url;
    },
    getPreviewImg(url) {
      if (url != null && url != "") {
        url = this.$http.defaults.baseURL + "/file/" + url;
      }
      var urls = new Array();
      urls.push(url);
      return urls;
    },
    suresubmit() {
      const that = this;
      that.$confirm('确定交卷?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        that.submit();
      });
    },
    submit() {
      const that = this;
      that.$http
        .post('/examinationManage/submitTestPaper', {
          "joinid": that.joinid,
          "questionList": that.testPaper.questionList
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.$message({
              showClose: true,
              type: 'success',
              message: '交卷成功'
            });
            that.dialogFormVisibleExam = false;
          }
        })
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
        .get('/examinationManage/getOnlineExaminationPage', {
          params: param
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.tableData = response.data.data;
            that.total = response.data.count;
          }
        })
    },
    typeFormat(row, column) {
      let tp = row[column.property];
      if (tp == "1") {
        tp = "口令考试";
      } else if (tp == "0") {
        tp = "完全开放";
      }
      return tp;
    },
    changeTestPaperData(val) {
      const that = this;
      var param = new URLSearchParams();
      param.append("id", val);
      that.$http
        .get('/examinationManage/getTestPagerById', {
          params: param
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.examination.testPaperData = response.data.data;
          }
        })
    },
    joinExamination(row) {
      this.examination.id = row.id;
      this.examination.name = row.name;
      this.examination.type = row.type;
      this.examination.time = row.time;
      this.examination.score = row.score;
      this.examination.password = "";
      this.examination.testpaper = row.testpaper;
      this.examination.subjectName = row.subjectName;
      this.examination.qualifiedscore = row.qualifiedscore;
      this.dialogFormVisible = true;
    },
    surejoin() {
      const that = this;
      that.$http
        .post('/examinationManage/joinExamination', that.examination)
        .then(function (response) {
          if (response.data.code == 200) {
            that.dialogFormVisible = false;
            that.joinid = response.data.data;
            that.getData();
          }
        })
    },
    // 全屏事件
    handleFullScreen() {
      let element = document.documentElement;
      if (element.requestFullscreen) {
        element.requestFullscreen();
      } else if (element.webkitRequestFullScreen) {
        element.webkitRequestFullScreen();
      } else if (element.mozRequestFullScreen) {
        element.mozRequestFullScreen();
      }
    },
    handleExitFullScreen() {
      if (document.exitFullscreen) {
        document.exitFullscreen();
      } else if (document.webkitCancelFullScreen) {
        document.webkitCancelFullScreen();
      } else if (document.mozCancelFullScreen) {
        document.mozCancelFullScreen();
      } else if (document.msExitFullscreen) {
        document.msExitFullscreen();
      }
    }
  },
  mounted() {
    this.getDataPage(this.currentPage, this.pageSize);
  },
  data() {
    return {
      examination: {
        id: "",
        name: "",
        subjectName: "",
        type: "",
        time: "",
        score: "",
        testpaper: "",
        qualifiedscore: "",
        password: ""
      },
      total: 0,
      pageSize: 5,
      currentPage: 1,
      tableData: [],
      dialogFormVisible: false,
      dialogFormVisibleExam: false,
      testPaper: {},
      joinid: "",
      seconds: 0,
      djs: "",
      timer:null
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

#timerClass {
  position: fixed;
  width: 160px;
  left: 20px;
  padding-left: 20px;
  font-size: 17px;
  background:white;
}
</style>