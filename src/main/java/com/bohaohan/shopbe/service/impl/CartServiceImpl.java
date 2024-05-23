package com.bohaohan.shopbe.service.impl;

import com.bohaohan.shopbe.dto.cart.CartRequest;
import com.bohaohan.shopbe.dto.cart.CartResponse;
import com.bohaohan.shopbe.dto.cartProduct.CartProductRequest;
import com.bohaohan.shopbe.dto.cartProduct.CartProductResponse;
import com.bohaohan.shopbe.dto.product.ProductResponse;
import com.bohaohan.shopbe.entity.Account;
import com.bohaohan.shopbe.entity.Cart;
import com.bohaohan.shopbe.entity.CartProduct;
import com.bohaohan.shopbe.entity.Product;
import com.bohaohan.shopbe.repository.AccountRepository;
import com.bohaohan.shopbe.repository.CartProductRepository;
import com.bohaohan.shopbe.repository.CartRepository;
import com.bohaohan.shopbe.repository.ProductRepository;
import com.bohaohan.shopbe.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartProductRepository cartProductRepository;


    //Working but there are some problems need to modified
//    @Override
//    public CartResponse addCart(CartRequest cartRequest) {
//        Account account = accountRepository.findById(cartRequest.getAccountId()).orElse(null);
//        if (account.getCart() == null) {
//            account.setCart(new Cart());
//            account.getCart().setAccount(accountRepository.findById(cartRequest.getAccountId()).orElse(null));
//            account.getCart().setProducts(new HashSet<>());
//            account.getCart().getProducts().add(productRepository.findById(cartRequest.getProductId()).orElse(null));
//            accountRepository.save(account);
//        } else {
//            account.getCart().getProducts().add(productRepository.findById(cartRequest.getProductId()).orElse(null));
//            accountRepository.save(account);
//        }
//        Set<Product> products = account.getCart().getProducts();
//        Set<ProductResponse> productResponses = products.stream()
//                .map(product -> new ProductResponse(
//                        product.getId(),
//                        product.getName(),
//                        product.getPrice(),
//                        product.getDescription(),
//                        product.getImageURL()
//                ))
//                .collect(Collectors.toSet());
//        return new CartResponse(productResponses);
//    }

    @Override
    public List<CartProductResponse> addToCart(CartProductRequest cartProductRequest) {
        Account account = accountRepository.findById(cartProductRequest.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + cartProductRequest.getAccountId()));
        Product product = productRepository.findById(cartProductRequest.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + cartProductRequest.getProductId()));
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(product);
        cartProduct.setQuantity(cartProductRequest.getQuantity());
        Cart cart = account.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setCartProducts(new ArrayList<>());
            cart.setAccount(account);
            account.setCart(cart);
            System.out.println("Create new cart then set to Account");
        }
        cart.getCartProducts().add(cartProduct);
        cartProduct.setCart(cart);
        cartProductRepository.save(cartProduct);
        accountRepository.save(account);


        List<CartProductResponse> cartProducts = cart.getCartProducts().stream()
                .map(cartProduct1 -> new CartProductResponse(
                        cartProduct1.getId(),
                        cartProduct1.getProduct().getName(),
                        cartProduct1.getProduct().getPrice(),
                        cartProduct1.getProduct().getDescription(),
                        cartProduct1.getProduct().getImageURL(),
                        cartProduct1.getQuantity()
                ))
                .collect(Collectors.toList());
        return cartProducts;
    }

    @Override
    public List<CartProductResponse> getCartByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));
        Cart cart = account.getCart();
        if (cart != null) {
            List<CartProductResponse> cartProductResponses = cart.getCartProducts().stream()
                    .map(cartProduct1 -> new CartProductResponse(
                            cartProduct1.getId(),
                            cartProduct1.getProduct().getName(),
                            cartProduct1.getProduct().getPrice(),
                            cartProduct1.getProduct().getDescription(),
                            cartProduct1.getProduct().getImageURL(),
                            cartProduct1.getQuantity()
                    ))
                    .collect(Collectors.toList());
            return cartProductResponses;
        }
        return null;
    }

    @Override
    public void removeCartProductFromCart(CartProductRequest cartProductRequest) {
        Account account = accountRepository.findById(cartProductRequest.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + cartProductRequest.getAccountId()));
        CartProduct cartProduct = cartProductRepository.findById(cartProductRequest.getId()).orElse(null);
        account.getCart().getCartProducts().remove(cartProduct);
        accountRepository.save(account);
    }

    @Override
    public void removeCart(Long accountId) {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        if (!accountOpt.isPresent()) {
            throw new RuntimeException("Account not found");
        }
        Account account = accountOpt.get();
        Cart cart = account.getCart();
        if (cart != null) {
            cart.getCartProducts().clear();
            account.setCart(null);
            cartRepository.delete(cart);
            accountRepository.save(account);
        }
    }

}
