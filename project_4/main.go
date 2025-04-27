package main

import (
	"net/http"
	
	"github.com/labstack/echo/v4"
	"project_4/controllers"
    "project_4/models"
	"gorm.io/driver/sqlite"
    "gorm.io/gorm"
)

func main() {
	e := echo.New()

	db, err := gorm.Open(sqlite.Open("test.db"), &gorm.Config{})
    if err != nil {
        panic("failed to connect database")
    }

	db.AutoMigrate(&models.Product{})

	e.Use(func(next echo.HandlerFunc) echo.HandlerFunc {
        return func(c echo.Context) error {
            c.Set("db", db)
            return next(c)
        }
    })

	e.POST("/products", controllers.CreateProduct)
    e.GET("/products", controllers.GetProducts)
	
	e.GET("/", func(c echo.Context) error {
		return c.String(http.StatusOK, "Hello, World!")
	})
	e.Logger.Fatal(e.Start(":1323"))
}