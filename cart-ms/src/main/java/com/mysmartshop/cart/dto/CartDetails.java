package com.mysmartshop.cart.dto;

import java.util.List;

import com.mysmartshop.cart.model.CartItems;

import lombok.Builder;
import lombok.Data;

@Data

public class CartDetails {
	private List<CartItems> itemList;
	private float totalCartValue;

}
