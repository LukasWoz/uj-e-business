import React, { createContext, useState, useContext } from "react";

const CartContext = createContext();

export function CartProvider({ children }) {
    const [cartItems, setCartItems] = useState([]);
  
    const addToCart = (product) => {
      setCartItems((prev) => [...prev, product]);
    };
  
    const clearCart = () => setCartItems([]);
  
    const totalAmount = cartItems.reduce(
      (sum, item) => sum + item.price,
      0
    );
  
    return (
      <CartContext.Provider value={{ cartItems, addToCart, clearCart, totalAmount }}>
        {children}
      </CartContext.Provider>
    );
  }

export function useCart() {
  return useContext(CartContext);
}