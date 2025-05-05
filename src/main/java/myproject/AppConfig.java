package myproject;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "myproject")
public class AppConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public Handler handlerChain() {
        SugarHandler sugarHandler = new SugarHandler();
        MilkHandler milkHandler = new MilkHandler();
        sugarHandler.setNext(milkHandler);
        return sugarHandler;
    }


    @Bean
    public Menu menu(
            ShoppingCart cart,
            Scanner scanner,
            Handler handlerChain,
            BlackTeaFactory blackTeaFactory,
            CoffeeFactory coffeeFactory,
            GreenTeaFactory greenTeaFactory,
            PaymentStrategyProxy paymentStrategyProxy
    ) {
        return new Menu(
                cart,
                scanner,
                handlerChain,
                blackTeaFactory,
                coffeeFactory,
                greenTeaFactory,
                paymentStrategyProxy
        );
    }

    @Bean
    public List<PaymentStrategy> paymentStrategies() {
        return Arrays.asList(
                new CreditCardStrategy(),
                new DebitCardStrategy(),
                new SoulPaymentStrategy()
        );
    }

    @Bean
    public PaymentStrategyProxy paymentStrategyProxy(
            List<PaymentStrategy> paymentStrategies,
            Scanner scanner
    ) {
        return new PaymentStrategyProxy(paymentStrategies, scanner);
    }
}



