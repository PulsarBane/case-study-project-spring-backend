package com.revature.clp.ecommerce.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.clp.ecommerce.project.dao.OrderDao;
import com.revature.clp.ecommerce.project.dao.OrderItemDao;
import com.revature.clp.ecommerce.project.dao.ProductDao;
import com.revature.clp.ecommerce.project.dao.UserDao;
import com.revature.clp.ecommerce.project.dto.OrderDto;
import com.revature.clp.ecommerce.project.dto.OrderItemDto;
import com.revature.clp.ecommerce.project.dto.UserDto;
import com.revature.clp.ecommerce.project.entity.OrderEntity;
import com.revature.clp.ecommerce.project.entity.OrderItemEntity;
import com.revature.clp.ecommerce.project.entity.ProductEntity;
import com.revature.clp.ecommerce.project.entity.UserEntity;

@Service
@Transactional
public class EcommServiceImpl implements EcommService {

	private static final Logger logger = LoggerFactory.getLogger(EcommServiceImpl.class);

	LocalDateTime date = LocalDateTime.now();

	@Autowired
	UserDao userDao;

	@Autowired
	OrderDao orderDao;

	@Autowired
	OrderItemDao orderItemDao;

	@Autowired
	ProductDao productDao;


	public EcommServiceImpl() {

	}

	/*
	 * Register
	 * As a User, you can register a new account
	 */
	@Override
	public UserDto registerUser(UserDto userDto) {
		logger.info("Entered registerUser() in service layer...");

		UserEntity newUserEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, newUserEntity);
		userDao.saveAndFlush(newUserEntity);

		userDto.setUserID(newUserEntity.getUserID());
		logger.info("Exited registerUser() in service layer...");
		return userDto;
	}

	/*
	 * Login
	 * As a User or Admin, you can log into the application.
	 */
	@Override
	public UserDto findByUsernameAndPassword(UserDto userDto) {
		logger.info("Entered findByUsernameAndPassword() in service layer...");

		Optional<UserEntity> optionalUserEntity = userDao.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
		if (optionalUserEntity.isPresent()) {
			BeanUtils.copyProperties(optionalUserEntity.get(), userDto);
		}
		logger.info("Exited findByUsernameAndPassword() in service layer...");
		return userDto;
	}

	/*
	 * CheckOut
	 * As a User or Guest, I should be able to checkout with the items in my cart, purchase them and remove them from the inventory.
	 * 1. Have save method to set to true
	 */
	@Override
	public OrderDto checkOut(OrderDto orderDto) {
		logger.info("Entered checkOut() in service layer...");

		OrderEntity orderEntity = orderDao.findByUserIDAndOrderStatus(orderDto.getUserID(), false);
		orderEntity.setOrderStatus(true);
		orderDao.save(orderEntity);

		logger.info("Exited checkOut() in service layer...");
		return orderDto;

	}


	/*
	 * User Profile
	 * As a User, I should be able to create and maintain a profile page.
	 */
	@Override
	public UserDto findUserProfile(UserDto userDto) {
		logger.info("Entered findUserProfile() in service layer...");

		Optional<UserEntity> optionalUserEntity = userDao.findById(userDto.getUserID());
		if (optionalUserEntity.isPresent()) {
			BeanUtils.copyProperties(optionalUserEntity.get(), userDto);
		}

		logger.info("Exited findUserProfile() in service layer...");
		return userDto;
	}
	
	/*
	 * Cart
	 * As a User or Guest, you can add items to your cart that you will later purchase or remove from your cart.
	 * Steps:
	 * 1. Fetch record from Order table with a UserID and orderStatus as false.
	 * 	-> If it fetches a record it means we have an open cart -> Update query: PK = 0 (save method)
	 * 	-> If it does not fetch, then it is a new record (first item entering into a cart) -> Insert query: PK = Generated (save method)
	 */
	@Override
	public OrderDto updateCart(OrderDto orderDto) {
		logger.info("Entered updateCart() in service layer...");

		OrderEntity orderEntity = orderDao.findByUserIDAndOrderStatus(orderDto.getUserID(), false);	

		if(orderEntity != null) {
			OrderItemEntity orderItemEntity = new OrderItemEntity();
			orderItemEntity.setOrderNo(orderEntity.getOrderNo());
			orderItemEntity.setProductSku(orderDto.getAllProducts().get(0).getProductSku());
			orderItemDao.save(orderItemEntity);
		}else{
			ProductEntity productEntity = new ProductEntity();
			productEntity.setProductSku(orderDto.getAllProducts().get(0).getProductSku());
			productEntity.setProductName(orderDto.getAllProducts().get(0).getProductName());
			productEntity.setProductImage(orderDto.getAllProducts().get(0).getProductImage());
			productEntity.setProductQuantity(orderDto.getAllProducts().get(0).getProductQuantity());
			productEntity.setProductPrice(orderDto.getAllProducts().get(0).getProductPrice());
			List<ProductEntity> allProducts = new ArrayList<ProductEntity>();
			allProducts.add(productEntity);
			OrderEntity order = new OrderEntity();
			order.setUserID(orderDto.getUserID());
			order.setOrderStatus(false);
			order.setAllProducts(allProducts);
			orderDao.saveAndFlush(order);
			orderDto.setOrderNo(order.getOrderNo());
			orderDto.setOrderDate(order.getOrderDate());
		}

		logger.info("Exited updateCart() in service layer...");
		return orderDto;

	}

	/*
	 * View Previous Orders
	 * As a User, I should be able to view a list of all my previous orders and access the details of each order.
	 * 1. Check for checkout orders 
	 */
	@Override
	public List<OrderDto> findPreviousOrdersById(Integer userID) {
		logger.info("Entered findPreviousOrdersById() in service layer...");

		List<OrderEntity> orderEntity = orderDao.findByUserID(userID);
		List<OrderDto> fetchedOrderDto = new ArrayList<OrderDto>();


		orderEntity.forEach((eachEntity)->{

			OrderDto orderDto = new OrderDto();
			BeanUtils.copyProperties(eachEntity, orderDto);

			// Check if it is a previously checked out order
			if(orderDto.getOrderStatus() == true) {
				List<OrderItemDto> fetchedOrderItemDto = new ArrayList<OrderItemDto>();
				eachEntity.getOrderItems().forEach((eachItemEntity) -> {
					OrderItemDto itemDto = new OrderItemDto();
					BeanUtils.copyProperties(eachItemEntity, itemDto);
					fetchedOrderItemDto.add(itemDto);
				});

				orderDto.setOrderItems(fetchedOrderItemDto);
				fetchedOrderDto.add(orderDto);	
			}
		});

		logger.info("Exited findPreviousOrdersById() in service layer...");
		return fetchedOrderDto;

	}
	


}
