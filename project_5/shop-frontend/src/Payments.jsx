import React, { useState } from "react";

function Payments() {
  const [amount, setAmount] = useState("");
  const [method, setMethod] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    const paymentData = {
      amount: parseFloat(amount),
      method: method
    };

    fetch("http://localhost:8080/payments", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(paymentData)
    })
      .then(response => {
        if (!response.ok) {
          throw new Error("Payment failed");
        }
        return response.text();
      })
      .then(message => {
        alert(message);
        setAmount("");
        setMethod("");
      })
      .catch(error => {
        console.error("Error submitting payment:", error);
        alert("Payment error");
      });
  };

  return (
    <div>
      <h2>Płatności</h2>
      <form
        onSubmit={handleSubmit}
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "1rem",
          maxWidth: "400px",
        }}
      >
        <label>
          Kwota:
          <input
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
            style={{ width: "100%", padding: "0.5rem" }}
          />
        </label>
        <label>
          Metoda płatności:
          <input
            type="text"
            value={method}
            onChange={(e) => setMethod(e.target.value)}
            required
            style={{ width: "100%", padding: "0.5rem" }}
          />
        </label>
        <button
          type="submit"
          style={{
            padding: "0.75rem",
            backgroundColor: "#007bff",
            color: "white",
            border: "none",
            borderRadius: "4px",
          }}
        >
          Zapłać
        </button>
      </form>
    </div>
  );
}

export default Payments;