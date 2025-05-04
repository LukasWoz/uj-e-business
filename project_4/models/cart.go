package models

import "gorm.io/gorm"

type Cart struct {
	gorm.Model
	UserID uint   `json:"user_id"`
	Status string `json:"status"`
	CartItems []CartItem `json:"items" gorm:"foreignKey:CartID"`
}