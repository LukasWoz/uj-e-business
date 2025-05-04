package controllers

import (
	"net/http"

	"github.com/labstack/echo/v4"
	"gorm.io/gorm"
	"project_4/models"
)

func CreateCart(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)

	cart := new(models.Cart)
	if err := c.Bind(cart); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}

	if err := db.Create(&cart).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusCreated, cart)
}

func GetCarts(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)

	var carts []models.Cart
	if err := db.
		Preload("CartItems").
		Preload("CartItems.Product").
		Find(&carts).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusOK, carts)
}

func GetCart(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var cart models.Cart
	if err := db.
		Preload("CartItems").
		Preload("CartItems.Product").
		First(&cart, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Cart not found"})
	}

	return c.JSON(http.StatusOK, cart)
}

func UpdateCart(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var cart models.Cart
	if err := db.First(&cart, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Cart not found"})
	}

	if err := c.Bind(&cart); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}

	if err := db.Save(&cart).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusOK, cart)
}

func DeleteCart(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var cart models.Cart
	if err := db.First(&cart, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Cart not found"})
	}

	if err := db.Delete(&cart).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.NoContent(http.StatusNoContent)
}