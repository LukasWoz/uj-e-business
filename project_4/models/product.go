package models

import "gorm.io/gorm"

type Product struct {
	gorm.Model
	Name		 string  `json:"name"`
	Description  string  `json:"description"`
	Price 		 float64 `json:"price"`
	
	CategoryID uint     `json:"category_id"`
	Category   Category `json:"category" gorm:"foreignKey:CategoryID"`
}

func FilterByMinPrice(min float64) func(*gorm.DB) *gorm.DB {
	return func(db *gorm.DB) *gorm.DB {
		return db.Where("price >= ?", min)
	}
}

func FilterByCategory(categoryID uint) func(*gorm.DB) *gorm.DB {
	return func(db *gorm.DB) *gorm.DB {
		return db.Where("category_id = ?", categoryID)
	}
}

func SortByPriceAsc() func(*gorm.DB) *gorm.DB {
	return func(db *gorm.DB) *gorm.DB {
		return db.Order("price ASC")
	}
}

func SortByPriceDesc() func(*gorm.DB) *gorm.DB {
	return func(db *gorm.DB) *gorm.DB {
		return db.Order("price DESC")
	}
}