package com.webprog.demo1.service.implementaion;

import com.webprog.demo1.model.Product;
import com.webprog.demo1.model.ShoppingCart;
import com.webprog.demo1.model.User;
import com.webprog.demo1.model.enumerations.ShoppingCartStatus;
import com.webprog.demo1.model.exceptions.ProductAlreadyInShoppingCartException;
import com.webprog.demo1.model.exceptions.ProductNotFoundException;
import com.webprog.demo1.model.exceptions.ShoppingCartNotFoundException;
import com.webprog.demo1.model.exceptions.UserNotFoundException;
import com.webprog.demo1.repository.InMemoryShoppingCartRepository;
import com.webprog.demo1.repository.InMemoryUserRepository;
import com.webprog.demo1.service.ProductService;
import com.webprog.demo1.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final InMemoryShoppingCartRepository shoppingCartRepository;
    private final InMemoryUserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository shoppingCartRepository,
                                   InMemoryUserRepository userRepository,
                                   ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if (this.shoppingCartRepository.findById(cartId).isEmpty())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return this.shoppingCartRepository
                .findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    User user = this.userRepository.findByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException(username));
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if (shoppingCart.getProducts()
                .stream().filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0) {
            throw new ProductAlreadyInShoppingCartException(productId, username);
        }
        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }

}
