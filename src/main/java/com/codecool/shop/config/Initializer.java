package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier samsung = new Supplier("Samsung", "Information and communications technology, medical and health care services");
        supplierDataStore.add(samsung);
        Supplier toshiba = new Supplier("Toshiba", " Information technology and communications equipment and systems");
        supplierDataStore.add(toshiba);
        Supplier logitech = new Supplier("Logitech", "Provider of personal computer and mobile peripherals");
        Supplier microsoft = new Supplier("Microsoft", "Develops, manufactures, licenses, supports and sells computer software, consumer electronics, personal computers, and related services");
        supplierDataStore.add(microsoft);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory notebook = new ProductCategory("Notebook", "Hardware", "A notebook is a small, portable personal computer (PC) with a \"clamshell\" form factor.");
        productCategoryDataStore.add(notebook);
        ProductCategory webcam = new ProductCategory("Webcam", "Hardware", "A webcam is a video camera that feeds or streams its image in real time to or through a computer to a computer network.");
        productCategoryDataStore.add(webcam);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Samsung Notebook 9 Pro Pen", 1049.99f, "USD", "The Samsung Notebook 9 Pro Pen is engineered for people who are going places. You can count on exceptional processing power and fast charging to keep you moving forward and a lightweight frame that won’t weigh you down. Plus, with the versatility of 360-degree tablet mode, a touch screen, S Pen, and extreme processing power - consider yourself empowered!", notebook, samsung));
        productDataStore.add(new Product("Toshiba Portege Z30-A Laptop", 299f, "USD", "The Toshiba Portege Z30 is a business-oriented 13-inch Ultrabook. That gets you the slim profile and light body of an Ultrabook, but with the extra security and office-friendly features you’d expect of a business laptop.", notebook, toshiba));
        productDataStore.add(new Product("Lenovo Flex 14 2-in-1 Convertible Laptop", 650, "USD", "The Lenovo flex 14 Convertible touch screen laptop can help make your ideas happen.", notebook, lenovo));
        productDataStore.add(new Product("Logitech HD Pro Webcam C920", 59.90f, "USD", "With spectacular video quality up to HD 1080p and dual built-in mics, C920 makes it a breeze to make your video presence stand out from the crowd.", webcam, logitech));
        productDataStore.add(new Product("Logitech HD Webcam C525", 59.99f, "USD", " For portable HD video calling and recording-with autofocus.", webcam, logitech));
        productDataStore.add(new Product("Microsoft Q2F-00013 USB 2.0 LifeCam Webcam", 49, "USD", "The closest to being there. Experience the amazing clarity and detail of HD video with brilliant color and crystal-clear audio.", webcam, microsoft));


    }
}
