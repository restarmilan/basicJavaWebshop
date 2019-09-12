package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
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
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    private String infoText = "Your shopping cart is empty";

    /*@Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        BufferedReader reader = req.getReader();
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject json = new JSONObject(tokener);
        //String jsonResponse = new Gson().toJson(json);
        //resp.setContentType("application/json");
        //resp.setCharacterEncoding("UTF-8");
        //resp.getWriter().print(json);


    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession(false) == null){
            HttpSession session = req.getSession(true);
            session.setAttribute("name", "Guest");
            session.setAttribute("loginStatus", false);
            session.setAttribute("cart", new CartDaoMem());
        }
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<ProductCategory> productCategories = productCategoryDataStore.getAll();
        List<Supplier> suppliers = supplierDao.getAll();
        context.setVariable("productCategories", productCategories);
        context.setVariable("suppliers", suppliers);
        context.setVariable("infoText", infoText);

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("products", productDataStore.getAll());


        String prodCat = req.getParameter("prod-or-cat");
        context.setVariable("prodCat", prodCat);

        engine.process("product/index.html", context, resp.getWriter());
    }

}
