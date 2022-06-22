<template>
  <div>
    <div class="box" v-loading="boxLoading" element-loading-text="拼命加载中">
        <el-row :gutter="20" style="text-align: center">
          <el-col :span="6">
            <span style="font-size: 14px;color:#606266;">用户名称：</span>
            <el-input v-model="userName" clearable size="mini" placeholder="请输入关键字" style="width:70%"></el-input>
          </el-col>
          <el-col :span="6">
            <span style="font-size: 14px;color:#606266;">部门名称：</span>
            <el-input v-model="deptName" clearable size="mini" placeholder="请输入关键字" style="width: 70%;"></el-input>
          </el-col>
          <el-col :span="6">
            <span style="font-size: 14px;color:#606266;">完成时间：</span>
            <el-date-picker style="width: 70%;"
                            v-model="times"
                            size="mini"
                            type="daterange"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            value-format="yyyy-MM-dd"
                            align="right">
            </el-date-picker>
          </el-col>
          <el-col :span="6">
            <span style="font-size: 14px;color:#606266;">设备名称：</span>
            <el-input v-model="deviceName" clearable size="mini" placeholder="请输入关键字" style="width: 70%;"></el-input>
          </el-col>
          <el-col :span="6">
            <span style="font-size: 14px;color:#606266;display: inline-block;width: 70px;text-align: right">设备IP：</span>
            <el-input v-model="deviceIp" clearable size="mini" placeholder="请输入关键字" style="width: 70%;margin-top: 10px;"></el-input>
          </el-col>
        </el-row>
        <div style="margin-top: 10px;">
          <el-button type="success" size="mini" plain   @click="search()" icon="el-icon-search">搜索</el-button>
          <el-button type="primary" size="mini" style="margin-left: 20px;" :loading="exclLoading" @click="exportExcl()" icon="el-icon-document">打印报表</el-button>
        </div>
        <div class="con">
          <el-scrollbar style="height:75vh">
            <el-scrollbar style="height: 90%">
              <div style="margin-bottom: 20px;">
                <el-table
                    :data="tableData"
                    border
                    @sort-change="sortChange"
                    size="mini"
                    style="width: 100%;">
                  <el-table-column
                      type="index"
                      width="50">
                  </el-table-column>
                  <el-table-column
                      prop="deptName"
                      label="部门名称"
                      show-overflow-tooltip
                      width="150">
                    <template slot-scope="scope">
                      <span>{{scope.row.deptName==null?'未知部门':scope.row.deptName}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column
                      prop="userName"
                      label="用户名称"
                      width="180">
                    <template slot-scope="scope">
                      <span>{{scope.row.userName==null?'未知用户':scope.row.userName}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column
                      prop="deviceIp"
                      label="设备IP"
                      width="180">
                  </el-table-column>
                  <el-table-column
                      prop="deviceId"
                      label="设备mac地址"
                      width="180">
                  </el-table-column>
                  <el-table-column
                      prop="deviceName"
                      label="设备名称"
                      width="180">
                  </el-table-column>
                  <el-table-column
                      sortable="custom"
                      prop="colorTotal"
                      label="彩色总数"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      sortable="custom"
                      prop="scanFaxScans"
                      label="扫描总数"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      sortable
                      prop="monochromeTotal"
                      label="单色总数"
                      width="100">
                  </el-table-column>
                  <el-table-column
                      sortable="custom"
                      prop="total"
                      label="总数"
                      width="100">
                  </el-table-column>
<!--                  <el-table-column-->
<!--                      sortable="custom"-->
<!--                      prop="copyFullColor"-->
<!--                      label="以全彩色复制的总页数"-->
<!--                      width="170">-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      sortable="custom"-->
<!--                      prop="faxTx"-->
<!--                      label="已发送传真中的总页数"-->
<!--                      width="170">-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      sortable="custom"-->
<!--                      prop="faxRx"-->
<!--                      label="已接收传真中的总页数"-->
<!--                      width="170">-->
<!--                  </el-table-column>-->
                  <el-table-column
                      sortable="custom"
                      prop="duplexTotal"
                      label="双工总数"
                      width="100">
                  </el-table-column>
<!--                  <el-table-column-->
<!--                      sortable="custom"-->
<!--                      prop="numberOfOriginals"-->
<!--                      label="显示原始页面的总数"-->
<!--                      width="170">-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      sortable="custom"-->
<!--                      prop="copyBlackLarge"-->
<!--                      label="以黑色复制的总页数"-->
<!--                      width="170">-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      sortable="custom"-->
<!--                      prop="sheetsOfPrints"-->
<!--                      label="已打印的纸张总数"-->
<!--                      width="170">-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      sortable="custom"-->
<!--                      prop="printTotalLarge"-->
<!--                      label="所有颜色打印的总页数"-->
<!--                      width="180">-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      sortable="custom"-->
<!--                      prop="printFullColorLarge"-->
<!--                      label="以全彩色打印的总页数"-->
<!--                      width="180">-->
<!--                  </el-table-column>-->
                  <el-table-column
                      sortable="custom"
                      prop="rectime"
                      label="完成时间"
                      width="170">
                  </el-table-column>
                </el-table>
              </div>
            </el-scrollbar>
            <div style="margin-top: 10px;text-align: right">
              <el-pagination
                  :page-size="pageSize"
                  :current-page="pageNo"
                  :page-sizes="[15,20,30,40,50]"
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  background
                  small
                  :pager-count=5
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="total">
              </el-pagination>
            </div>
          </el-scrollbar>
        </div>
      </div>
  </div>
</template>
<script>
let ElMessage = window.ELEMENT.Message;
module.exports = {
  name: "index",
  data(){
    return{
      pageNo:1,
      pageSize:15,
      total:0,
      tableData: [],
      times:[],
      userName:'',
      deptName:'',
      deviceName:'',
      deviceIp:'',
      exclLoading:false,
      boxLoading:false
    }
  },
  mounted() {
    this.getTableInfo();
  },
  methods:{
    getTableInfo(prop,order){
      if(this.boxLoading){
        return;
      }
      this.boxLoading=true;
      let start="";
      let end=""
      if(this.times==null||this.times=='null' ||this.times=='' ||this.times=="undefined"){
        start = '';
        end = '';
      }else {
        start=this.times[0];
        end=this.times[1];
      }
      // setTimeout(r=>{
        axios.post("/platUserCounter/selectAll", {
          "deviceName":this.deviceName,
          "deviceIp":this.deviceIp,
          "userName":this.userName,
          "deptName":this.deptName,
          "start":start,
          "end":end,
          "pageNo":this.pageNo,
          "pageSize":this.pageSize,
          "prop":prop,
          "order":order
        }).then(({data})=>{
          if(data.code==200) {
            this.total = data.data.total
            this.tableData = data.data.list;
          }else{
            ElMessage.error(data.msg)
          }
        }).catch(e=>{
          ElMessage.error(e.message);
        }).finally(f=>{
          this.boxLoading=false;
        })
      // },5000);
    },
    getDept(dept,deptName){
      axios.post("/platUserCounter/getDept",{
        "id":dept.parentId
      }).then(r=>{
        deptName=r.data.departName+"/"+deptName
        // console.log("parentId",r.data.parentId)
        if(r.data.parentId!=0 && r.data.parentId!=null){
          this.getDept(r.data,deptName);
          return;
        }
        console.log("name",deptName)
      }).catch(e=>{
        ElMessage.error(e.message);
      })
      return deptName;
    },
    search(){
      this.getTableInfo()
    },
    handleSizeChange(value){
      this.pageSize=value;
      this.getTableInfo();
    },
    handleCurrentChange(value){
      this.pageNo=value;
      this.getTableInfo();
    },
    exportExcl(){
      let start="";
      let end=""
      if(this.times!=null){
        start=this.times[0];
        end=this.times[1];
      }else{
        start="";
        end="";
      }
      if(start==""||end==""||start==null||end==null||start=="undefined"||end=="undefined"){
        ElMessage.error("请选择导出的时间范围")
      }else{
        this.$confirm('你要导出的是'+start+'到'+end+'的数据，请确定是否正确', '提示', {
          closeOnClickOutside: false,
          closeOnPressEscape: false,
          closeOnHashChange: false,
          closeOnClickModal: false,
          confirmButtonText: '取消',
          cancelButtonText: '确定',
          type: 'warning'
        }).then(() => {
          return;
        }).catch(() => {
          if(this.exclLoading){
            return;
          }
          this.exclLoading=true;
          setTimeout(r=>{
            axios.post("/platUserCounter/exportExcl",{
              "excelTitle":"用户报表",
              "deviceName":this.deviceName,
              "deviceIp":this.deviceIp,
              "userName":this.userName,
              "deptName":this.deptName,
              "start":start,
              "end":end
            }).then(({data})=>{

            }).catch(e=>{
              ElMessage.error(e.message)
            }).finally(f=>{
              this.exclLoading=false;
            })
          },2000)
        });
      }
    },
    sortChange(column){
      this.getTableInfo(column.prop,column.order)
    },
  }
}
</script>

<style scoped>
.header{
  width: 98%;
  height: 20px;
  padding: 15px;
  margin-top: 10px;
  font-size: 18px;
  background: #f5f5f5;
}
.box{
  width: 98%;
  margin: 10px auto;
}
.con{
  width: 100%;
  margin-top: 10px;
}
.con .el-scrollbar__wrap {
  overflow-x: hidden;
}
</style>