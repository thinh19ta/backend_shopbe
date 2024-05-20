package com.bohaohan.shopbe.service.impl;

import com.bohaohan.shopbe.dto.cart.CartRequest;
import com.bohaohan.shopbe.dto.cart.CartResponse;
import com.bohaohan.shopbe.dto.product.ProductResponse;
import com.bohaohan.shopbe.entity.Account;
import com.bohaohan.shopbe.entity.Cart;
import com.bohaohan.shopbe.entity.Product;
import com.bohaohan.shopbe.repository.AccountRepository;
import com.bohaohan.shopbe.repository.CartRepository;
import com.bohaohan.shopbe.repository.ProductRepository;
import com.bohaohan.shopbe.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;


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
    public CartResponse addCart(CartRequest cartRequest) {
        Account account = accountRepository.findById(cartRequest.getAccountId()).orElse(null);
        Product product = productRepository.findById(cartRequest.getProductId()).orElse(null);
        Cart cart = account.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setProducts(new HashSet<>());
            cart.setAccount(account);
            account.setCart(cart);
            System.out.println("Create new cart then set to Account");
        }
        cart.getProducts().add(product);
        accountRepository.save(account);


        Set<ProductResponse> productResponses = cart.getProducts().stream()
                .map(productChild -> new ProductResponse(
                        productChild.getId(),
                        productChild.getName(),
                        productChild.getPrice(),
                        productChild.getDescription(),
                        productChild.getImageURL()
                ))
                .collect(Collectors.toSet());
        return new CartResponse(productResponses);
    }

    @Override
    public List<ProductResponse> getCartByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        Cart cart = account.getCart();
        if (cart != null) {
            List<ProductResponse> productResponses = cart.getProducts().stream()
                    .map(productChild -> new ProductResponse(
                            productChild.getId(),
                            productChild.getName(),
                            productChild.getPrice(),
                            productChild.getDescription(),
                            productChild.getImageURL()
                    ))
                    .collect(Collectors.toList());
            return productResponses;
        }
        return null;
    }

    @Override
    public void removeProductFromCart(CartRequest cartRequest) {
        Account account = accountRepository.findById(cartRequest.getAccountId()).orElse(null);
        Product product = productRepository.findById(cartRequest.getProductId()).orElse(null);
        account.getCart().getProducts().remove(product);
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
            cart.getProducts().clear();
            account.setCart(null);
            cartRepository.delete(cart);
            accountRepository.save(account);
        }
    }

}
