package controllers

import (
    "net/http"

    "github.com/labstack/echo/v4"
    "gorm.io/gorm"
    "project_4/models"
)

func CreateProduct(c echo.Context) error {
    db := c.Get("db").(*gorm.DB)

    product := new(models.Product)
    if err := c.Bind(product); err != nil {
        return c.JSON(http.StatusBadRequest, err.Error())
    }

    if err := db.Create(&product).Error; err != nil {
        return c.JSON(http.StatusInternalServerError, err.Error())
    }

    return c.JSON(http.StatusCreated, product)
}

func GetProducts(c echo.Context) error {
    db := c.Get("db").(*gorm.DB)

    var products []models.Product
    if err := db.Find(&products).Error; err != nil {
        return c.JSON(http.StatusInternalServerError, err.Error())
    }

    return c.JSON(http.StatusOK, products)
}

func GetProduct(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var product models.Product
	if err := db.First(&product, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Product not found"})
	}

	return c.JSON(http.StatusOK, product)
}

func UpdateProduct(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var product models.Product
	if err := db.First(&product, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Product not found"})
	}

	if err := c.Bind(&product); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}

	if err := db.Save(&product).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusOK, product)
}

func DeleteProduct(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var product models.Product
	if err := db.First(&product, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Product not found"})
	}

	if err := db.Delete(&product).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.NoContent(http.StatusNoContent)
}