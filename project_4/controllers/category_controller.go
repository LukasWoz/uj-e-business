package controllers

import (
	"net/http"
	"project_4/models"

	"github.com/labstack/echo/v4"
	"gorm.io/gorm"
)

func CreateCategory(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	category := new(models.Category)

	if err := c.Bind(category); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}

	if err := db.Create(&category).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusCreated, category)
}

func GetCategories(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	var categories []models.Category

	if err := db.Preload("Products").Find(&categories).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusOK, categories)
}

func GetCategory(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var category models.Category
	if err := db.Preload("Products").First(&category, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Category not found"})
	}

	return c.JSON(http.StatusOK, category)
}

func UpdateCategory(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var category models.Category
	if err := db.First(&category, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Category not found"})
	}

	if err := c.Bind(&category); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}

	if err := db.Save(&category).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.JSON(http.StatusOK, category)
}

func DeleteCategory(c echo.Context) error {
	db := c.Get("db").(*gorm.DB)
	id := c.Param("id")

	var category models.Category
	if err := db.First(&category, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"error": "Category not found"})
	}

	if err := db.Delete(&category).Error; err != nil {
		return c.JSON(http.StatusInternalServerError, err.Error())
	}

	return c.NoContent(http.StatusNoContent)
}