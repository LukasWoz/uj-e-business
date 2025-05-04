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
      <form onSubmit={handleSubmit}>
        <div>
          <label>Kwota: </label>
          <input
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Metoda płatności: </label>
          <input
            type="text"
            value={method}
            onChange={(e) => setMethod(e.target.value)}
            required
          />
        </div>
        <button type="submit">Zapłać</button>
      </form>
    </div>
  );
}

export default Payments;