import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import Products from "./Products";
import Payments from "./Payments";
import Cart from "./Cart";
import Header from "./Header";

function App() {
  return (
    <div className="app-container">
      <Header />
      <main className="main-content">
        <Routes>
          <Route path="/" element={<Products />} />
          <Route path="/payments" element={<Payments />} />
          <Route path="/cart" element={<Cart />} />
        </Routes>
      </main>
    </div>
  );
}

export default App;