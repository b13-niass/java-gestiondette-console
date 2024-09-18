package com.example.odc;


import com.example.odc.services.ArticleService;
import com.example.odc.views.LoginView;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.odc")
public class Main {

    public static void main(String[] args) {
//        LoginView loginView = new LoginView();
//        loginView.displayLoginScreen();
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        ArticleService service = context.getBean(ArticleService.class);

        System.out.println(service);

    }
}
