import React from "react";
import { Link } from "react-router-dom";
import "./Header.css";

function Header() {
  return (
    <header className="header">
      <h1 className="logo">Sklep</h1>
      <nav className="nav-links">
        <Link to="/">Produkty</Link>
        <Link to="/cart">Koszyk</Link>
        <Link to="/payments">Płatności</Link>
      </nav>
    </header>
  );
}

export default Header;