package com.bohaohan.shopbe.service.impl;

import com.bohaohan.shopbe.dto.orderData.OrderDataRequest;
import com.bohaohan.shopbe.dto.orderData.OrderDataResponse;
import com.bohaohan.shopbe.dto.orderProduct.OrderProductRequest;
import com.bohaohan.shopbe.dto.orderProduct.OrderProductResponse;
import com.bohaohan.shopbe.entity.Account;
import com.bohaohan.shopbe.entity.OrderData;
import com.bohaohan.shopbe.entity.OrderProduct;
import com.bohaohan.shopbe.entity.Product;
import com.bohaohan.shopbe.repository.AccountRepository;
import com.bohaohan.shopbe.repository.OrderDataRepository;
import com.bohaohan.shopbe.repository.OrderProductRepository;
import com.bohaohan.shopbe.repository.ProductRepository;
import com.bohaohan.shopbe.service.CartService;
import com.bohaohan.shopbe.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    @Override
    @Transactional
    public OrderDataResponse addOrderData(OrderDataRequest orderDataRequest) {

        // Lay ra account.
        Account account = accountRepository.findById(orderDataRequest.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + orderDataRequest.getAccountId()));

        // Lay list cac orderProductRequest tu` orderData request, orderProductRequest chua' chi? productId va` quantity
        List<OrderProductRequest> orderProductRequests = orderDataRequest.getOrderProductRequests();

        // Chuyen du lieu tu Cart => OrderData => Moi orderData la 1 phat' new,
        // OrderData chua' List<OrderProduct>, nen cung phai new moi List<OrderProduct>
        // Sau do map du lieu tu` cart => List<OrderProduct> => add vao` OrderData
        OrderData orderData = new OrderData();
        List<OrderProduct> orderProducts = new ArrayList<>();

        // Lay account ra, lay list orderData
        // Check xem no null hay ko, neu null, new moi, add vao` 1 orderData
        List<OrderData> orderDataList = account.getOrderData();
        if (orderDataList == null) {
            orderDataList = new ArrayList<>();
        }

        // Map du lieu tu` orderProductRequest sang OrderProduct, sau do' add vao` orderProducts.
        orderProductRequests.forEach(
                orderProductRequest -> {
                    OrderProduct orderProduct = new OrderProduct();
                    Product product = productRepository.findById(orderProductRequest.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Product Id Not found" + orderProductRequest.getProductId()));
                    orderProduct.setProduct(product);
                    orderProduct.setOrderData(orderData);
                    orderProduct.setQuantity(orderProductRequest.getQuantity());
                    orderProducts.add(orderProduct);
                }
        );


        // Luc nay` List<OrderProduct> da~ hoan` thien, tien' hanh` add vao` orderData
        orderData.setOrderProducts(orderProducts);
        // Set cac field con` lai.
        orderData.setOrderDate(new Date());
        orderData.setAccount(account);
        orderData.setPaymentMethod(orderDataRequest.getPaymentMethod());
        orderData.setPaymentStatus(orderDataRequest.getPaymentStatus());
        orderData.setTotalPrice(orderDataRequest.getTotalPrice());
        orderData.setStatus(orderDataRequest.getStatus());
        // 1 OrderData hoan` thien
        //Add vao` orderDataList cua account
        orderDataList.add(orderData);

        //Save lai account
        accountRepository.save(account);

        //Tra ve orderData vua moi add vao`
        OrderDataResponse orderDataResponse = new OrderDataResponse();
        orderDataResponse.setStatus(orderData.getStatus());
        orderDataResponse.setTotalPrice(orderData.getTotalPrice());
        orderDataResponse.setPaymentStatus(orderData.getPaymentStatus());
        orderDataResponse.setPaymentMethod(orderData.getPaymentMethod());
        orderDataResponse.setOrderProductResponses(
                orderData.getOrderProducts().stream().map(orderProduct
                                -> new OrderProductResponse(orderProduct.getId(),
                                orderProduct.getProduct().getId(),
                                orderProduct.getProduct().getName(),
                                orderProduct.getProduct().getPrice(),
                                orderProduct.getProduct().getDescription(),
                                orderProduct.getProduct().getImageURL(),
                                orderProduct.getQuantity()))
                        .collect(Collectors.toList())
        );

        return orderDataResponse;
    }

    @Override
    public OrderDataResponse addOrderDataByAccountId(OrderDataRequest orderDataRequest) {
        // Lay ra account.
        Account account = accountRepository.findById(orderDataRequest.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + orderDataRequest.getAccountId()));

        // Chuyen du lieu tu Cart => OrderData => Moi orderData la 1 phat' new,
        // OrderData chua' List<OrderProduct>, nen cung phai new moi List<OrderProduct>
        // Sau do map du lieu tu` cart => List<OrderProduct> => add vao` OrderData
        OrderData orderData = new OrderData();
        List<OrderProduct> orderProducts = new ArrayList<>();

        // Lay account ra, lay list orderData
        // Check xem no null hay ko, neu null, new moi, add vao` 1 orderData
        List<OrderData> orderDataList = account.getOrderData();
        if (orderDataList == null) {
            orderDataList = new ArrayList<>();
        }

        account.getCart().getCartProducts().forEach(
            cartProduct -> {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setProduct(cartProduct.getProduct());
                orderProduct.setOrderData(orderData);
                orderProduct.setQuantity(cartProduct.getQuantity());
                orderProducts.add(orderProduct);
            }
        );

        // Luc nay` List<OrderProduct> da~ hoan` thien, tien' hanh` add vao` orderData
        orderData.setOrderProducts(orderProducts);
        // Set cac field con` lai.
        orderData.setOrderDate(new Date());
        orderData.setAccount(account);
        orderData.setPaymentMethod(orderDataRequest.getPaymentMethod());
        orderData.setTotalPrice(orderDataRequest.getTotalPrice());
        orderData.setPaymentStatus(orderDataRequest.getPaymentStatus());
        orderData.setStatus(orderDataRequest.getStatus());
        // 1 OrderData hoan` thien
        //Add vao` orderDataList cua account
        orderDataList.add(orderData);

        //Save lai account
        accountRepository.save(account);

        cartService.removeAllProductFromCart(orderDataRequest.getAccountId());

        //Tra ve orderData vua moi add vao`
        OrderDataResponse orderDataResponse = new OrderDataResponse();
        orderDataResponse.setStatus(orderData.getStatus());
        orderDataResponse.setTotalPrice(orderData.getTotalPrice());
        orderDataResponse.setPaymentStatus(orderData.getPaymentStatus());
        orderDataResponse.setPaymentMethod(orderData.getPaymentMethod());
        orderDataResponse.setOrderDate(String.valueOf(orderData.getOrderDate()));
        orderDataResponse.setOrderProductResponses(
                orderData.getOrderProducts().stream().map(orderProduct
                                -> new OrderProductResponse(orderProduct.getId(),
                                orderProduct.getProduct().getId(),
                                orderProduct.getProduct().getName(),
                                orderProduct.getProduct().getPrice(),
                                orderProduct.getProduct().getDescription(),
                                orderProduct.getProduct().getImageURL(),
                                orderProduct.getQuantity()))
                        .collect(Collectors.toList())
        );

        return orderDataResponse;
    }

    @Override
    public List<OrderDataResponse> getAllOrderDataByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));

        return account.getOrderData().stream().map(orderData -> {
            List<OrderProductResponse> orderProductResponses = orderData.getOrderProducts().stream()
                    .map(orderProduct -> new OrderProductResponse(
                            orderProduct.getId(),
                            orderProduct.getProduct().getId(),
                            orderProduct.getProduct().getName(),
                            orderProduct.getProduct().getPrice(),
                            orderProduct.getProduct().getDescription(),
                            orderProduct.getProduct().getImageURL(),
                            orderProduct.getQuantity()
                    ))
                    .collect(Collectors.toList());

            return new OrderDataResponse(
                    orderData.getId(),
                    orderProductResponses,
                    orderData.getPaymentMethod(),
                    orderData.getPaymentStatus(),
                    orderData.getTotalPrice(),
                    String.valueOf(orderData.getOrderDate()),
                    orderData.getStatus()
            );
        }).collect(Collectors.toList());
    }


}
