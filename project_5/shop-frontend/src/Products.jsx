import React, { useEffect, useState } from "react";

function Products() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/products")
      .then(response => response.json())
      .then(data => setProducts(data))
      .catch(error => console.error("Error fetching products:", error));
  }, []);

  return (
    <div>
      <h2>Produkty</h2>
      <ul>
        {products.map(product => (
          <li key={product.id}>
            {product.name} - {product.price} zł
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Products;