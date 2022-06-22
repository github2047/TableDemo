<template>
  <div>
    <el-scrollbar style="max-height: 1080px">
      <div class="common-layout" style="height: 100vh">
        <el-container>
          <el-container>
            <el-aside class="set-el-aside" style="width: 200px;">
              <el-row style="background: #545c64;width:200px;height: 100vh">
                <el-col>
                  <el-menu
                      :default-active="this.activeIndex"
                      active-text-color="#ffd04b"
                      background-color="#545c64"
                      class="el-menu-vertical-demo"
                      text-color="#fff"
                      :router=true

                  >
                    <el-menu-item index="/" style="width: 200px">
                      <span style="font-size: 18px;">用户报表</span>
                    </el-menu-item>
                  </el-menu>
                </el-col>
              </el-row>
            </el-aside>
            <el-main>
              <router-view />
            </el-main>
          </el-container>
        </el-container>

      </div>
    </el-scrollbar>
  </div>
</template>

<script>
let ElMessage = window.ELEMENT.Message;
module.exports= {
  name: 'headView',
  data(){
    return{
      activeIndex:this.$route.path,
    }
  },
  mounted() {

  },
  methods: {

  }
};
</script>

<style scoped>
.el-menu-item.is-active {
  background: #7a7a77;
  color: black;
}
.common-layout .el-scrollbar__wrap {
  overflow-x: hidden;
}
</style>