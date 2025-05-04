package controllers

import (
	"net/http"
	"project_4/models"

	"github.com/labstack/echo/v4"
	"gorm.io/gorm"
)

func CreateCartItem(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	item := new(models.CartItem)

	if err := c.Bind(item); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}

	if err := db.Create(&item).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusCreated, item)
}

func GetCartItems(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	var items []models.CartItem

	if err := db.
		Preload("Product").
		Preload("Cart").
		Find(&items).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusOK, items)
}

func GetCartItem(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var item models.CartItem
	if err := db.Preload("Product").First(&item, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Cart item not found"})
	}

	return c.JSON(http.StatusOK, item)
}

func UpdateCartItem(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var item models.CartItem
	if err := db.First(&item, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Cart item not found"})
	}

	if err := c.Bind(&item); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}

	if err := db.Save(&item).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusOK, item)
}

func DeleteCartItem(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var item models.CartItem
	if err := db.First(&item, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Cart item not found"})
	}

	if err := db.Delete(&item).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.NoContent(http.StatusNoContent)
}