import { Category } from "./Category"
import { User } from "./User"

export type Product = {
  id: string,
  name: string,
  description: string,
  image: string,
  currentPrice: number,
  createdAt: Date,
  updatedAt: Date,
  category: Category,
  user: User
}

export type CreateProductRequest = {
  name: string,
  description: string,
  image: string,
  currentPrice: number,
  category: string
}

export type UpdateProductRequest = {
  productId: string,
  name: string,
  description: string,
  image: string,
  currentPrice: number,
  category: string
}

export type ProductStock = {
  productId: string,
  inventoryId: string,
  name: string,
  image: string,
  currentPrice: number,
  category: Category
  quantity: number
}

export type UpdateProductStockRequest = {
  quantity: number,
}
