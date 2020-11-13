package d;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {
    public static void main(String[] args) {
        // 1����������������
        AutoGenerator mpg = new AutoGenerator();
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 2��ȫ������
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("zwq");
        gc.setOpen(false); //���ɺ��Ƿ����Դ������
        gc.setFileOverride(false); //��������ʱ�ļ��Ƿ񸲸�
        gc.setServiceName("I%sService"); //ȥ��Service�ӿڵ�����ĸI
        gc.setIdType(IdType.ASSIGN_ID); //��������
        gc.setDateType(DateType.ONLY_DATE);//�������ɵ�ʵ��������������
        gc.setSwagger2(false);//����Swagger2ģʽ
        mpg.setGlobalConfig(gc);

        // 3������Դ����
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4��������
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null); //ģ����
        pc.setParent("cn.test.project");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5����������
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("book");//����Щ�����ɴ��룬�����Ƕ��ű�
        strategy.setNaming(NamingStrategy.underline_to_camel);//���ݿ��ӳ�䵽ʵ�����������
        strategy.setTablePrefix(pc.getModuleName() + "_"); //����ʵ��ʱȥ����ǰ׺

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//���ݿ���ֶ�ӳ�䵽ʵ�����������
        strategy.setEntityLombokModel(false); // lombok ģ�� @Accessors(chain = true) setter��ʽ����

        strategy.setRestControllerStyle(true); //restful api��������
        strategy.setControllerMappingHyphenStyle(true); //url���շ�ת���ַ�

        mpg.setStrategy(strategy);

        // 6��ִ��
        mpg.execute();
    }
}
