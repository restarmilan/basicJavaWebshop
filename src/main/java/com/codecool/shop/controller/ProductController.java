package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        //context.setVariable("category", productCategoryDataStore.find(1));
        //context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        context.setVariable("category1", productCategoryDataStore.find(1));
        context.setVariable("products1", productDataStore.getBy(productCategoryDataStore.find(1)));
        context.setVariable("category2", productCategoryDataStore.find(2));
        context.setVariable("products2", productDataStore.getBy(productCategoryDataStore.find(2)));
        context.setVariable("category3", productCategoryDataStore.find(3));
        context.setVariable("products3", productDataStore.getBy(productCategoryDataStore.find(3)));
        List<ProductCategory> productCategories = productCategoryDataStore.getAll(); // ez kell a <form><select>hez - prod.cat
        List<Supplier> suppliers = supplierDao.getAll(); // ez kell a <selecthez> - suppliers
        context.setVariable("productCategories", productCategories);
        context.setVariable("suppliers", suppliers);
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);

        engine.process("product/index.html", context, resp.getWriter());

        HttpSession session = req.getSession();
        synchronized (session){
            session.setAttribute("cart", cart);// ez csak próba
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println()
    }

}
