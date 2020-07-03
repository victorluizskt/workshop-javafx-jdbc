package modell.services;

import model.entities.Department;
import model.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsService {

    public List<Product> findAll(){
        List<Product> list = new ArrayList<>();
        list.add(new Product(1, "Notebook Asus", 1500.0, 6));
        list.add(new Product(2, "Mouse gamer", 600.0, 6));
        list.add(new Product(3, "Intel i5", 450.0, 6));
        return list;
    }
}
