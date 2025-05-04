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
	db.AutoMigrate(&models.Cart{})
	db.AutoMigrate(&models.CartItem{})
	db.AutoMigrate(&models.Category{})


	e.Use(func(next echo.HandlerFunc) echo.HandlerFunc {
        return func(c echo.Context) error {
            c.Set("db", db)
            return next(c)
        }
    })

	e.POST("/products", controllers.CreateProduct)
    e.GET("/products", controllers.GetProducts)
	e.GET("/products/:id", controllers.GetProduct)
	e.PUT("/products/:id", controllers.UpdateProduct)
	e.DELETE("/products/:id", controllers.DeleteProduct)
	
	e.POST("/carts", controllers.CreateCart)
	e.GET("/carts", controllers.GetCarts)
	e.GET("/carts/:id", controllers.GetCart)
	e.PUT("/carts/:id", controllers.UpdateCart)
	e.DELETE("/carts/:id", controllers.DeleteCart)

	e.POST("/cart-items", controllers.CreateCartItem)
	e.GET("/cart-items", controllers.GetCartItems)
	e.GET("/cart-items/:id", controllers.GetCartItem)
	e.PUT("/cart-items/:id", controllers.UpdateCartItem)
	e.DELETE("/cart-items/:id", controllers.DeleteCartItem)

	e.POST("/categories", controllers.CreateCategory)
	e.GET("/categories", controllers.GetCategories)
	e.GET("/categories/:id", controllers.GetCategory)
	e.PUT("/categories/:id", controllers.UpdateCategory)
	e.DELETE("/categories/:id", controllers.DeleteCategory)

	e.GET("/", func(c echo.Context) error {
		return c.String(http.StatusOK, "Hello, World!")
	})
	e.Logger.Fatal(e.Start(":1323"))
}