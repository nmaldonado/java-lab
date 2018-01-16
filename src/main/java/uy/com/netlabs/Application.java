package uy.com.netlabs;

import com.github.javafaker.Faker;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import uy.com.netlabs.Controllers.CategoryController;
import uy.com.netlabs.Controllers.ProductController;
import uy.com.netlabs.Controllers.TransactionController;
import uy.com.netlabs.Controllers.TransactionDetailController;
import uy.com.netlabs.Controllers.impl.CategoryControllerImpl;
import uy.com.netlabs.Controllers.impl.ProductControllerImpl;
import uy.com.netlabs.Controllers.impl.TransactionControllerImpl;
import uy.com.netlabs.Controllers.impl.TransactionDetailControllerImpl;
import uy.com.netlabs.exceptions.ErrorLowStockException;
import uy.com.netlabs.model.Category;
import uy.com.netlabs.model.Product;
import uy.com.netlabs.model.Transaction;
import uy.com.netlabs.model.TransactionDetail;
import uy.com.netlabs.service.CategoryService;
import uy.com.netlabs.service.ProductService;
import uy.com.netlabs.service.TransactionDetailService;
import uy.com.netlabs.service.TransactionService;
import uy.com.netlabs.service.impl.CategoryServiceImpl;
import uy.com.netlabs.service.impl.ProductServiceImpl;
import uy.com.netlabs.service.impl.TransactionDetailServiceImpl;
import uy.com.netlabs.service.impl.TransactionServiceImpl;

import java.util.Calendar;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages={"uy.com.netlabs"})
public class Application {

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    @Autowired
    private TransactionService transactionService = new TransactionServiceImpl();

    @Autowired
    private TransactionDetailService transactionDetailService = new TransactionDetailServiceImpl();

    @Autowired
    private ProductService productService = new ProductServiceImpl();

    @Autowired
    private CategoryService categoryService = new CategoryServiceImpl();

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }



        /**Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        int choice;

        GDC gdc = new GDC();
        gdc.loadInicialData();


        while (!exit) {

            System.out.println("1 - Buy a product ");
            System.out.println("2 - List all transactions ");
            System.out.println("3 - List all products ");
            System.out.println("0 - Exit");

            try {
                System.out.println("Enter your choice ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Choice 1, buy a product.");
                        gdc.transactionProcess();
                        break;
                    case 2:
                        System.out.println("Choice 2, Show all transactions.");
                        gdc.listAllTransactions();
                        break;
                    case 3:
                        System.out.println("Choice 3, Show all products.");
                        gdc.listAllProducts();
                        break;
                    case 0:
                        System.out.println("Bye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Only numbers between 0 & 3");
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("Must insert a number");
                scanner.next();
            }
        }**/



    public void listAllProducts(){
        ProductService productService= new ProductServiceImpl();

        for (Product product: productService.listProducts()
             ) {
            System.out.println(product.toString());
            System.out.println("_________________");
        }
    }

    public void listAllTransactions(){




        for (Transaction t:transactionService.listTransactions()
                ) {

            System.out.println(t);
            System.out.println("    -----------");
            for (TransactionDetail td:t.getTransactionDetail()
                    ) {
                System.out.println("    Detail");
                System.out.println("    -----------");
                System.out.println("    "+td.getProduct().getId() +"| "+td.getProduct().getName() +"| " +   productService.getFinalPrice(td.getProduct()));
                System.out.println("    ###########");
            }
        }

    }

    public void transactionProcess(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        int choice;
        int quantity;

        System.out.println("Select a product from the list ");
        System.out.println("_______________________________");
        for (Product product:  productService.listProducts()
             ) {
            System.out.println(product.toString());
            System.out.println("---------------------");
        }
        choice = scanner.nextInt();
        Product product = null;

        while (!productService.exist(choice)){
            System.out.println("Product not found, select again");
            System.out.println("_______________________________");
            choice = scanner.nextInt();

        }
        product = productService.findById(choice);
        System.out.println("Product found!");
        System.out.println("______________");
        System.out.println(product.toString());

        System.out.println("Select quantity:");
        System.out.println("______________");
        quantity= scanner.nextInt();
        while(quantity < 1){
            System.out.println("Select a valid quantity");
            quantity= scanner.nextInt();
        }

        System.out.println("You will buy "+ quantity + " of "+ product.getName() +", unit price:" + product.getPrice() +
                " with "+product.getCategory().getDiscountPercentage() + "% of discount, at total cost of:" + (productService.getFinalPrice(product) * quantity));

        System.out.println("Confirm? 1-yes | 0-no");
        System.out.println("______________________");


        while (!exit) {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    try{
                        Transaction transaction = registerTransaction(product,quantity);
                        System.out.println("||||||||||||||||||||||||||||||||||||||||||||");
                        System.out.println("Transaction confirmed, here are your details");

                        System.out.println(transaction.toString());
                        System.out.println("    ############");
                        for (TransactionDetail td:transaction.getTransactionDetail()
                                ) {
                            System.out.println("    Detail");
                            System.out.println("    -----------");
                            System.out.println("    "+td.getProduct().getName() + "| unit price:"+ product.getPrice() +
                                    " with "+ product.getCategory().getDiscountPercentage() + "% of discount" + " final price: " + productService.getFinalPrice(product));
                            System.out.println("    -----------");

                        }
                        System.out.println("||||||||||||||||||||||||||||||||||||||||||||");

                        exit=true;
                    }catch (ErrorLowStockException e){
                        System.out.println("||||||||||||||||||||||||||||||||||||||||||||");
                        System.out.println("There is not enough stock to register this transaction, sorry :(");
                        exit=true;
                    }
                    break;
                case 0:
                    exit=true;
                    System.out.println("bye...");
                    break;
                default:
                    System.out.println("Select only 1 or 0");
            }
        }

    }

    public Transaction registerTransaction(Product product, int quantity) throws ErrorLowStockException{


        Transaction transaction = new Transaction();
        TransactionDetail transactionDetail = transactionDetailService.createTransactionDetail();

        //Product
        ProductController productController = new ProductControllerImpl();

        if(product.getStock()>=quantity){
            product.setStock(product.getStock()-quantity);
            productService.updateProduct(product);
            transactionDetail.setQuantity(quantity);
        }else{
            throw new ErrorLowStockException("Low stock error");
        }



        transactionDetail.setProduct(product);
        //TODO here must check changes in the discount to apply
        transactionDetail.setDiscountApplied(product.getCategory().getDiscountPercentage());
        transaction.addTransactionDetails(transactionDetail);
        transaction.setDate(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));

        transaction.setTotalPrice(productService.getFinalPrice(product)*quantity);
        transaction.addTransactionDetails(transactionDetail);

        transactionService.save(transaction);
        transactionDetailService.registerTransactionDetail(transactionDetail);

        return  transaction;
    }

    public void loadInicialData(){

        Faker faker = new Faker();

        double min=0.50, max=200;
        int minstock=10,maxStock=25;

        for (int i=0;i<=10;i++){
            Category category = new Category();
            Product product = new Product();

            category.setCiudad(faker.pokemon().location());
            category.setDiscountPercentage(10 + (int)(Math.random() * ((50 - 10) + 1)));
            categoryService.save(category);

            product.setName(faker.pokemon().name());
            product.setCategory(category);
            product.setPrice(min + Math.random() * (max - min));
            product.setStock(minstock + (int)(Math.random() * ((maxStock - minstock) + 1)));
            productService.save(product);
        }




        System.out.println("Inital data loaded.");

    }

    }
