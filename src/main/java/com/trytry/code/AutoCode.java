package com.trytry.code;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

//代码生成器
public class AutoCode {

    public static void main(String[] args) {
        //需要构建一个代码生成器对象
        AutoGenerator mpg = new AutoGenerator();
        //配置策略
        //1、全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取当前系统目录
        String projectPath = System.getProperty("user.dir");
        //输出到的目录
        gc.setOutputDir(projectPath+"/src/main/java");
        //作者
        gc.setAuthor("111");
        //是否打开资源管理器
        gc.setOpen(false);
        //重新生成时文件是否覆盖
        gc.setFileOverride(false);
        //去掉service的i前缀
        gc.setServiceName("%sService");
        //主键生成策略
        gc.setIdType(IdType.ID_WORKER);
        //日期类型
        gc.setDateType(DateType.ONLY_DATE);
        //自动配置swagger文档
//        gc.setSwagger2(true);

        mpg.setGlobalConfig(gc);
        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3307/dy_db?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        //数据库类型
        dsc.setDbType(DbType.MYSQL);

        mpg.setDataSource(dsc);


        //3、包的配置
        PackageConfig pc = new PackageConfig();
        //设置模块名字
//        pc.setModuleName("blog");
        pc.setParent("com.trytry");
        //实体类
        pc.setEntity("domain");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");

        mpg.setPackageInfo(pc);

        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        //映射的表名
        strategy.setInclude("admin","category_type","city","comment","district","good","good_category","good_photo","order",
                "order_information","province","receiving_address","report_information","school","userinfo","wantbuy_information");
        //包的命名规则，下划线转驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //列的命名规则，下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        //自动生成lombok
        strategy.setEntityLombokModel(true);

        //乐观锁
//        strategy.setVersionFieldName("version");
        //开启驼峰命名格式
        strategy.setRestControllerStyle(true);
        //url中使用下划线命名
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        //执行代码构造器
        mpg.execute();
    }

}
