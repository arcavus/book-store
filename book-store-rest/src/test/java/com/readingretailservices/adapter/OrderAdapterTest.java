package com.readingretailservices.adapter;


import com.bookstore.adapter.impl.OrderAdapterImpl;
import com.bookstore.domain.OrderDomain;
import com.bookstore.domain.PageResponse;
import com.bookstore.entity.OrderEntity;
import com.bookstore.mapper.OrderEntityMapper;
import com.bookstore.repository.OrderRepository;
import com.readingretailservices.utils.Utils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderAdapterTest {
    @InjectMocks
    OrderAdapterImpl orderAdapter;
    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderEntityMapper mapper;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void saveOrderTest() {
        OrderDomain orderDomain = Utils.createOrderDomain();
        when(mapper.toEntity(orderDomain)).thenReturn(Utils.createOrderEntity());
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(Utils.createOrderEntity());
        when(mapper.toDomainObject(any(OrderEntity.class))).thenReturn(orderDomain);
        OrderDomain result = orderAdapter.saveOrder(orderDomain);
        Assert.assertNotNull(result);
    }

    @Test
    public void getOrderByIdTest() {
        when(orderRepository.findOrderEntityById("2")).thenReturn(Optional.of(Utils.createOrderEntity()));
        when(mapper.toDomainObject(any(OrderEntity.class))).thenReturn(Utils.createOrderDomain());
        Optional<OrderDomain> domain = orderAdapter.getOrdersById("2");
        Assert.assertEquals(true, domain.isPresent());
    }

    @Test
    public void getOrderByCustomerId() {
        when(orderRepository.findOrderEntityByCustomerId("2")).thenReturn(Lists.newArrayList(Utils.createOrderEntity()));
        when(mapper.toDomainObject(any(OrderEntity.class))).thenReturn(Utils.createOrderDomain());
        List<OrderDomain> result = orderAdapter.getOrdersByCustomerId("2");
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void filterOrdersByDateRangeTest() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<OrderEntity> orderEntityPage = new PageImpl<>(Lists.newArrayList(Utils.createOrderEntity()));
        when(orderRepository.findOrderEntityByCreatedDateBetween(LocalDate.now(), LocalDate.now().plusDays(1), pageable)).thenReturn(orderEntityPage);
        when(mapper.toListDomainObject(orderEntityPage.toList())).thenReturn(Lists.newArrayList(Utils.createOrderDomain()));
        PageResponse<OrderDomain> response = orderAdapter.filterOrdersByDateRange(LocalDate.now(), LocalDate.now().plusDays(1), 0, 20);
        Assert.assertEquals(1, response.getList().size());
    }

}
