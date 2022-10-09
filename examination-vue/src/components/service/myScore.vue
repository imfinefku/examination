<template>
  <div>
    <el-table :data="tableData" border style="width: 100%;margin-top: 20px;" :cell-style="cellStyle"
      :header-cell-style="{background: 'rgb(242, 243, 244)',color:'#515a6e'}">
      <el-table-column fixed prop="id" label="ID" v-if="false">
      </el-table-column>
      <el-table-column prop="name" label="考试名称">
      </el-table-column>
      <el-table-column prop="subjectName" label="考试科目">
      </el-table-column>
      <el-table-column prop="score" label="总分">
      </el-table-column>
      <el-table-column prop="qualifiedscore" label="合格分数">
      </el-table-column>
      <el-table-column prop="myScore" label="我的分数">
      </el-table-column>
      <el-table-column prop="pass" label="是否合格">
      </el-table-column>
      <el-table-column :formatter="dateFormat" prop="time" label="考试时间">
      </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="look(scope.row)">查看</el-button>
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
      <el-dialog :visible.sync="dialogFormVisible" :fullscreen="true">
        <div class="questionClass">
          <div class="testPaperHeader">
            <div style="font-size:20px;">{{testPaper.name}}
              <el-button type="primary" plain style="float:right;margin-top: 10px;" @click="dialogFormVisible=false">关闭</el-button>
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
            <div v-if="question.type=='单选题'" style="margin-top:10px;">
              <div>A.{{question.a}}</div>
              <div>B.{{question.b}}</div>
              <div>C.{{question.c}}</div>
              <div>D.{{question.d}}</div>
              <div>
                <el-radio :disabled="true" v-model="question.reply" label="A">A</el-radio>
                <el-radio :disabled="true" v-model="question.reply" label="B">B</el-radio>
                <el-radio :disabled="true" v-model="question.reply" label="C">C</el-radio>
                <el-radio :disabled="true" v-model="question.reply" label="D">D</el-radio>
              </div>
            </div>
            <div v-if="question.type=='判断题'" style="margin-top:10px;">
              <div style="margin-top:10px;">
                <el-radio :disabled="true" v-model="question.reply" label="对">对</el-radio>
                <el-radio :disabled="true" v-model="question.reply" label="错">错</el-radio>
              </div>
            </div>
            <div v-if="question.type=='简答题'" style="margin-top:10px;">
              <div style="margin-top:10px;">
                <el-input :disabled="true" v-model="question.reply" type="textarea" :rows="5">
                </el-input>
              </div>
            </div>
            <div style="margin-top:20px;margin-bottom:20px;color: red;">
              得分：{{question.correctScore}} 分
            </div>
            <div style="margin-top:20px;margin-bottom:20px;color: blue;">
              参考答案：
              {{question.answer}}
            </div>
            <div v-if="question.analysis!=''" style="margin-top:20px;margin-bottom:20px;color: green;">
              解析：
              {{question.analysis}}
            </div>
          </div>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
  
<script>
import formatDate from '../../utils/dateUtil.js';
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
    look(row) {
      if (row.type != 2) {
        this.$message({
          showClose: true,
          type: 'error',
          message: '试卷未批改，无法查看'
        });
        return;
      }
      this.curJoinId = row.joinid;
      this.dialogFormVisible = true;
      const that = this;
      var param = new URLSearchParams();
      param.append("joinid", that.curJoinId);
      that.$http
        .get('/examinationManage/getTestPaperCorrectionByJoinId', {
          params: param
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.testPaper = response.data.data;
          }
        })
    },
    getDataPage(currentPage, pageSize) {
      const that = this;
      var param = new URLSearchParams();
      param.append("page", currentPage);
      param.append("limit", pageSize);
      that.$http
        .get('/examinationManage/getMyScorePage', {
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
    },
    cellStyle(row, column, rowIndex, columnIndex) {
      if (row.column.label === "是否合格") {
        return 'color:' + row.row.color;
      }
    },
    getPreviewImg(url) {
      if (url != null && url != "") {
        url = this.$http.defaults.baseURL + "/file/" + url;
      }
      var urls = new Array();
      urls.push(url);
      return urls;
    },
    getImg(url) {
      if (url != null && url != "") {
        url = this.$http.defaults.baseURL + "/file/" + url;
      }
      return url;
    }
  },
  mounted() {
    this.getDataPage(this.currentPage, this.pageSize);
  },
  data() {
    return {
      testPaper: {},
      curJoinId: "",
      dialogFormVisible: false,
      total: 0,
      pageSize: 5,
      currentPage: 1,
      tableData: []
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