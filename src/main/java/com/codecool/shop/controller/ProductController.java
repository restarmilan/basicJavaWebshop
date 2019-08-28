package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        BufferedReader reader = req.getReader();
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject json = new JSONObject(tokener);
        //String jsonResponse = new Gson().toJson(json);
        //resp.setContentType("application/json");
        //resp.setCharacterEncoding("UTF-8");
        System.out.println(json.get("id"));
        resp.getWriter().print(json);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category1", productCategoryDataStore.find(1));
        context.setVariable("products1", productDataStore.getBy(productCategoryDataStore.find(1)));
        context.setVariable("category2", productCategoryDataStore.find(2));
        context.setVariable("products2", productDataStore.getBy(productCategoryDataStore.find(2)));
        context.setVariable("category3", productCategoryDataStore.find(3));
        context.setVariable("products3", productDataStore.getBy(productCategoryDataStore.find(3)));

        List<ProductCategory> productCategories = productCategoryDataStore.getAll();
        List<Supplier> suppliers = supplierDao.getAll();

        context.setVariable("productCategories", productCategories);
        context.setVariable("suppliers", suppliers);
        context.setVariable("Amazon", productDataStore.getBy(supplierDao.find(1)));
        context.setVariable("Lenovo", productDataStore.getBy(supplierDao.find(2)));
        context.setVariable("Samsung", productDataStore.getBy(supplierDao.find(3)));
        context.setVariable("Toshiba", productDataStore.getBy(supplierDao.find(4)));
        context.setVariable("Logitech", productDataStore.getBy(supplierDao.find(5)));
        context.setVariable("Microsoft", productDataStore.getBy(supplierDao.find(6)));

        String prodCat = req.getParameter("prod-or-cat");
        context.setVariable("prodCat", prodCat);

        engine.process("product/index.html", context, resp.getWriter());
    }

}
// // Alternative setting of the template context
// Map<String, Object> params = new HashMap<>();
// params.put("category", productCategoryDataStore.find(1));
// params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
// context.setVariables(params);