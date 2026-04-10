export type Category = {
  id: string,
  name: string,
  parentId: string,
  parentName: string | null,
}

export type CreateCategoryRequest = {
  name: string,
  parentId: string | null,
}
