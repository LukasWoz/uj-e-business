package models

import "gorm.io/gorm"

type Payment struct {
	gorm.Model
	Method string  `json:"method"`
	Amount float64 `json:"amount"`
}