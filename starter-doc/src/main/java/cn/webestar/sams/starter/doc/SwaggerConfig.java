package cn.webestar.sams.starter.doc;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
//@EnableSwagger2
public class SwaggerConfig {

//    @Bean(value = "defaultApi2")
//    public Docket defaultApi2() {
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("问向4.0接口文档")
//                        .description("# WXCLOUD4 RESTful APIs")
//                        .termsOfServiceUrl("http://webestar.cn")
//                        .contact(new Contact("WenXiang", "http://webestar.cn", "bestaone@163.com"))
//                        .version("V4.0")
//                        .build())
//                .groupName("V4.0")
//                .select()
//                //这里指定你自己的Controller扫描包路径
//                .apis(RequestHandlerSelectors.basePackage("cn.webestar.sams"))
//                .paths(PathSelectors.any())
//                .build();
//        return docket;
//    }

}