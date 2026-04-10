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
