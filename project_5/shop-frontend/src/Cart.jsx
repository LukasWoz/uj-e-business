import React from "react";
import { useCart } from "./CartContext";

function Cart() {
  const { cartItems, clearCart } = useCart();

  const grouped = cartItems.reduce((acc, item) => {
    if (!acc[item.id]) {
      acc[item.id] = { ...item, quantity: 1 };
    } else {
      acc[item.id].quantity += 1;
    }
    return acc;
  }, {});

  const groupedItems = Object.values(grouped);
  const total = groupedItems.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

  return (
    <div>
      <h2>Koszyk</h2>
      {groupedItems.length === 0 ? (
        <p>Twój koszyk jest pusty.</p>
      ) : (
        <div>
          <ul>
            {groupedItems.map((item) => (
              <li key={item.id}>
                {item.name} × {item.quantity} —{" "}
                {(item.price * item.quantity).toFixed(2)} zł
              </li>
            ))}
          </ul>
          <h3>Razem: {total.toFixed(2)} zł</h3>
          <button onClick={clearCart}>Wyczyść koszyk</button>
        </div>
      )}
    </div>
  );
}

export default Cart;