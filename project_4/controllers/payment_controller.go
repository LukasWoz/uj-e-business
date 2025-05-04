package controllers

import (
	"net/http"
	"project_4/models"

	"github.com/labstack/echo/v4"
	"gorm.io/gorm"
)

func CreatePayment(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)

	payment := new(models.Payment)
	if err := c.Bind(payment); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}

	if err := db.Create(&payment).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusCreated, payment)
}

func GetPayments(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)

	var payments []models.Payment
	if err := db.Find(&payments).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusOK, payments)
}

func GetPayment(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var payment models.Payment
	if err := db.First(&payment, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Payment not found"})
	}

	return c.JSON(http.StatusOK, payment)
}

func UpdatePayment(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var payment models.Payment
	if err := db.First(&payment, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Payment not found"})
	}

	if err := c.Bind(&payment); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}

	if err := db.Save(&payment).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusOK, payment)
}

func DeletePayment(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var payment models.Payment
	if err := db.First(&payment, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Payment not found"})
	}

	if err := db.Delete(&payment).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.NoContent(http.StatusNoContent)
}