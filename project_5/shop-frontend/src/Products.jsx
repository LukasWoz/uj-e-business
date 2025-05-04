import React, { useEffect, useState } from "react";
import { useCart } from "./CartContext";

function Products() {
  const [products, setProducts] = useState([]);
  const { addToCart } = useCart();

  useEffect(() => {
    fetch("http://localhost:8080/products")
      .then(response => response.json())
      .then(data => setProducts(data))
      .catch(error => console.error("Error fetching products:", error));
  }, []);

  return (
    <div>
      <h2>Produkty</h2>
      <div style={{ display: "flex", gap: "20px", flexWrap: "wrap" }}>
        {products.map(product => (
          <div key={product.id} style={{
            border: "1px solid #ddd",
            borderRadius: "8px",
            padding: "16px",
            width: "200px"
          }}>
            <strong>{product.name}</strong>
            <p>{product.price} zł</p>
            <button
              onClick={() => addToCart(product)}
              onMouseDown={(e) => e.preventDefault()}
              style={{
                padding: "0.5rem 1rem",
                backgroundColor: "#007bff",
                color: "white",
                border: "none",
                borderRadius: "4px",
                cursor: "pointer",
              }}
            >
              Dodaj do koszyka
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Products;