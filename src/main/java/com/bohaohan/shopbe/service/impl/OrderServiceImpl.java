package com.bohaohan.shopbe.service.impl;

import com.bohaohan.shopbe.dto.orderData.OrderDataRequest;
import com.bohaohan.shopbe.dto.orderData.OrderDataResponse;
import com.bohaohan.shopbe.dto.orderProduct.OrderProductRequest;
import com.bohaohan.shopbe.entity.Account;
import com.bohaohan.shopbe.entity.OrderData;
import com.bohaohan.shopbe.entity.OrderProduct;
import com.bohaohan.shopbe.entity.Product;
import com.bohaohan.shopbe.repository.AccountRepository;
import com.bohaohan.shopbe.repository.OrderDataRepository;
import com.bohaohan.shopbe.repository.OrderProductRepository;
import com.bohaohan.shopbe.repository.ProductRepository;
import com.bohaohan.shopbe.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDataRepository orderDataRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    @Transactional
    public OrderDataResponse addOrderData(OrderDataRequest orderDataRequest) {
        Account account = accountRepository.findById(orderDataRequest.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + orderDataRequest.getAccountId()));
        List<OrderProductRequest> orderProductRequests = orderDataRequest.getOrderProductRequests();


        //Lay account ra, lay list orderdata, no la nhung order
        //check xem no null hay ko, neu null, new moi, add vao` 1 orderdata
        List<OrderData> orderDataList = account.getOrderData();

        List<OrderProduct> orderProducts = new ArrayList<>();
        OrderData orderData = new OrderData();

        orderProductRequests.forEach(
                orderProductRequest -> {
                    OrderProduct orderProduct = new OrderProduct();
                    Product product = productRepository.findById(orderProductRequest.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Product Id Not found" + orderProductRequest.getProductId()));
                    orderProduct.setProduct(product);
                    orderProduct.setQuantity(orderProductRequest.getQuantity());
                    orderProduct.setOrderData(orderData);
                    orderProducts.add(orderProduct);
                }
        );

        orderData.setOrderProducts(orderProducts);
        orderData.setOrderDate(new Date());
        orderData.setAccount(account);
        orderData.setPaymentMethod(orderDataRequest.getPaymentMethod());
        orderData.setPaymentStatus(orderDataRequest.getPaymentStatus());
        orderData.setStatus(orderDataRequest.getStatus());

        if (orderDataList == null) {
            orderDataList = new ArrayList<>();
            orderDataList.add(orderData);
        }

        orderDataList.add(orderData);

        accountRepository.save(account);
        return null;
    }


}
